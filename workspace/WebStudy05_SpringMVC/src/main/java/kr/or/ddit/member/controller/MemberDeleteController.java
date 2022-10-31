package kr.or.ddit.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberDeleteController{
	
	private final MemberService service;
	
	@PostMapping("/member/memberDelete.do")
	public String memberDelete(
		@RequestParam(name="who", required=true) String memId
		, RedirectAttributes redirectAttributes
	){
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		ServiceResult result = service.removeMember(member);
		String viewName = null;
		if(ServiceResult.OK.equals(result)) {
			viewName = "redirect:/member/memberList.do";
		}else {
			redirectAttributes.addFlashAttribute("message", memId + "삭제 처리 실패");
			viewName = "redirect:/member/memberList.do";
		}
		return viewName;
	}
}



















