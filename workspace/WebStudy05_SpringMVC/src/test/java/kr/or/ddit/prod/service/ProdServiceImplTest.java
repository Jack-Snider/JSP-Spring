package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestContextConfiguration;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@TestContextConfiguration
public class ProdServiceImplTest {
	
	@Inject
	ProdService service;

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
