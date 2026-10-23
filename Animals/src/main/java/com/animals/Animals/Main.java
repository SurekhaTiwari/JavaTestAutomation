package com.animals.Animals;

public class Main {

	public static void main(String[] args) {
		Dog dog = new Dog("Buddy");
		Cat cat = new Cat("Whiskers");
		
		System.out.println("---------Dog behavior---------");
		dog.walk();
		dog.run();
		dog.eat();
		
		System.out.println("---------Cat behavior---------");
		cat.walk();
		cat.run();
		cat.eat();

	}

}
