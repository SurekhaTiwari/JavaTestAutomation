package com.bank;
/**
 * Checking Account Class
 * Extends Account and implements overdraft facility
 * Features: Overdraft protection, no interest, per-check fee
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;
    private double monthlyFee;
    private int checksWritten;
    
    // Constructor
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance, "Checking Account");
        this.overdraftLimit = 5000; // Overdraft protection limit
        this.monthlyFee = 100; // Monthly maintenance fee
        this.checksWritten = 0;
    }
    
    // Override withdraw to allow overdraft
    @Override
    public boolean withdraw(double amount) {
        double availableFunds = balance + overdraftLimit;
        
        if (amount > 0 && amount <= availableFunds) {
            balance -= amount;
            checksWritten++;
            
            if (balance < 0) {
                System.out.println("✓ Withdrawn: Rs. " + amount + " (using overdraft)");
                System.out.println("  ⚠ OVERDRAFT ALERT - Current Balance: Rs. " + balance);
                System.out.println("  Remaining Overdraft: Rs. " + (overdraftLimit + balance));
            } else {
                System.out.println("✓ Withdrawn: Rs. " + amount);
                System.out.println("  New Balance: Rs. " + balance);
            }
            return true;
        } else {
            System.out.println("✗ Exceeded overdraft limit. Available funds: Rs. " + availableFunds);
            return false;
        }
    }
    
    // No interest for checking account
    @Override
    public void applyInterest() {
        System.out.println("✗ Checking accounts do not earn interest.");
    }
    
    // Deduct monthly maintenance fee
    public void deductMonthlyFee() {
        balance -= monthlyFee;
        System.out.println("\n════════════════════════════════");
        System.out.println("  MONTHLY FEE CHARGED");
        System.out.println("════════════════════════════════");
        System.out.println("Monthly Fee: Rs. " + monthlyFee);
        System.out.println("Checks Written: " + checksWritten);
        System.out.println("New Balance: Rs. " + String.format("%.2f", balance));
        System.out.println("════════════════════════════════");
        checksWritten = 0; // Reset for next month
    }
    
    // Get overdraft balance status
    public void displayOverdraftStatus() {
        if (balance < 0) {
            System.out.println("⚠ OVERDRAFT IN USE: Rs. " + String.format("%.2f", Math.abs(balance)));
            System.out.println("  Remaining Overdraft: Rs. " + String.format("%.2f", overdraftLimit + balance));
        } else {
            System.out.println("✓ Overdraft Not In Use");
            System.out.println("  Available Overdraft: Rs. " + overdraftLimit);
        }
    }
    
    // Display checking account specific info
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Overdraft Limit: Rs. " + overdraftLimit);
        System.out.println("Monthly Fee: Rs. " + monthlyFee);
        System.out.println("Checks Written This Month: " + checksWritten);
        displayOverdraftStatus();
    }
    
    // Getters
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public int getChecksWritten() {
        return checksWritten;
    }
}
