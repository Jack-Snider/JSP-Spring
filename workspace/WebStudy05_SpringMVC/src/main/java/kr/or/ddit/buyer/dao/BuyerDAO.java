package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface BuyerDAO {
	/**
	 * 거래처 신규 등록
	 * @param buyer
	 * @return
	 */
	public int insertBuyer(BuyerVO buyer);
	/**
	 * 검색 조건에 맞는 레코드수(totalRecord) 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<BuyerVO> pagingVO);
	/**
	 * 검색 조건에 맞는 레코드 목록(dataList) 조회
	 * @param pagingVO
	 * @return
	 */
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 상세 조회
	 * @param buyerId
	 * @return 없으면, null반환
	 */
	public BuyerVO selectBuyer(@Param("buyerId") String buyerId);
	
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return
	 */
	public int updateBuyer(BuyerVO buyer);
}
