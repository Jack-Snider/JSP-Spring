	package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDAO boardDAO;
	private final AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchFolder}")
	private Resource attatchFolder;
	
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		saveFolder = attatchFolder.getFile();
	}
	
	private int processAttatchList(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			// 메타데이터 저장
			rowcnt = attatchDAO.insertAttatches(board);
			// 2진 데이터 저장
			for(AttatchVO attatch : attatchList) {
				try {
					attatch.saveTo(saveFolder);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return rowcnt;
	}
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		int rowcnt = boardDAO.insertBoard(board);  // 1.
		rowcnt += processAttatchList(board);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		BoardVO board = boardDAO.selectBoard(boNo);
		if(board==null)
			throw new RuntimeException(String.format("%d 글번호의 글이 없음.", boNo));
		boardDAO.incrementBoHit(boNo);
		return board;
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectTotalRecord(pagingVO);
	}

	private boolean boardAuthenticate(BoardVO board) {
		BoardVO saved = retrieveBoard(board.getBoNo());
		String inputPass = board.getBoPass();
		String savedPass = saved.getBoPass();
		return savedPass.equals(inputPass);
	}
	
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		ServiceResult result = null;
		if(boardAuthenticate(board)) {
			int rowcnt = boardDAO.updateBoard(board);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	private int processDeleteAttatches(BoardVO board) {
		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
		
		if(attatchList==null || attatchList.isEmpty()) return 0;
		
		List<String> saveNames = attatchList.stream()
					.map(attatch->{
						return attatch.getAttSavename();
					}).collect(Collectors.toList());
		int rowcnt = 0;
		// 메타데이터 삭제
		rowcnt = attatchDAO.deleteAttathes(board.getBoNo());
		// 2진 데이터 삭제
		if(!saveNames.isEmpty()) {
			for(String attSavename : saveNames) {
				File deleteFile = new File(saveFolder, attSavename);
				FileUtils.deleteQuietly(deleteFile);
			}
		}
		return rowcnt;
	}
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		ServiceResult result = null;
		if(boardAuthenticate(board)) {
			processDeleteAttatches(board);
			int rowcnt = boardDAO.deleteBoard(board);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}
	
	@Override
	public int recommend(int boNo) {
		boardDAO.incrementRec(boNo);
		return boardDAO.selectBoard(boNo).getBoRec();
	}
}


















