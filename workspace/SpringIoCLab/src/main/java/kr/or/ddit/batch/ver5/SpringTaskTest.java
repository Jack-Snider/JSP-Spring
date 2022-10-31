package kr.or.ddit.batch.ver5;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTaskTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("/kr/or/ddit/batch/conf/quartz-context.xml");
		context.registerShutdownHook();
	}
}
