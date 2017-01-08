package com.koodu.controllers;

import com.koodu.exception.TransactionException;
import com.koodu.models.Subscriber;
import com.koodu.services.SubscriberService;
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
@RequestMapping("/api/v1/pwmtest/subscribers")
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Subscriber> createSubscriber(@Valid @RequestBody Subscriber subscriber) throws TransactionException {
        Subscriber subscriberResponse = subscriberService.createSubscriber(subscriber);
        return new ResponseEntity<>(subscriberResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Subscriber>> getAllSubscribers() throws TransactionException {
        List<Subscriber> subscriberResponse = subscriberService.getAllSubscribers();
        return new ResponseEntity<>(subscriberResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET)
    public ResponseEntity<Subscriber> getSubscriber(@PathVariable("subscriberId") long subscriberId) throws TransactionException {
        Subscriber subscriberResponse = subscriberService.getSubscriber(subscriberId);
        return new ResponseEntity<>(subscriberResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Subscriber> updateSubscriber(@Valid @RequestBody Subscriber subscriber) throws TransactionException {
        Subscriber subscriberResponse = subscriberService.updateSubscriber(subscriber);
        return new ResponseEntity<>(subscriberResponse, HttpStatus.OK);
    }
}
