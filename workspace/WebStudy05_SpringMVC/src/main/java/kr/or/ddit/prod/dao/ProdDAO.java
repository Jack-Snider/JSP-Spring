package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Persistence Layer
 *
 */
@Mapper
public interface ProdDAO {
	public int insertProd(ProdVO prod);
	public ProdVO selectProd(@Param("prodId") String prodId);
	
	/**
	 * 페이징 처리를 위한 전체 레코드 수 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	/**
	 * 페이징 처리를 위한 데이터 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	
	public int updateProd(ProdVO prod);
//	public int deleteProd(String prodId);
}
