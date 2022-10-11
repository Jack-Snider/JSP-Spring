package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

@Controller
@RequestMapping( "/board" )
public class BoardRetrieveController {
	@Inject
	private BoardService service;
	
	@RequestMapping(value="boardList.do", method=RequestMethod.GET)
	public String list(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, Model model
	) {
		
		/*
		 * Model model
		 * 
		 * 여기서 model은 일종의 scope의 속성을 가지는 객체인데
		 * 범위는 request와 동일하다.
		 * 
		 * 
		 */
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
	
	@RequestMapping( "boardView.do" )
	public ModelAndView	 boardView(@RequestParam( name = "what", required = true ) int boNo ) {
		
		BoardVO board = service.retrieveBoard( boNo );
		ModelAndView mav = new ModelAndView();
		mav.addObject( "board", board );
		mav.setViewName( "board/boardList.do" );
		
		return mav;
	
	}
	
}











