package kr.or.ddit.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.RequiredArgsConstructor;

/**
 * RESTful URI
 * /member (GET)
 * /member/a001 (GET)
 * /member/a001 (PUT)
 * /member/a001 (DELETE)
 * 
 * non-RESTful URI
 * /member/memberList.do (GET)
 * /member/memberView.do?who=a001 (GET)
 * /member/memberInsert.do (GET)
 * /member/memberInsert.do (POST)
 * /member/memberUpdate.do?who=a001 (GET)
 * /member/memberUpdate.do?who=a001 (POST)
 * /member/memberDelete.do?who=a001 (POST)
 *
 */
@RequiredArgsConstructor
@Controller
public class MemberListController{
	
	private final MemberService service;
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
		@ModelAttribute("simpleCondition") SearchVO simpleCondition
		, @RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, Model model
	){
		PagingVO<MemberVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
		
		return "member/memberList";
	}
}





















