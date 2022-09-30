package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdServiceImplTest {
	
	ProdService service = new ProdServiceImpl();

	@Test
	public void testCreateProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProdList() {
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		assertNotNull(prodList);
		log.info("prodList : \n{}", prodList);
	}

	@Test
	public void testModifyProd() {
		fail("Not yet implemented");
	}

}
