package com.koodu.services;

/**
 *
 * @author Abiola.Adebanjo
 */
import com.koodu.exception.TransactionException;
import com.koodu.models.Transaction;
import com.koodu.utils.Constants;
import com.koodu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koodu.daos.TransactionRepository;
import com.koodu.models.Response;
import com.koodu.models.Subscriber;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.security.crypto.codec.Base64;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    SubscriberService subscriberService;
    
    public Response createTransaction() throws TransactionException {
        Transaction transactionResponse = transactionRepository.save(new Transaction());
        if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
            throw new TransactionException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
        } else {
            return new Response(transactionResponse.getProviderToken());
        }
    }
    
    public Response makeTransaction(Transaction transaction) throws TransactionException {
        Transaction transactionResponse = transactionRepository.findOne(transaction.getProviderToken());
        if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.TOKEN_NOT_FOUND_ERROR_MESSAGE);
        } else if (!StringUtils.isEmpty(transactionResponse.getStatus())) {
            throw new TransactionException(Constants.INVALID_TRANSACTION_ERROR_CODE, Constants.INVALID_TRANSACTION_ERROR_MESSAGE);
        } else {
            Subscriber subscriber = subscriberService.getSubscriber(transaction.getSubscriberId());
            validateMacData(transaction);
            double balance = getBalance(subscriber.getBalance(), transaction.getAmount());
            subscriber.setBalance(balance);
            subscriberService.updateSubscriber(subscriber);
            
            transactionResponse.setSubscriberId(transaction.getSubscriberId());
            transactionResponse.setTransactionId(transaction.getTransactionId());
            transactionResponse.setTransactionType(transaction.getTransactionType());
            transactionResponse.setNarration(transaction.getNarration());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setMercantId(transaction.getMercantId());
            transactionResponse.setTerminalId(transaction.getTerminalId());
            transactionResponse.setAcquirer(transaction.getAcquirer());
            transactionResponse.setMacdata(transaction.getMacdata());
            transactionResponse.setPaycode(transaction.getPaycode());
            transactionResponse.setStatus(Constants.AUTHORIZED);
            transactionResponse = transactionRepository.save(transactionResponse);
            if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
                throw new TransactionException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
            } else {
                return new Response(Constants.AUTHORIZED_SUCCESS_CODE, Constants.AUTHORIZED_SUCCESS_MESSAGE);
            }
        }
    }
    
    public Response reverseTransaction(Transaction transaction) throws TransactionException {
        Transaction transactionResponse = transactionRepository.findByTransactionId(transaction.getOriginalTransactionId());
        if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE);
        } else if (!StringUtils.isEmpty(transactionResponse.getStatus()) && transactionResponse.getStatus().equalsIgnoreCase(Constants.REVERSED)) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE);
        } else if (!transactionResponse.getPaycode().equalsIgnoreCase(transaction.getPaycode())) {
            throw new TransactionException(Constants.INVALID_TRANSACTION_ERROR_CODE, Constants.INVALID_TRANSACTION_ERROR_MESSAGE);
        } else {
            Subscriber subscriber = subscriberService.getSubscriber(transaction.getSubscriberId());
            validateMacData(transaction);
            transactionResponse.setSubscriberId(transaction.getSubscriberId());
            transactionResponse.setOriginalTransactionId(transaction.getOriginalTransactionId());
            transactionResponse.setTransactionId(transaction.getTransactionId());
            transactionResponse.setPaycode(transaction.getPaycode());
            transactionResponse.setStatus(Constants.REVERSED);
            transactionResponse = transactionRepository.save(transactionResponse);
            if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
                throw new TransactionException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
            } else {
                double balance = subscriber.getBalance() + transactionResponse.getAmount();
                subscriber.setBalance(balance);
                subscriberService.updateSubscriber(subscriber);
                return new Response(Constants.AUTHORIZED_SUCCESS_CODE, Constants.APPROVED_SUCCESS_MESSAGE);
            }
        }
    }
    
    public List<Transaction> getAllTransactions() throws TransactionException {
        List<Transaction> transactionResponse = transactionRepository.findAll();
        if (transactionResponse == null || transactionResponse.isEmpty()) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE);
        } else {
            return transactionResponse;
        }
    }
    
    public Transaction getTransaction(String transactionId) throws TransactionException {
        Transaction transactionResponse = transactionRepository.findByTransactionIdOrOriginalTransactionId(transactionId);
        if (transactionResponse == null || StringUtils.isEmpty(transactionResponse.getProviderToken())) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE);
        } else {
            return transactionResponse;
        }
    }
    
    private double getBalance(double balance, double amount) throws TransactionException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new TransactionException(Constants.INSUFFICIENT_BALANCE_ERROR_CODE, Constants.INSUFFICIENT_BALANCE_ERROR_MESSAGE);
        }
        return balance;
    }
    
    private void validateMacData(Transaction transaction) throws TransactionException {
        String macdata = transaction.getMacdata();
        String baseStringToBeSigned = "";
        if (!StringUtils.isEmpty(transaction.getOriginalTransactionId())) {
            baseStringToBeSigned = transaction.getSubscriberId() + "&" + transaction.getPaycode() + "8/orifg8Gt6SRT1RqwkZsMaFqek=";
        } else {
            baseStringToBeSigned = transaction.getSubscriberId() + "&" + transaction.getProviderToken() + "&" + transaction.getTransactionType() + "&" + transaction.getPaycode() + "8/orifg8Gt6SRT1RqwkZsMaFqek=";
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException ex) {
            throw new TransactionException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
        }
        byte[] signatureBytes = messageDigest.digest(baseStringToBeSigned.getBytes());
        String computedMacData = new String(Base64.encode(signatureBytes));
        System.out.println(computedMacData);
        
        if (!computedMacData.equals(macdata)) {
            throw new TransactionException(Constants.SECURITY_VIOLATION_ERROR_CODE, Constants.SECURITY_VIOLATION_ERROR_MESSAGE);
        }
    }
}
