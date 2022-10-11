package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public SampleDAO getSampleDAO() {
		return new SampleDAOImpl_Oracle();
	}
}
