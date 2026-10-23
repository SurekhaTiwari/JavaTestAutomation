package com.student;
/**
 * Abstract Person Class
 * Base class for all people in the system
 * Demonstrates: Encapsulation, Abstraction
 */
public abstract class Person {
    private String name;
    private int age;
    private String id;
    
    // Constructor
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void displayDetails();
    
    // Concrete method - available to all subclasses
    public void greet() {
        System.out.println("Hello, I am " + name);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getId() {
        return id;
    }
}
