package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan.do")
public class GugudanServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// String timeText = String.format( "%tc", Calendar.getInstance() ); //
		// formatting
		// req.setAttribute( "timeText" , timeText);

		StringBuffer gugu = new StringBuffer();

		String minDan = req.getParameter("minDan");
		String maxDan = req.getParameter("maxDan");

		int min = 0;
		int max = 0;

		// null체크와 isEmpty() 체크
		if ((minDan == null && maxDan == null) || (minDan.isEmpty() && maxDan.isEmpty())) {
			min = 2;
			max = 9;
		} else {

			// 파라미터가 정말 숫자가 맞는지에 대한 검증 ( 정규표현식 사용 )
			if (minDan.matches("[2-9]") && maxDan.matches("[2-9]")) {
				min = Integer.parseInt(minDan);
				max = Integer.parseInt(maxDan);
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "2단부터 9단까지 범위내 파라미터만 가능");
			}

		}

		for (int i = min; i <= max; i++) {
			gugu.append("<tr>");
			for (int j = 1; j <= 9; j++) {
				gugu.append(String.format("<td>%d * %d = %d</td>", i, j, i * j));
			}
			gugu.append("</tr>");
		}

		StringBuffer tmp = new StringBuffer();
		for (int i = 2; i <= 9; i++) {
			tmp.append(String.format("<option value = %d > %d 단</option>", i, i));
		}

		/*
		 	getHeader() 메소드를 통해 클라이언트의 다양한 정보를 습득할 수 있다.
		 	
		 	동기( Synchronous ) :	요청을 보낸 후 응답( 결과물 )을 받아야 다음 동작이
		 						이루어지는 방식을 말한다.
		 						모든 일은 순차적으로 실행되며 어떤 작업이 수행중이라면 다음 작업은
		 						대기하게 된다.
		 						
		 						요약 : 요청과 응답이 동시에 받는다는 의미
		 	
		 	비동기( Asynchronous ) :	동시에 일어나지 않는다는 의미로 요청과 결과가 동시에
		 							일어나지 않을거라는 약속이다. 요청과 응답이 다른 시간대 존재하기
		 							때문에, 요청내용에 대해 지금 바로 혹은 당장 응답받지 않아도 된다.
		 							( 바로 응답이 와도 되고 )
		 	
		 	
		 */
		String header = req.getHeader("X-Requested-with");

		if ("XMLHttpRequest".equals(header)) {

			// try문 안에서 객체를 만들어버리면 객체를 close() 해주지 않아도 된다.
			try ( PrintWriter out = resp.getWriter(); ) {
				out.println( gugu );
			}

		} else {
			req.setAttribute("gugudan", gugu);
			req.setAttribute("options", tmp);
			req.setAttribute("minDan", min);
			req.setAttribute("maxDan", max);

			String viewLayer = "/WEB-INF/views/tmpl/gugudan.tmpl";
			
			
			req.getRequestDispatcher(viewLayer).forward(req, resp);
		}

	}

}
