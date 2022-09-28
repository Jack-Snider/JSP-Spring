package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Persistence Layer
 *
 */
public interface ProdDAO {
	public int insertProd(ProdVO prod);
	public ProdVO selectProd(@Param("prodId") String prodId);
	public List<ProdVO> selectProdList();
	public int updateProd(ProdVO prod);
//	public int deleteProd(String prodId);
}
