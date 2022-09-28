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

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("command", "INSERT");
		String logicalViewName = "member/memberForm";
		viewResolve(logicalViewName, req, resp);
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
		
		String logicalViewName = null;
		if(valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				logicalViewName = "member/memberForm";
				break;
			case OK:
				logicalViewName = "redirect:/member/memberList.do";
				break;

			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "member/memberForm";
				break;
			}
		}else {
			logicalViewName = "member/memberForm";
		}
		viewResolve(logicalViewName, req, resp);
	}

	// Hibernate validator 
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			errors.put("memId", "회원아이디누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			errors.put("memPass", "비밀번호누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemName())) {
			errors.put("memName", "회원명누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			errors.put("memZip", "우편번호누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			errors.put("memAdd1", "주소1누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			errors.put("memAdd2", "주소2누락");
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			errors.put("memMail", "이메일누락");
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





















