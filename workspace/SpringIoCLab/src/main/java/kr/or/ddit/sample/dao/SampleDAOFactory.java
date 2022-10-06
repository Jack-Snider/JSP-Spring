package kr.or.ddit.sample.dao;
/**
 * Factory : 어떠한 객체를 생산해내는 곳
 * @author PC-04
 *
 */
public class SampleDAOFactory {
	
	public SampleDAO getSampleDAO() {
		return new SampleDAOImpl_MariaDB();
	}
	
}
