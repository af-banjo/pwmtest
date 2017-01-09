package com.koodu.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Abiola.Adebanjo
 * @date Jan 6, 2017
 * @time 10:40:13 AM
 */
@Document
public class Subscriber {

    @Id
    private String id;
    @NotBlank(message = "SubscriberId is required")
    @Indexed(unique = true)
    private String subscriberId;
    private double balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
