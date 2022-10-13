package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.DeleteGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardDeleteController {
	private final BoardService service;
	
	@PostMapping("/board/boardDelete.do")
	public String boardDelete(
		@Validated(DeleteGroup.class) BoardVO board
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case INVALIDPASSWORD:
				message = "비번 오류";
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			case OK:
				viewName = "redirect:/board/boardList.do";
				break;
			default:
				message = "서버 오류";
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			}
		}else {
			message = "누락 데이터";
			viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}






