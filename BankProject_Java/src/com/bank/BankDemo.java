package com.bank;
/**
 * Bank Management System - Main Demo Class
 * Demonstrates all features of SavingsAccount and CheckingAccount
 * Perfect for interview coding demonstrations
 */
public class BankDemo {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         BANK MANAGEMENT SYSTEM - LIVE DEMO                 ║");
        System.out.println("║    Interview Question: Account Management with OOP         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        // Create accounts
        SavingsAccount savingsAcc = new SavingsAccount("SAV-001", "Rajesh Kumar", 50000);
        CheckingAccount checkingAcc = new CheckingAccount("CHK-001", "Priya Singh", 30000);
        
        // ========== DEMO 1: Display Initial Account Info ==========
        demoSection("DEMO 1: Initial Account Information");
        savingsAcc.displayAccountInfo();
        checkingAcc.displayAccountInfo();
        
        // ========== DEMO 2: Basic Transactions ==========
        demoSection("DEMO 2: Basic Transactions");
        System.out.println("\n--- Savings Account: Deposit Rs. 20000 ---");
        savingsAcc.deposit(20000);
        
        System.out.println("\n--- Checking Account: Withdraw Rs. 5000 ---");
        checkingAcc.withdraw(5000);
        
        // ========== DEMO 3: Savings Account - Minimum Balance Check ==========
        demoSection("DEMO 3: Savings Account - Minimum Balance Protection");
        System.out.println("Current Balance: Rs. " + savingsAcc.getBalance());
        System.out.println("Minimum Balance Required: Rs. " + savingsAcc.getMinimumBalance());
        System.out.println("\n--- Attempting to withdraw Rs. 68000 (would breach minimum) ---");
        savingsAcc.withdraw(68000);
        
        System.out.println("\n--- Withdrawing valid amount: Rs. 40000 ---");
        savingsAcc.withdraw(40000);
        
        // ========== DEMO 4: Interest Calculation ==========
        demoSection("DEMO 4: Interest Calculation");
        System.out.println("--- Savings Account: Applying Monthly Interest ---");
        savingsAcc.applyInterest();
        
        System.out.println("\n--- Checking Account: Attempting to apply interest ---");
        checkingAcc.applyInterest();
        
        // ========== DEMO 5: Checking Account - Overdraft Feature ==========
        demoSection("DEMO 5: Checking Account - Overdraft Protection");
        System.out.println("Current Balance: Rs. " + checkingAcc.getBalance());
        System.out.println("Overdraft Limit: Rs. " + checkingAcc.getOverdraftLimit());
        
        System.out.println("\n--- Attempting to withdraw Rs. 32000 (within overdraft limit) ---");
        checkingAcc.withdraw(32000);
        
        System.out.println("\n--- Attempting to withdraw Rs. 5500 (would exceed overdraft) ---");
        checkingAcc.withdraw(5500);
        
        // ========== DEMO 6: Monthly Fee Deduction ==========
        demoSection("DEMO 6: Monthly Fee Deduction (Checking Account)");
        System.out.println("Checks written this month: " + checkingAcc.getChecksWritten());
        checkingAcc.deductMonthlyFee();
        
        // ========== DEMO 7: Final Account Status ==========
        demoSection("DEMO 7: Final Account Status");
        savingsAcc.displayAccountInfo();
        checkingAcc.displayAccountInfo();
        
        // ========== DEMO 8: Polymorphism Demonstration ==========
        demoSection("DEMO 8: Polymorphism - Array of Different Accounts");
        Account[] accounts = { savingsAcc, checkingAcc };
        
        System.out.println("Using polymorphism to process all accounts:");
        for (Account acc : accounts) {
            System.out.println("\n--- " + acc.getAccountType() + " ---");
            System.out.println("Account Holder: " + acc.getAccountHolder());
            System.out.println("Balance: Rs. " + acc.getBalance());
            acc.applyInterest();
        }
        
        // ========== SUMMARY ==========
        demoSection("SUMMARY - Key OOP Concepts Demonstrated");
        System.out.println("\n1. ✓ ENCAPSULATION");
        System.out.println("   - Private variables (accountNumber, balance)");
        System.out.println("   - Public getter/setter methods for controlled access");
        
        System.out.println("\n2. ✓ INHERITANCE");
        System.out.println("   - SavingsAccount and CheckingAccount extend Account");
        System.out.println("   - Reuse common functionality");
        
        System.out.println("\n3. ✓ POLYMORPHISM");
        System.out.println("   - Abstract method applyInterest() implemented differently");
        System.out.println("   - Method override: withdraw() behavior varies by account type");
        
        System.out.println("\n4. ✓ ABSTRACTION");
        System.out.println("   - Abstract Account class hides implementation details");
        System.out.println("   - Subclasses implement specific behaviors");
        
        System.out.println("\n5. ✓ REAL-WORLD SCENARIOS");
        System.out.println("   - Minimum balance enforcement");
        System.out.println("   - Interest calculation");
        System.out.println("   - Overdraft protection");
        System.out.println("   - Monthly fees");
        
        System.out.println("\n" + "═".repeat(61) + "\n");
    }
    
    // Helper method to print section headers
    private static void demoSection(String title) {
        System.out.println("\n" + "═".repeat(61));
        System.out.println("  " + title);
        System.out.println("═".repeat(61));
    }
}
