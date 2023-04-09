package com.aoeivux.javaweb.多线程.生产者消费者问题;


class Clerk {

	private  int productCount = 0;

	public synchronized void consumeProduct() {
		if(productCount > 0) {
			System.out.println(Thread.currentThread().getName() + ":开始消费第" + productCount++ +"个产品");
			productCount++;
		}
		try {
			wait(); // 线程阻塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void productProduct() {
		if(productCount < 20) {
			System.out.println(Thread.currentThread().getName() + ":开始生产第" + productCount + "个产品");
			productCount++;
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class Producer extends Thread {
	private Clerk clerk;
	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}
	@Override
	public void run() {
		System.out.println("开始生产....");
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.productProduct();
		}
	}
	
}


class Consumer extends Thread {
	private Clerk clerk;
	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}
	@Override
	public void run() {
		System.out.println("开始消费....");
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.consumeProduct();
		}
	}
	
}

public class ProductTest {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Producer producer = new Producer(clerk);
		producer.setName("生产者1");

		Consumer consumer = new Consumer(clerk);
		consumer.setName("消费者1");

		producer.start();
		consumer.start();

	}
}
