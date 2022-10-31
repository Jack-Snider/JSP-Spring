package kr.or.ddit.buyer.service;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public interface BuyerService {
	/**
	 * 신규 거래처 등록
	 * @param buyer
	 * @return ServiceResult.OK, ServiceResult.FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	/**
	 * 페이징과 검색이 적용된 거래처 목록 조회
	 * Call by Reference 구조를 활용하여, totalRecord 와 dataList 완성함.
	 * @param pagingVO
	 */
	public void retrieveBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * 거래처 상세 조회
	 * @param buyerId
	 * @return 없으면, {@link PKNotFoundException} 발생
	 */
	public BuyerVO retrieveBuyer(String buyerId);
	
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return 없으면, {@link PKNotFoundException}, 
	 * 				{@link ServiceResult.OK}, {@link ServiceResult.FAIL}
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
}
