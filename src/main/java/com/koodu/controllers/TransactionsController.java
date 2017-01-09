package com.koodu.controllers;

import com.koodu.exception.TransactionException;
import com.koodu.models.Transaction;
import com.koodu.models.Response;
import com.koodu.services.TransactionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abiola.Adebanjo
 */
@RestController
@RequestMapping("/api/v1/pwmtest/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public ResponseEntity<Response> createTransaaction() throws TransactionException {
        Response response = transactionService.createTransaction();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ResponseEntity<Response> authorizeTransaction(@Valid @RequestBody Transaction transaction) throws TransactionException {
        Response response = transactionService.makeTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/reverse", method = RequestMethod.POST)
    public ResponseEntity<Response> reverseTransaction(@Valid @RequestBody Transaction transaction) throws TransactionException {
        Response response = transactionService.reverseTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getAllSubscribers() throws TransactionException {
        List<Transaction> transactionResponse = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getSubscriber(@PathVariable("transactionId") String transactionId) throws TransactionException {
        Transaction transactionResponse = transactionService.getTransaction(transactionId);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
}
