package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	
	private ProdDAO dao = new ProdDAOImpl();			

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(prodId);
		return prod;
	}

	@Override
	public List<ProdVO> retrieveProdList() {
		return dao.selectProdList();
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId());
		
		int rowcnt = dao.updateProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
