package com.bank;
/**
 * Savings Account Class
 * Extends Account and implements interest calculation
 * Features: 4% annual interest, minimum balance requirement
 */
public class SavingsAccount extends Account {
    private double interestRate;
    private double minimumBalance;
    private int monthsSinceLastInterest;
    
    // Constructor
    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance, "Savings Account");
        this.interestRate = 0.04; // 4% annual interest
        this.minimumBalance = 1000; // Minimum balance requirement
        this.monthsSinceLastInterest = 0;
    }
    
    // Override withdraw to check minimum balance
    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < minimumBalance) {
            System.out.println("✗ Withdrawal denied! Minimum balance of Rs. " + minimumBalance + " must be maintained.");
            System.out.println("  Current Balance: Rs. " + balance);
            return false;
        }
        return super.withdraw(amount);
    }
    
    // Apply interest - compound interest calculation
    @Override
    public void applyInterest() {
        double monthlyRate = interestRate / 12;
        double interest = balance * monthlyRate;
        balance += interest;
        monthsSinceLastInterest = 0;
        
        System.out.println("\n════════════════════════════════");
        System.out.println("  INTEREST APPLIED (Monthly)");
        System.out.println("════════════════════════════════");
        System.out.println("Interest Rate: " + (interestRate * 100) + "% per annum");
        System.out.println("Interest Added: Rs. " + String.format("%.2f", interest));
        System.out.println("New Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("════════════════════════════════");
    }
    
    // Calculate interest without applying (for preview)
    public double calculateInterestPreview() {
        double monthlyRate = interestRate / 12;
        return balance * monthlyRate;
    }
    
    // Get minimum balance requirement
    public double getMinimumBalance() {
        return minimumBalance;
    }
    
    // Display savings account specific info
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Interest Rate: " + (interestRate * 100) + "% p.a.");
        System.out.println("Minimum Balance: Rs. " + minimumBalance);
        System.out.println("Expected Monthly Interest: Rs. " + 
                          String.format("%.2f", calculateInterestPreview()));
    }
}
