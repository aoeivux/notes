package com.aoeivux.javaweb.多线程.使用Callable创建多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableThread implements Callable<Integer>{
	private int sum = 0;

	@Override
	public Integer call() throws Exception {
		for (int i = 0; i < 100; i++) {
			if(i % 2 == 0) {
				System.out.println(i);
				sum += i;
			}
		}
		return sum;
	}


}

class App {
	public static void main(String[] args) {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableThread());
		Thread thread = new Thread(futureTask);
		thread.start();
	}
}
