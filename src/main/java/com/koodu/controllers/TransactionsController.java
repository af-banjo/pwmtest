package com.koodu.controllers;

import com.koodu.exception.TransactionException;
import com.koodu.models.Transaction;
import com.koodu.models.Response;
import com.koodu.models.Subscriber;
import com.koodu.services.TransactionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Transaction> createTransaaction() throws TransactionException {
        Transaction transaction = transactionService.createTransaction();
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ResponseEntity<Response> authorizeTransaaction(@Valid @RequestBody Transaction transaction) throws TransactionException {
        Response response = transactionService.makeTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
