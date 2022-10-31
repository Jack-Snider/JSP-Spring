package kr.or.ddit.batch.ver2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 1부터 20까지 숫자를 매 1초마다 반복적으로 콘솔에 출력할 것.
 * 상기의 작업을 어플리케이션의 다른 기능에는 전혀 영향이 없이 실행할 것.
 * 상기의 작업을 매 2초마다 새로 시작할 것.
 * 
 * Timer + TimerTask
 *
 */
public class PrintNumberJobTest {
	static class PrintNumberJob extends TimerTask{
		private int number;
		
		@Override
		public void run() {
			System.out.printf("%d - %s\n", ++number, Thread.currentThread().getName());
			if(number==20) cancel();
		};
	}
	
	static class PrintNumberJobGenerateJob extends TimerTask{
		
		private Timer timer;

		public PrintNumberJobGenerateJob(Timer timer) {
			super();
			this.timer = timer;
		}

		@Override
		public void run() {
			timer.scheduleAtFixedRate(new PrintNumberJob(), 0, 1000);
		}
		
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new PrintNumberJobGenerateJob(timer);
		timer.schedule(task, 0, 2000);
		
		
		System.out.println("어플리케이션의 다른 기능");
	}
}










