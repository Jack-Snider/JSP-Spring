package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;

/**
 * /buyer/buyerList.do(GET)
 * /buyer/buyerView.do?what=P10101(GET)
 * /buyer/buyerInsert.do(GET)
 * /buyer/buyerInsert.do(POST)
 * /buyer/buyerUpdate.do?what=P10101(GET)
 * /buyer/buyerUpdate.do?what=P10101(POST)
 * 
 * "전자제품" 카테고리의 "삼성전자" 를 검색.
 *
 */
@Controller
public class BuyerRetrieveController{
	@Inject
	private BuyerService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@RequestMapping("/buyer/buyerList.do")
	public String buyerList(
		@ModelAttribute("detailCondition") BuyerVO detailCondition	
		, @RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, Model model
	){
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		service.retrieveBuyerList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "buyer/buyerList";
	}
	
	@RequestMapping("/buyer/buyerView.do")
	public String buyerView(
		@RequestParam(name="what", required=true) String buyerId
		, Model model
	){
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		
		return "buyer/buyerView";
	}
	
}
