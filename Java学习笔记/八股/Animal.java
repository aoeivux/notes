package com.aoeivux.八股;

public class Animal {
	private String movement;

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public Animal(){}

	public Animal(String movString) {
		this.movement = movString;
	}

	@Override
	public String toString() {
		return "Animal [movement=" + movement + "]";
	}

}
