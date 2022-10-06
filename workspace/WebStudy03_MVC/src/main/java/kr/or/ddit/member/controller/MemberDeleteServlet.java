package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("who");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400);
			return;
		}
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		ServiceResult result = service.removeMember(member);
		String commandPage = null;
		if(ServiceResult.OK.equals(result)) {
			commandPage = "redirect:/member/memberList.do";
		}else {
			req.getSession().setAttribute("message", memId + "삭제 처리 실패");
			commandPage = "redirect:/member/memberList.do";
		}
		viewResolve(commandPage, req, resp);
	}
	
	
	private void viewResolve(
			String commandPage, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(commandPage.startsWith("redirect:")) {
			commandPage = commandPage.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + commandPage);
		}else {
			req.setAttribute("commandPage", commandPage);
			String viewName = "/WEB-INF/views/template.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}



















