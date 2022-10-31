package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 *
 */
public interface ProdService {
	public ServiceResult createProd(ProdVO prod);
	public ProdVO retrieveProd(String prodId);
	
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO);
	
	
	public ServiceResult modifyProd(ProdVO prod);
}
