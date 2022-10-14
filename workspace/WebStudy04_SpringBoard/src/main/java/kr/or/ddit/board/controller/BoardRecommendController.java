package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.BoardService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BoardRecommendController {
	@Inject
	private BoardService service;

	@RequestMapping("/board/boardRecommend.do")
	public Map<String, Object> recommend(@RequestParam(name="what", required=true) int boNo) {
		Map<String, Object> marshallingTarget = new HashMap<>();
		int boRec = service.recommend(boNo);
		marshallingTarget.put("boRec", boRec);
		marshallingTarget.put("success", boRec > 0);
		return marshallingTarget;
	}
}
