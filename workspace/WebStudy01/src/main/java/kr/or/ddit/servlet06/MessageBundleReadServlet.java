package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Servlet implementation class MessageBundleReadServlet
 */
@WebServlet("/getMessage")
public class MessageBundleReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1) 메시지 번들 로딩(ResourceBundle)
//		2) "hello"코드 메시지 확보
//		3) 확보한 메시지를 컨텐츠화(Accept 헤더에 따라 Content-Type 결정).
		Locale locale = request.getLocale();
		request.setCharacterEncoding("UTF-8");
		String languageTag = request.getParameter("language");
		if(languageTag!=null && !languageTag.isEmpty()) {
			locale = Locale.forLanguageTag(languageTag);
		}
		
		String baseName = "egovframework/message/com/message-common";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		String value = bundle.getString("hello");
	
//		Map<String, Object> model = Collections.singletonMap("hello", value);
		String accept = request.getHeader("Accept");
//		String contentType = null;
		
		request.setAttribute("hello", value);
		
		String viewName = null;
		
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		}else if(accept.contains("xml")) {
			viewName = "/xmlView.do";
		}else {
			viewName = "/WEB-INF/views/messageView.jsp";
		}
		
		request.getRequestDispatcher(viewName).forward(request, response);	
	}


}













