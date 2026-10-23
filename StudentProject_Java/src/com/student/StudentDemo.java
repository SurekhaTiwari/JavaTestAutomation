package com.student;
/**
 * Student Management System - Main Demo
 * Simple interview project demonstrating OOP concepts
 * Time to code live: 10-15 minutes
 */
public class StudentDemo {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   STUDENT MANAGEMENT SYSTEM - LIVE DEMO    ║");
        System.out.println("║        Simple OOP Project (2 Classes)      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        // Create students
        double[] marks1 = {85, 90, 88, 92};
        Student student1 = new Student("Rahul Kumar", 20, "STU001", "CS101", marks1);
        
        double[] marks2 = {95, 98, 96, 99};
        Student student2 = new Student("Priya Singh", 20, "STU002", "CS102", marks2);
        
        double[] marks3 = {65, 70, 68, 72};
        Student student3 = new Student("Amit Patel", 21, "STU003", "CS103", marks3);
        
        // ========== DEMO 1: Display Individual Student ==========
        demoSection("DEMO 1: Display Student Information");
        student1.displayDetails();
        
        // ========== DEMO 2: Inherited Method ==========
        demoSection("DEMO 2: Using Inherited Method from Person");
        student1.greet();
        System.out.println("Age: " + student1.getAge());
        
        // ========== DEMO 3: Calculate GPA ==========
        demoSection("DEMO 3: GPA and Grade Calculation");
        System.out.println("Student 1 - " + student1.getName() + ":");
        System.out.println("  Average: " + String.format("%.2f", student1.getAverageMarks()));
        System.out.println("  Grade: " + student1.getGrade());
        System.out.println("  GPA: " + String.format("%.2f", student1.getGPA()));
        
        System.out.println("\nStudent 2 - " + student2.getName() + ":");
        System.out.println("  Average: " + String.format("%.2f", student2.getAverageMarks()));
        System.out.println("  Grade: " + student2.getGrade());
        System.out.println("  GPA: " + String.format("%.2f", student2.getGPA()));
        
        System.out.println("\nStudent 3 - " + student3.getName() + ":");
        System.out.println("  Average: " + String.format("%.2f", student3.getAverageMarks()));
        System.out.println("  Grade: " + student3.getGrade());
        System.out.println("  GPA: " + String.format("%.2f", student3.getGPA()));
        
        // ========== DEMO 4: Compare Students ==========
        demoSection("DEMO 4: Comparing Students");
        Student topStudent = findTopStudent(student1, student2, student3);
        System.out.println("Topper: " + topStudent.getName());
        System.out.println("GPA: " + String.format("%.2f", topStudent.getGPA()));
        
        // ========== DEMO 5: Array of Students (Polymorphism) ==========
        demoSection("DEMO 5: Polymorphism - Array of Students");
        Person[] persons = {student1, student2, student3};
        
        System.out.println("Using Person reference to call Student methods:\n");
        for (int i = 0; i < persons.length; i++) {
            persons[i].displayDetails();
        }
        
        // ========== SUMMARY ==========
        demoSection("SUMMARY - OOP Concepts Demonstrated");
        System.out.println("\n1. ✓ ENCAPSULATION");
        System.out.println("   - Private variables (marks, gpa)");
        System.out.println("   - Public methods for access");
        
        System.out.println("\n2. ✓ INHERITANCE");
        System.out.println("   - Student extends Person");
        System.out.println("   - Reuse of name, age, id");
        
        System.out.println("\n3. ✓ ABSTRACTION");
        System.out.println("   - Abstract Person class");
        System.out.println("   - Abstract displayDetails() method");
        
        System.out.println("\n4. ✓ POLYMORPHISM");
        System.out.println("   - Person reference, Student object");
        System.out.println("   - Method override: displayDetails()");
        
        System.out.println("\n5. ✓ REAL-WORLD LOGIC");
        System.out.println("   - GPA calculation");
        System.out.println("   - Grade assignment");
        System.out.println("   - Student comparison");
        
        System.out.println("\n" + "═".repeat(44) + "\n");
    }
    
    // Helper method to find student with highest GPA
    public static Student findTopStudent(Student... students) {
        Student topStudent = students[0];
        for (Student student : students) {
            if (student.getGPA() > topStudent.getGPA()) {
                topStudent = student;
            }
        }
        return topStudent;
    }
    
    // Helper method to print section headers
    private static void demoSection(String title) {
        System.out.println("\n" + "═".repeat(44));
        System.out.println("  " + title);
        System.out.println("═".repeat(44));
    }
}
