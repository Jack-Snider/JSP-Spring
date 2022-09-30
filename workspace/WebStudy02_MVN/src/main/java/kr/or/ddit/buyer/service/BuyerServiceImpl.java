package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements BuyerService {
	private BuyerDAO dao = new BuyerDAOImpl();

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		int rowcnt = dao.insertBuyer(buyer);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public void retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		//============================================================
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BuyerVO> buyerList = dao.selectBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		//============================================================
	}

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = dao.selectBuyer(buyerId);
		if(buyer == null)
			throw new PKNotFoundException(buyerId);
		return buyer;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		retrieveBuyer(buyer.getBuyerId());
		
		int rowcnt = dao.updateBuyer(buyer);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
