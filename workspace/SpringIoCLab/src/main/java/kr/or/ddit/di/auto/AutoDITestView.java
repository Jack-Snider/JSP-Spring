package kr.or.ddit.di.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.view.SampleView;

public class AutoDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/auto/conf/AutoDI-Context.xml");
		context.registerShutdownHook();
		
		SampleView view = context.getBean(SampleView.class);
		view.renderMessage(4);
	}
	
}
