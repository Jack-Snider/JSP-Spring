package kr.or.ddit.batch.ver1;

/**
 * 1부터 20까지 숫자를 매 1초마다 반복적으로 콘솔에 출력할 것.
 * 상기의 작업을 어플리케이션의 다른 기능에는 전혀 영향이 없이 실행할 것.
 * 상기의 작업을 매 2초마다 새로 시작할 것.
 *
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

		@Override
		public void run() {
			while(true) {
				new Thread(new PrintNumberJob()).start();
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
		
		new Thread(new PrintNumberJobGenerateJob()).start();
		
		System.out.println("어플리케이션의 다른 기능");
	}
}










