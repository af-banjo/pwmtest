package com.koodu.services;

/**
 *
 * @author Abiola.Adebanjo
 */
import com.koodu.daos.SubscriberRepository;
import com.koodu.exception.TransactionException;
import com.koodu.utils.Constants;
import com.koodu.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koodu.models.Subscriber;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    public Subscriber createSubscriber(Subscriber subscriber) throws TransactionException {
        Subscriber subscriberResponse = subscriberRepository.save(subscriber);

        if (subscriberResponse == null || StringUtils.isEmpty(subscriberResponse.getSubscriberId())) {
            throw new TransactionException(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE);
        } else {
            return subscriberResponse;
        }
    }

    public List<Subscriber> getAllSubscribers() throws TransactionException {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        if (subscribers == null || subscribers.isEmpty()) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.SUBSCRIBER_NOT_FOUND_ERROR_MESSAGE);
        } else {
            return subscribers;
        }
    }

    public Subscriber getSubscriber(String subscriberId) throws TransactionException {
        Subscriber subscriber = subscriberRepository.findBySubscriberId(subscriberId);
        if (subscriber == null || StringUtils.isEmpty(subscriber.getSubscriberId())) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.SUBSCRIBER_NOT_FOUND_ERROR_MESSAGE);
        } else {
            return subscriber;
        }
    }

    public Subscriber updateSubscriber(Subscriber subscriber) throws TransactionException {
        Subscriber subscriberResponse = subscriberRepository.findBySubscriberId(subscriber.getSubscriberId());
        if (subscriberResponse == null || StringUtils.isEmpty(subscriberResponse.getSubscriberId())) {
            throw new TransactionException(Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE, Constants.SUBSCRIBER_NOT_FOUND_ERROR_MESSAGE);
        } else {
            subscriberResponse.setBalance(subscriber.getBalance());
            subscriberResponse = subscriberRepository.save(subscriberResponse);
            return subscriberResponse;
        }
    }

}
