package kr.or.ddit.board.event;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardEventListener {
	
	@Resource(name="allWsSessions")
	private List<WebSocketSession> allWsSessions;
	
	@EventListener(NewBoardEvent.class)
	public void newBoardEventListener(NewBoardEvent event) throws IOException {
		BoardVO newBoard = event.getSource();
		log.info("{} 가 작성한 새글 , {} 제목으로 올라왔음.", newBoard.getBoWriter(), newBoard.getBoTitle());
		// WebSocket 연결이 수립되어 있는 모든 사용자에게 푸시 메시지 전송.
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(newBoard);
		TextMessage message = new TextMessage(jsonPayload);
		
		for(WebSocketSession singleSession : allWsSessions) {
			singleSession.sendMessage(message);
		}
	}
}


















