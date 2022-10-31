package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/prod")
public class OthersListController{
	@Inject
	private OthersDAO othersDAO;
	
	@RequestMapping("getLprodList.do")
	public String lprodList(Model model) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		model.addAttribute("model", lprodList);
		return "jsonView";
	}
	
	@RequestMapping("getBuyerList.do")
	public String buyerList(String lprodGu, Model model){
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu);
		model.addAttribute("model", buyerList);
		return "jsonView";
	}
}













