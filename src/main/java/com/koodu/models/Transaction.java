package com.koodu.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Abiola.Adebanjo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    @Id
    private String providerToken;
    private long subscriberId;
    private String transactionId;
    private String transactionType;
    private String narration;
    private String mercantId;
    private String terminalId;
    private double amount;
    private String acquirer;
    private String macdata;
    private String paycode;
    private String status;

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(String providerToken) {
        this.providerToken = providerToken;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getMercantId() {
        return mercantId;
    }

    public void setMercantId(String mercantId) {
        this.mercantId = mercantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getMacdata() {
        return macdata;
    }

    public void setMacdata(String macdata) {
        this.macdata = macdata;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
