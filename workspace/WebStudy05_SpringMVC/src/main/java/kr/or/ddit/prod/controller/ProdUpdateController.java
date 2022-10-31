package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController{
	@Inject
	private ProdService service;
	
	@ModelAttribute("command")
	public String command() {
		return "UPDATE";
	}
	
	
	@GetMapping
	public String doGet(@RequestParam(required=true) String what, Model model){
		
		ProdVO prod = service.retrieveProd(what);
		
		model.addAttribute("prod", prod);
		
		return "prod/prodForm";
	}
	
	@PostMapping
	public String doPost(
	 	@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
	 	, Errors errors
	 	, Model model
	){
		String logicalViewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
				logicalViewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "prod/prodForm";
				break;
			}
		}else {
			logicalViewName = "prod/prodForm";
		}
		return logicalViewName;
	}
	
}












