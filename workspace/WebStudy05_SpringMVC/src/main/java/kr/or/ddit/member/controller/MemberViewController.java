package kr.or.ddit.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberViewController{
	private final MemberService service;
	
	@RequestMapping("/member/memberView.do")
	public String memberView(
		@RequestParam(name="who", required=true) String memId
		, Model model
		, @RequestParam(required=false) String layout
	){
		MemberVO member = service.retrieveMember(memId);
		
		model.addAttribute("member", member);
		
		String viewName = null;
		
		if("GRID".equals(layout)) {
			viewName = "member/memberView";
		}else {
			viewName = "/member/memberView";
		}
		return viewName;
	}
}














