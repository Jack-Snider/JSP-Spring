package kr.or.ddit.login.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.service.AuthenticationService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginProcessController{
	
	private final AuthenticationService service;
	@GetMapping("/login/loginForm.do")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login/loginProcess.do")
	public String loginProcess(
		String memId, String memPass, HttpSession session
		, RedirectAttributes redirectAttributes
	){
		
		String viewName = null;
		String message = null;
		if(StringUtils.isBlank(memId) || StringUtils.isBlank(memPass)) {
			message = "아이디나 비밀번호 누락";
			viewName = "redirect:/login/loginForm.do";
		}else {
			MemberVO inputData = new MemberVO();
			inputData.setMemId(memId);
			inputData.setMemPass(memPass);
			try {
				MemberVO authMember = service.authenticate(inputData);
				session.setAttribute("authMember", authMember);
				viewName = "redirect:/";
				
			}catch (UserNotFoundException | BadCredentialException e) {
				if(e instanceof UserNotFoundException) {
					message = "해당 회원 없음.";
				}else {
					message = "비밀번호 오류임.";
				}
				viewName = "redirect:/login/loginForm.do";
			}
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
//		1. encoding
//		2. 파라미터 획득
//		3. 검증
//		통과
//		4. 인증여부 확인
//			인증
//				5. welcome 페이지로 이동(redirect) - session scope를 통해 "authMember" 를 유지.
//			실패
//				5. loginForm 으로 이동(redirect) - 메시지 전달
//		불통
//		4. loginForm으로 이동(redirect) - 메시지 전달
	}
}











