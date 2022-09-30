package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * RESTful URI
 * /member (GET)
 * /member/a001 (GET)
 * /member/a001 (PUT)
 * /member/a001 (DELETE)
 * 
 * non-RESTful URI
 * /member/memberList.do (GET)
 * /member/memberView.do?who=a001 (GET)
 * /member/memberInsert.do (GET)
 * /member/memberInsert.do (POST)
 * /member/memberUpdate.do?who=a001 (GET)
 * /member/memberUpdate.do?who=a001 (POST)
 * /member/memberDelete.do?who=a001 (POST)
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO simpleCondition = new SearchVO(searchType, searchWord);
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
//		req.setAttribute("memberList", memberList);
		req.setAttribute("pagingVO", pagingVO);
		
		String viewName = "/member/memberList.tiles";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}





















