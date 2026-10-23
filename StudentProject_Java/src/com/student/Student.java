package com.student;
/**
 * Student Class
 * Extends Person class
 * Demonstrates: Inheritance, Method Override, Encapsulation
 */
public class Student extends Person {
    private String rollNumber;
    private double[] marks;  // Array of marks in different subjects
    private double gpa;
    
    // Constructor
    public Student(String name, int age, String id, String rollNumber, double[] marks) {
        super(name, age, id);
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.gpa = calculateGPA();
    }
    
    // Calculate GPA from marks (0-10 scale -> 0-4 GPA)
    private double calculateGPA() {
        if (marks.length == 0) return 0;
        
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        double average = sum / marks.length;
        return (average / 10) * 4;  // Convert to 4-point scale
    }
    
    // Calculate average marks
    public double getAverageMarks() {
        if (marks.length == 0) return 0;
        
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.length;
    }
    
    // Get grade based on average
    public String getGrade() {
        double average = getAverageMarks();
        
        if (average >= 90) return "A+ (Excellent)";
        else if (average >= 80) return "A (Good)";
        else if (average >= 70) return "B (Average)";
        else if (average >= 60) return "C (Pass)";
        else return "F (Fail)";
    }
    
    // Override abstract method from Person
    @Override
    public void displayDetails() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       STUDENT INFORMATION           ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("ID: " + getId());
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Average Marks: " + String.format("%.2f", getAverageMarks()));
        System.out.println("Grade: " + getGrade());
        System.out.println("GPA: " + String.format("%.2f", gpa));
    }
    
    // Getters
    public String getRollNumber() {
        return rollNumber;
    }
    
    public double getGPA() {
        return gpa;
    }
    
    public double[] getMarks() {
        return marks;
    }
}
