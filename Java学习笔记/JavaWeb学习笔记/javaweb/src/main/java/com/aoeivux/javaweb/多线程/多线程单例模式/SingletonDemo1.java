package com.aoeivux.javaweb.多线程.多线程单例模式;

/**
 * 1.懒汉式单例模式,常见实现
 */

public class SingletonDemo1 {
	private static SingletonDemo1 instance;

	//构造方法private化
	private SingletonDemo1(){}
	
	public static synchronized SingletonDemo1 getInstance() {
		if(instance == null) {
			instance = new SingletonDemo1();
		}
		return instance;
	}
}