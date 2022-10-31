package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{
	@Inject
	private BuyerService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@GetMapping
	public String buyerForm(){
		return "buyer/buyerForm";
	}
	
	@PostMapping
	public String buyerInsert(
		@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer	
		, Errors errors	
		, Model model
	){
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				viewName = "buyer/buyerForm";
				break;
			}
		}else {
			viewName = "buyer/buyerForm";
		}
		return viewName;
	}
	
}












