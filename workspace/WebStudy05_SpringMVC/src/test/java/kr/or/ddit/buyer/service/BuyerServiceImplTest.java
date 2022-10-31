package kr.or.ddit.buyer.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestContextConfiguration;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@RunWith(SpringRunner.class)
@TestContextConfiguration
public class BuyerServiceImplTest {
	
	@Inject
	BuyerService service;

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
