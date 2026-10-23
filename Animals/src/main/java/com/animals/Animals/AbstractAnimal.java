package com.animals.Animals;

public abstract class AbstractAnimal implements Animal {
	String name;

	public AbstractAnimal(String name) {
		this.name = name;
	}

	@Override
	public void walk() {
		System.out.println(name + " is jumping.");
	}
	
	@Override
	public void eat() {
		System.out.println(name + " is eating.");
	}
	
	@Override
	public abstract void run();

}
