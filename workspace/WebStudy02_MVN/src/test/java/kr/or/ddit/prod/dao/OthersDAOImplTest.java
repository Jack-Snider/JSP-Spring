package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class OthersDAOImplTest {

	OthersDAO dao = new OthersDAOImpl();
	
	@Test
	public void testSelectLprodList() {
		dao.selectLprodList();
	}

	@Test
	public void testSelectBuyerList() {
		dao.selectBuyerList("P101");
	}

}
