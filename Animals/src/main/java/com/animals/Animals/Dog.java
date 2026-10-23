package com.animals.Animals;

public class Dog extends AbstractAnimal {
	
	public Dog(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println(name + " is running fast.");
		
	}

}
