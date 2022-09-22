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

@WebServlet( "/member/memberInsert.do" )
public class MemberInsertServlet extends HttpServlet {

	private MemberService service = new MemberServiceImpl();
	
	private void viewResolve( String commandPage, HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		
		if( commandPage.startsWith( "redirect:" ) ) {
			commandPage = commandPage.substring( "redirect:".length() );
			resp.sendRedirect( req.getContextPath() +  commandPage );
		}else {
			req.setAttribute( "commandPage" , commandPage );
			String viewName = "/WEB-INF/views/template.jsp";
			req.getRequestDispatcher( viewName ).forward( req, resp );			
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String commandPage = "/WEB-INF/views/member/memberForm.jsp";
		viewResolve( commandPage, req, resp );
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding( "UTF-8" );
		
		MemberVO member = new MemberVO();
		req.setAttribute( "member" , member );
		
		// Reflection 기술로 MemberVO 클래스의 setter를 알아서 찾아서 
		// req.getParameterMap() 으로 입력한 값 가져온다음 다 넣어줌
		try {
			BeanUtils.populate( member, req.getParameterMap() );
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException( e );
		}
		
		
		Map<String, String> errors = new HashMap<>();
		req.setAttribute( "errors" , errors);
		
		boolean valid = validate( member, errors );
		
		// 검증단계
		
		String commandPage = null;
		if( valid ) {
			ServiceResult result = service.createMember( member );
			switch( result ) {
			case PKDUPLICATED:
				req.setAttribute( "message" , "아이디 중복" );
				commandPage = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case OK:
				commandPage = "redirect:/member/memberList.do";
				break;		
			default:
				req.setAttribute( "message" , "서버 오류, 쫌따 다시 하셈" );
				commandPage = "/WEB-INF/views/member/memberForm.jsp";
				break;
			}
		}else {
			commandPage = "/WEB-INF/views/member/memberForm.jsp";
		}
		
		viewResolve( commandPage, req, resp );			
		
//		member.setMemId( req.getParameter( "mem_id" ) );
//		member.setMemPass( req.getParameter( "mem_pass" ) );
//		member.setMemName( req.getParameter( "mem_name" ) );
//		member.setMemRegno1( req.getParameter( "mem_regno1" ) );
//		member.setMemRegno2( req.getParameter( "mem_regno2" ) );
//		member.setMemBir( req.getParameter( "mem_bir" ) );
//		member.setMemZip( req.getParameter( "mem_zip" ) );
//		member.setMemAdd1( req.getParameter( "mem_add1" ) );
//		member.setMemAdd2( req.getParameter( "mem_add2" ) );
//		member.setMemHometel( req.getParameter( "mem_hometel" ) );
//		member.setMemComtel( req.getParameter( "mem_comtel" ) );
//		member.setMemHp( req.getParameter( "mem_hp" ) );
//		member.setMemMail( req.getParameter( "mem_mail" ) );
//		member.setMemJob( req.getParameter( "mem_job" ) );
//		member.setMemLike( req.getParameter( "mem_like" ) );
//		member.setMemMemorial( req.getParameter( "mem_memorial" ) );
//		member.setMemMemorialday( req.getParameter( "mem_memorialday" ) );
//		member.setMemMileage( 0 );
//		member.setMemDelete( "0" );
		
			
	}

	// Hibernate validator 객체검증 프레임워크를 추후 사용예정 ( 서블릿에서 이걸 하기엔 책임이 너무 많아~ )
	private boolean validate( MemberVO member, Map<String, String> errors ) {
		
		boolean valid = true;
		if( StringUtils.isBlank( member.getMemId() ) ) {
			errors.put( "memId", "아이디 누락" );
			valid = false;
		}
		
		if( StringUtils.isBlank( member.getMemPass() ) ) {
			errors.put( "memPass", "비밀번호 누락" );
			valid = false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		if( StringUtils.isNotBlank( member.getMemBir() ) )  {
			try {
				sdf.parse( member.getMemBir() );
			} catch (ParseException e) {
				errors.put( "memBir", "날짜 형식 확인" );
				valid = false;
			}
		}
		
		return valid;
	}
	
}
