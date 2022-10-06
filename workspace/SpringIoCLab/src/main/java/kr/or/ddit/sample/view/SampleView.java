package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleView {

	// private SampleDAO dao = new SampleDAOImpl_MariaDB(); // 컨테이너가 bean으로 가져가서 더이상
	// new가 필요없음
	// private SampleService service = new SampleServiceImpl( dao ); // 컨테이너가 bean으로
	// 가져가서 더이상 new가 필요없음

	private SampleService service;

	public SampleView() {
		log.info( "{} 객체 생성", this );
	}
	
	
	public void setService(SampleService service) {
		this.service = service;
	}

	public void renderMessage( Integer teamNumber ) {
				
		String content = String.format("조회한 모델데이터 : %s\n", service.retrieveTeam(teamNumber));
		System.out.println(content);
	}
	
	public void start() {
		log.info( "{} 초기화 완료.", this );
	}
	
	public void stop() {
		log.info( "{} 객체 소멸.", this );
	}

	public static void main(String... args) {

		// IoC container -> Dependency Injection container
		// ApplicationContext은 Spring에서 최상위 객체
		// ( bean객체를 담고있는 bean 컨테이너 xml 파일의 경로를 넣어주면 context는 컨테이너 변수로 쓸 수 있다. )
		ApplicationContext context = new ClassPathXmlApplicationContext( "/kr/or/ddit/sample/conf/Sample-Context.xml" );
		
		// 창고( context )에서 객체를 가져옴, 파라미터는 가져올 객체의 클래스정보
		SampleView view = context.getBean( SampleView.class );
		view.renderMessage(2);

	}

}
