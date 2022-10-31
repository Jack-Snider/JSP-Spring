package kr.or.ddit.member.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Controller
public class MyPageController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/mypage.do")
	public String myPage(
		@SessionAttribute(required=true) MemberVO authMember
		, Principal principal
		, MemberVOWrapper wrapper
		, Model model
	){
  		MemberVO member = wrapper.getRealUser();
  		model.addAttribute("member", member);
 		return "member/mypage";
	}
	
}
