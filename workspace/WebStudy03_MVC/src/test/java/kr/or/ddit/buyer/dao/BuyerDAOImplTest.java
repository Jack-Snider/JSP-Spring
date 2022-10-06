package kr.or.ddit.buyer.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImplTest {
	BuyerDAO dao = new BuyerDAOImpl();

	PagingVO<BuyerVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}
	
	@Test
	public void testInsertBuyer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTotalRecord() {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		assertNotEquals(0, totalRecord);
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> buyerList = dao.selectBuyerList(pagingVO);
		assertEquals(10, buyerList.size());
	}

	@Test
	public void testSelectBuyer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBuyer() {
		fail("Not yet implemented");
	}

}
