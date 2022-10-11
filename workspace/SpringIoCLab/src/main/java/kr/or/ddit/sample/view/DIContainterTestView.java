package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DIContainterTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/sample/conf/DI-Context.xml");
		
		context.registerShutdownHook();
		
//		SampleView view1 = (SampleView) context.getBean("sampleView1");
//		SampleView view2 = (SampleView) context.getBean("sampleView2");
//		log.info("주입된 객체 : {}", view1);
//		log.info("주입된 객체 : {}", view2);
//		log.info("동일 객체 여부 : {}", view1==view2);
		
	}
}
