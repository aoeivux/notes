package com.aoeivux.javaweb.多线程.多线程单例模式;

/**
 * 饿汉式单例模式
 */

public class SingletonDemo3 {
	private static SingletonDemo3 instance = new SingletonDemo3();

	//私有化构造方法
	private SingletonDemo3() {}

	public static SingletonDemo3 getInstance() {
		return instance;
	}
	
}
