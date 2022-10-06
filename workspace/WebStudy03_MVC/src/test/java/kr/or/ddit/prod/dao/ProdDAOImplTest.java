package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdDAOImplTest {
	
	ProdDAO dao = new ProdDAOImpl();
	ProdVO prod;
	
	@Before
	public void setUp() {
		prod = new ProdVO();
		prod.setProdLgu("P101");
		prod.setProdBuyer("P10101");
		prod.setProdName("신규상품");
		prod.setProdCost(1000);
		prod.setProdPrice(1000);
		prod.setProdSale(1000);
		prod.setProdOutline("상품 개요");
		prod.setProdImg("상품이미지경로");
		prod.setProdTotalstock(10);
		prod.setProdProperstock(10);
	}

	@Test
	public void testInsertProd() {
		int rowcnt = dao.insertProd(prod);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("sdfasdasdf");
		assertNull(prod);
		prod = dao.selectProd("P101000007");
		assertNotNull(prod);
		log.info("prod: {}", prod);
	}

	@Test
	public void testSelectProdList() {
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		log.info("prodList: {}", prodList);
	}

	@Test
	public void testUpdateProd() {
		prod.setProdId("P101000007");
		prod.setProdName("구상품");
		int rowcnt = dao.updateProd(prod);
		assertEquals(1, rowcnt);
	}

}











