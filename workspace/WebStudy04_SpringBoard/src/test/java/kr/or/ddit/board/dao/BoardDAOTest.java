package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class BoardDAOTest {
	@Inject
	private BoardDAO dao;
	private PagingVO<BoardVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}

	@Test
	public void testSelectTotalRecord() {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		assertNotEquals(0, totalRecord);
		log.info("totalRecord : {}", totalRecord);
	}
	@Test
	public void testSelectBoardList() {
		List<BoardVO> boardList = dao.selectBoardList(pagingVO);
		assertNotNull(boardList);
		log.info("boardList : {}", boardList);
	}
	@Test
	public void testSelectBoard() {
		BoardVO board = dao.selectBoard(300);
		assertNotNull(board);
		log.info("board : {}", board);
	}

}
