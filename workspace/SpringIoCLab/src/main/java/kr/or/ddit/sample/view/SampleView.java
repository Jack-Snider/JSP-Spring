package kr.or.ddit.sample.view;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleView {
	
	private SampleService service;
	public SampleView() {
		log.info("{} 객체 생성", this);
	}
//	@Autowired
	@Required
	@Inject
	public void setService(SampleService service) {
		this.service = service;
	}
	
	public void renderMessage(Integer teamNumber) {
		String content = String.format("조회한 모델데이터 : %s\n", service.retrieveTeam(teamNumber));
		System.out.println(content);
	}
	
	public void start() {
		log.info("{}  초기화 완료.", this);
	}
	
	public void stop() {
		log.info("{} 객체 소멸.", this);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/kr/or/ddit/sample/conf/Sample-Context.xml");
//		IoC container -> Dependency Injection container
		
		SampleView view = context.getBean(SampleView.class);
		view.renderMessage(2);
	}
}












