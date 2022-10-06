package kr.or.ddit.sample.service;

import java.util.Arrays;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleServiceImpl implements SampleService {

	// 1. 결합력 최상
//	private SampleDAO dao = new SampleDAOImpl_MariaDB();
	
	/*
	 * 데이터베이스는 dao와 연결되므로 DB가 바뀌면 DAO도 바껴야 한다.
	 * 이러한 결합도를 낮추기 위해 다양한 디자인패턴 사용
	 */
	
	
	// 2. Factory Object Pattern
	// 공장객체에서 받아서 동적으로 사용
//	private SampleDAO dao = new SampleDAOFactory().getSampleDAO();

	
	// 3. Strategy Pattern :  전략 주입자 필요, 주입 방식( setter, contructor ) 
	// 4. DI Container 활용
	private SampleDAO dao;
	
	public SampleServiceImpl() {
		super();
		log.info( "{}객체 생성, 기본 생성자", this );
	}
	
	public SampleServiceImpl( SampleDAO dao ) {
		super();
		log.info( "{}객체 생성, 기본 생성자", this );
		this.dao = dao;
	}



	public void start() {
		log.info( "{} 초기화( 주입 ) 완료.", this.dao );
	}
	
	public void stop() {
		log.info( "{} 객체 소멸.", this );
	}

	// setter를 통해 dao객체 초기화
	public void setDao( SampleDAO dao ) {
		log.info( "dao 를 setter에서 주입받음" );
		this.dao = dao;
	}




	@Override
	public String retrieveTeam( Integer teamNumber ) {
		
		String[] rawdata = dao.selectTeam( teamNumber );
		String information =  Arrays.toString( rawdata );
		return information;
		
	}

}
