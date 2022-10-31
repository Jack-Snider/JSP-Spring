package kr.or.ddit.batch.ver5;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintNunberJob {
	private int number;
	
//	@Scheduled(initialDelay=0, fixedRate=1000)
//	@Scheduled(cron="0 0 3 ? * MON")
	public void printNumber() {
		System.out.printf("%d - %s\n", ++number, Thread.currentThread().getName());
	}
}
