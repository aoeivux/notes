package com.aoeivux.javaweb.多线程.多线程单例模式;

/* 懒汉式单例模式双重锁定实现
 * 减少由于直接使用同步方法造成的性能问题，大大减少时间的消耗
 */

public class SingletonDemo2 {
	private volatile static SingletonDemo2 instance;

	//构造方法私有化
	private  SingletonDemo2() {}

	public static SingletonDemo2 getInstance() {
		if(instance == null) {
			synchronized(SingletonDemo2.class) {
				instance = new SingletonDemo2();
			}
		}
		return instance;
	}
}
