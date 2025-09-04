package BankingSystem;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private int accountID;
    private String type;   // Deposit / Withdrawal
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int transactionID, int accountID, String type, double amount) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public int getTransactionID() { 
    	return transactionID; }
    public int getAccountID() { 
    	return accountID; }
    public String getType() { 
    	return type; }
    public double getAmount() {
    	return amount; }
    public LocalDateTime getTimestamp() {
    	return timestamp; }

    }

