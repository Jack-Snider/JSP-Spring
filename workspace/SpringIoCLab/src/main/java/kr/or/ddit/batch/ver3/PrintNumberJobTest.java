package kr.or.ddit.batch.ver3;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1부터 20까지 숫자를 매 1초마다 반복적으로 콘솔에 출력할 것.
 * 상기의 작업을 어플리케이션의 다른 기능에는 전혀 영향이 없이 실행할 것.
 * 상기의 작업을 매 2초마다 새로 시작할 것.
 *
 *  Thread pooling
 */
public class PrintNumberJobTest {
	static class PrintNumberJob implements Runnable{
		public void run() {
			for(int number=1; number<=20; number++) {
				System.out.printf("%d - %s\n", number, Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		};
	}
	
	static class PrintNumberJobGenerateJob implements Runnable{
		
		private ThreadPoolExecutor executor;

		public PrintNumberJobGenerateJob(ThreadPoolExecutor executor) {
			super();
			this.executor = executor;
		}


		@Override
		public void run() {
			while(true) {
				executor.execute(new PrintNumberJob());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, 
						new LinkedBlockingQueue<>(5) 
						, new RejectedExecutionHandler() {
							
							@Override
							public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
								System.err.printf("%s 거절당함\n", r.getClass().getSimpleName());
							}
						}
					);
		
		Runnable job = new PrintNumberJobGenerateJob(executor);
		executor.execute(job);
		
		System.out.println("어플리케이션의 다른 기능");
	}
}










