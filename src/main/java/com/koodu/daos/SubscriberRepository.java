package com.koodu.daos;

import com.koodu.models.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Abiola.Adebanjo
 */
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {

    @Override
    public Subscriber save(Subscriber subscriber);
    
    public Subscriber findBySubscriberId(long subscirberId);
}
