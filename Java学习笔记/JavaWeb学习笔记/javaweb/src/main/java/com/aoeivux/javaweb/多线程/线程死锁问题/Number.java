package com.aoeivux.javaweb.多线程.线程死锁问题;

/**
 * 线程通信，使用两个线程交替增加数字
 */


public class Number implements Runnable {
	private static Integer number = 1;

	private Number() {}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				notify();
				if (number <= 100) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(":当前线程"+Thread.currentThread().getName()+" 数字为:" + number++);
				} else {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Number n1 = new Number();

		new Thread(n1,"甲").start();
		new Thread(n1,"乙").start();
	}

}
