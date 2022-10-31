package kr.or.ddit.batch.ver4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1부터 20까지 숫자를 매 1초마다 반복적으로 콘솔에 출력할 것.
 * 상기의 작업을 어플리케이션의 다른 기능에는 전혀 영향이 없이 실행할 것.
 * 상기의 작업을 매 2초마다 새로 시작할 것.
 *
 * Schedule + Thread Pooling
 */
public class PrintNumberJobTest {
	static class PrintNumberJob implements Runnable{
		private int number;
		public void run() {
			System.out.printf("%d - %s\n", ++number, Thread.currentThread().getName());
		};
	}
	
	static class PrintNumberJobGenerateJob implements Runnable{

		@Override
		public void run() {
			new PrintNumberJob();
		}
		
	}
	
	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		
		executor.scheduleAtFixedRate(new PrintNumberJob(), 0, 1, TimeUnit.SECONDS);
		
		System.out.println("어플리케이션의 다른 기능");
	}
}










