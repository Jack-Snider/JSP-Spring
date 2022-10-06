package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.service.AuthentiationServiceImpl;
import kr.or.ddit.login.service.AuthenticationService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet{
	
	private AuthenticationService service = new AuthentiationServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		HttpSession session = req.getSession(); 
				
		String viewName = null;
		String message = null;
		if(StringUtils.isBlank(memId) || StringUtils.isBlank(memPass)) {
			message = "아이디나 비밀번호 누락";
			viewName = "redirect:/login/loginForm.jsp";
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
				viewName = "redirect:/login/loginForm.jsp";
			}
		}
		
		session.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
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











