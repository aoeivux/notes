package com.aoeivux.javaweb.测试子类重写父类方法执行顺序问题;

public class Animal {
	public void hi() {
		System.out.println("Animal");
	}
	public static void main(String[] args) {
		new Dog().hi();
	}
}

class Dog extends Animal {

	@Override
	public void hi() {
		super.hi();
		System.out.println("Dog");
	}
}
