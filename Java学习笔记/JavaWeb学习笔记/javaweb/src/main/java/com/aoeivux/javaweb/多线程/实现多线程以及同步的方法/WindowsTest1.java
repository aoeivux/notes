package com.aoeivux.javaweb.多线程.实现多线程以及同步的方法;

/**
 * 继承Thread实现多线程
 * 
 * 1.同步代码块实现同步
 * 2.静态同步方法实现同步
 */

class Windows1 extends Thread {

	private static int ticket = 100;

	@Override
	public void run() {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			if (ticket == 0)
				break;
			show();
		}
	}

	// 隐式同步监视器->锁，这里是w2,w3,w4三个Windows1的实例对象，多线程不拥有同一把锁,线程同步失败。所以需要使用static制定该同步方法为唯一同步方法
	public static synchronized void show() {
		if (ticket > 0) {
			System.out.println(Thread.currentThread().getName() + ":卖票，票号为:" + ticket--);
		}
	}
}

public class WindowsTest1 {
	public static void main(String[] args) {

		Windows1 w2 = new Windows1();
		Windows1 w3 = new Windows1();
		Windows1 w4 = new Windows1();

		w2.setName("窗口1");
		w3.setName("窗口2");
		w4.setName("窗口3");

		w2.start();
		w3.start();
		w4.start();

	}
}
