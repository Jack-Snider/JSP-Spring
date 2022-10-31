package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String doGet(){
		return "member/memberForm";
	}
	
	@PostMapping
	public String doPost(
		@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
	) 
			throws ServletException, IOException {
		
		String logicalViewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				model.addAttribute("message", "아이디 중복");
				logicalViewName = "member/memberForm";
				break;
			case OK:
				logicalViewName = "redirect:/member/memberList.do";
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "member/memberForm";
				break;
			}
		}else {
			logicalViewName = "member/memberForm";
		}
		return logicalViewName;
	}

}





















