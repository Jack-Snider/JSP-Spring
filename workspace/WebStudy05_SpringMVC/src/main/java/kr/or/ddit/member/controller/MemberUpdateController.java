package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String command() {
		return "UPDATE";
	}
	
	@GetMapping
	public String doGet(
		@RequestParam(name="who", required=true) String memId
		, Model model
	){
		
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
		return "member/memberForm";
	}
	
	@PostMapping
	public String doPost(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
		, BindingResult errors
		, Model model
	) {
		String logicalViewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
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





















