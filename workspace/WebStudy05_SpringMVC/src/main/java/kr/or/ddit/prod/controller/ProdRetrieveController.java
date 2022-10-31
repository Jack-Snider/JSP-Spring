package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * /prod/prodList.do(GET)
 * /prod/prodView.do?what=P101000001(GET)
 * /prod/prodInsert.do(GET)
 * /prod/prodInsert.do(POST)
 * /prod/prodUpdate.do?what=P101000001(GET)
 * /prod/prodUpdate.do(POST)
 *
 */
@Controller
@RequestMapping("/prod")
public class ProdRetrieveController{
	
	@Inject
	private ProdService service;

	@RequestMapping("prodList.do")
	public String processHTML(){
		return "prod/prodList";
	}
	
	@RequestMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String processJsonData( 
		@ModelAttribute("detailCondition") ProdVO detailCondition
		, @RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, Model model
	){
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(7, 5);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		model.addAttribute("pagingVO", pagingVO);
		return "jsonView";
	}
	
	@RequestMapping("prodView.do")
	public String prodView(
		@RequestParam(required=true) String what
		, Model model
	){
		ProdVO prod = service.retrieveProd(what);
		model.addAttribute("prod", prod);
		
		return "prod/prodView";
	}
}























