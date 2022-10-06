package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DIContainerTestView {
	
	public static void main( String ... args ) {
		
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext( "classpath:/kr/or/ddit/sample/conf/DI-Context.xml" );
				// classpath:  -> 경로를 classpath 아래에서 찾아라.
				// file: -> 경로를 파일 아래에서 찾아라.
				// http: -> 경로를 웹에서 찾아라.
		
		context.registerShutdownHook(); // 예약 스레드 : non 데몬스레드가 하나도 안남아있으면 컨테이너를 종료해줌
		
		//SampleView view = context.getBean( SampleView.class );
		// 위의 방법이랑은 차이가 있다, 둘다 객체를 불러오는 것이지만 아래 방법은
		// context.registerShutdownHook()를 쓸 수 있다.
		// 즉, .close()를 따로 안해줘도 됨
//		SampleView view1 = ( SampleView )context.getBean( "sampleView1" ); // bean 아이디를 넣어줌
//		SampleView view2 = ( SampleView )context.getBean( "sampleView2" ); // bean 아이디를 넣어줌
//		log.info( "주입된 객체 : {}", view1 );
//		log.info( "주입된 객체 : {}", view2 );
//		log.info( "동일 객체 여부 : {}", view1 == view2 );
				
	}
}
