package kr.or.ddit.buyer.service;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImplTest {
	
	BuyerService service = new BuyerServiceImpl();

	@Test
	public void testCreateBuyer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveBuyerList() {
		PagingVO<BuyerVO> pagingVO = new PagingVO<BuyerVO>();
		pagingVO.setCurrentPage(1);
		service.retrieveBuyerList(pagingVO);
		assertNotEquals(0, pagingVO.getTotalRecord());
		assertNotNull(pagingVO.getDataList());
	}

	@Test
	public void testRetrieveBuyer() {
		assertNotNull(service.retrieveBuyer("P10101"));
	}

	@Test
	public void testModifyBuyer() {
		fail("Not yet implemented");
	}

}
