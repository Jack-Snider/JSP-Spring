package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("who");
		
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400);
			return;
		}
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		req.setAttribute("command", "UPDATE");
		
		String commandPage = "/WEB-INF/views/member/memberForm.jsp";
		viewResolve(commandPage, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(member, errors);
		
		String commandPage = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case OK:
				commandPage = "redirect:/member/memberList.do";
				break;

			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				commandPage = "/WEB-INF/views/member/memberForm.jsp";
				break;
			}
		}else {
			commandPage = "/WEB-INF/views/member/memberForm.jsp";
		}
		viewResolve(commandPage, req, resp);
	}

	// Hibernate validator 
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId()) ) {
			errors.put("memId", "아이디 누락");
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			errors.put("memPass", "비밀번호 누락");
			valid = false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(member.getMemBir())) {
			try {
				sdf.parse(member.getMemBir());
			} catch (ParseException e) {
				errors.put("memBir", "날짜 형식 확인");
				valid = false;
			}			
		}
		return valid;
		
	}
}





















