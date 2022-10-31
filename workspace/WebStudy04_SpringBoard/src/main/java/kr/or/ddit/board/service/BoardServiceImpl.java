	package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
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
//			if(1==1) throw new RuntimeException("트랜잭션 관리 여부 확인용 강제 예외 발생");
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
	
	private void encryptBoard(BoardVO board) {
		String plain = board.getBoPass();
		String encoded = passwordEncoder.encode(plain);
		board.setBoPass(encoded);
	}
	
	@Transactional // 선언적 프로그래밍 기법(AOP)
	@Override
	public ServiceResult createBoard(BoardVO board) {
		//===========암호화==========
		encryptBoard(board);
		//=========================
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
		String savedPass = saved.getBoPass(); // 암호화되어 저장된 비밀번호
		return passwordEncoder.matches(inputPass, savedPass);
	}
	
	@Transactional
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		ServiceResult result = null;
		if(boardAuthenticate(board)) {
			int rowcnt = boardDAO.updateBoard(board);
			// 1. 신규파일등록  : 2진, 메타데이타
			rowcnt += processAttatchList(board);
			// 2. 기존 파일 삭제 : 2진, 메타데이타
			processDeleteAttatch(board);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	private int processDeleteAttatch(BoardVO board) {
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos==null || delAttNos.length == 0) return 0;
		Arrays.sort(delAttNos);
 		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
		List<String> saveNames = attatchList.stream()
				.filter(attatch->{
					return Arrays.binarySearch(delAttNos, attatch.getAttNo()) >= 0;
				}).map(attatch->{
					return attatch.getAttSavename();
				}).collect(Collectors.toList());
		int rowcnt = attatchDAO.deleteAttatches(delAttNos);
		// 2진 데이터 삭제
		if(!saveNames.isEmpty()) {
			for(String attSavename : saveNames) {
				File deleteFile = new File(saveFolder, attSavename);
				FileUtils.deleteQuietly(deleteFile);
			}
		}
		return rowcnt;
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
	
	@Transactional
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
	
	@Override
	public AttatchVO retrieveAttatch(int attNo) {
		AttatchVO attatch = attatchDAO.selectAttatch(attNo);
		if(attatch == null)
			throw new RuntimeException("해당 파일 없음.");
		return attatch;
	}
}


















