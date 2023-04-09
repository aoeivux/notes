package com.aoeivux.javaweb.多线程.实现多线程以及同步的方法;

/**
 * 实现Runnable接口实现多线程
 * 
 * 1.同步代码块实现同步
 * 2.同步方法实现同步
 */

class Windows2 implements Runnable {

	private int ticket = 100;

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {

			synchronized (this) { // 此处的this是下面Windows的实例对象w1
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + ":卖票，票号为:" + ticket);
					ticket--;
				} else {
					break;
				}

			}
		}
	}

}

public class WindowsTest2 {
	public static void main(String[] args) {
		Windows2 w = new Windows2();

		Thread t1 = new Thread(w, "窗口1");
		Thread t2 = new Thread(w, "窗口2");
		Thread t3 = new Thread(w, "窗口3");

		t1.start();
		t2.start();
		t3.start();

	}
}
