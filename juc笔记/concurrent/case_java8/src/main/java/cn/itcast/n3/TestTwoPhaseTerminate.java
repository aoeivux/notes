package cn.itcast.n3;

import javax.management.monitor.Monitor;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * TestTwoPhaseTerminate
 */
@Slf4j(topic = "c.TestTwoPhaseTerminate")
public class TestTwoPhaseTerminate {

	private static Thread monitor;

	public static void main(String[] args) throws InterruptedException {
		TestTwoPhaseTerminate t = new TestTwoPhaseTerminate();
		t.start();
		Thread.sleep(3500);
		t.stop();
	}
	//启动监控线程
	public void start() {
		monitor = new Thread(()->{
			while(true) {
				Thread current = Thread.currentThread();
				if(current.isInterrupted()) {
					log.debug("break");
					break;
				}
				try {
					Thread.sleep(500);//情况1,sleep被打断会清除标记
					log.debug("monitor...");//情况2
				} catch (InterruptedException e) {
					e.printStackTrace();
					//重新设置标记
					monitor.interrupt();
				}
			}
		});
		monitor.start();
	}

	//停止监控线程
	public void stop() {
		monitor.interrupt();
	}
}
