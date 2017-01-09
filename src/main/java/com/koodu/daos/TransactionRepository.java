package com.koodu.daos;

import com.koodu.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Abiola.Adebanjo
 */
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    @Override
    public Transaction save(Transaction bookmark);

    public Transaction findBySubscriberIdAndProviderToken(String subscriberId, String providerToken);

    public Transaction findByTransactionId(String transactionId);

    public Transaction findByTransactionIdOrOriginalTransactionId(String transactionId);
}
