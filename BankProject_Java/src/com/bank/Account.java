package com.bank;
/**
 * Abstract base class representing a Bank Account
 * Demonstrates encapsulation and inheritance
 */
public abstract class Account {
    private String accountNumber;
    private String accountHolder;
    protected double balance;
    private String accountType;
    
    // Constructor
    public Account(String accountNumber, String accountHolder, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountType = accountType;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void applyInterest();
    
    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✓ Deposited: Rs. " + amount);
            System.out.println("  New Balance: Rs. " + balance);
        } else {
            System.out.println("✗ Invalid amount. Amount must be positive.");
        }
    }
    
    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✓ Withdrawn: Rs. " + amount);
            System.out.println("  New Balance: Rs. " + balance);
            return true;
        } else if (amount > balance) {
            System.out.println("✗ Insufficient funds. Current Balance: Rs. " + balance);
            return false;
        } else {
            System.out.println("✗ Invalid amount.");
            return false;
        }
    }
    
    // Check balance
    public double getBalance() {
        return balance;
    }
    
    // Display account details
    public void displayAccountInfo() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║     ACCOUNT INFORMATION         ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: Rs. " + String.format("%.2f", balance));
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public String getAccountType() {
        return accountType;
    }
}
