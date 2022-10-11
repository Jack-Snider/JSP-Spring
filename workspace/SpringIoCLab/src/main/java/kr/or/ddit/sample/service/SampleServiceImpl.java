package kr.or.ddit.sample.service;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
	// 1. 결합력 최상
//	private SampleDAO dao = new SampleDAOImpl_MariaDB();
	// 2. Factory object pattern
//	private SampleDAO dao = new SampleDAOFactory().getSampleDAO();
	// 3. Strategy Pattern : 전략 주입자 필요. 주입 방식 (setter, constructor)
	// 4. DI Container 활용.
//	@Resource(name="dao_oracle")
	private SampleDAO dao;
	
	public SampleServiceImpl() {
		super();
		log.info("{} 객체 생성, 기본 생성자", this);
	}
	
//	@Autowired // getBean(Class)->getBean("id")
	@Inject
	@Named("dao_oracle")
	public SampleServiceImpl(SampleDAO dao) {
		super();
		log.info("{} 객체 생성, dao 파라미터를 받는 생성자", this);
		this.dao = dao;
	}

	public void setDao(SampleDAO dao) {
		log.info("dao 를 setter에서 주입받음.");
		this.dao = dao;
	}
	
	public void start() {
		log.info("{}  초기화 (주입) 완료.{}", this, dao);
	}
	
	public void stop() {
		log.info("{} 객체 소멸.", this);
	}
	
	
	@Override
	public String retrieveTeam(Integer teamNumber) {
		String[] rawdata = dao.selectTeam(teamNumber);
		String information = Arrays.toString(rawdata);
		return information;
	}

}
