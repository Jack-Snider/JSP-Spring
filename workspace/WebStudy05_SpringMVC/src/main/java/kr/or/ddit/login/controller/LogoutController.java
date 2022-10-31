package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController{
	@PostMapping("/login/logout.do")
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return null;
		}
		session.invalidate();
		
		return "redirect:/";
	}
}
