package com.aoeivux.javaweb.测试作用域;

public class Test {
	public static void main(String[] args) {
		new Student();
		new Inner().hi();
		new Inner().i = 12;
	}

	private static class Inner {
		private int i = 0;
		private void hi() {
			System.out.println("inner");
		}
	}
}
