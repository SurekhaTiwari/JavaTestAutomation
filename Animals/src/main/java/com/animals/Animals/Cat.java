package com.animals.Animals;

public class Cat extends AbstractAnimal {
	
	public Cat(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println(name + " is running gracefully.");
		
	}

}
