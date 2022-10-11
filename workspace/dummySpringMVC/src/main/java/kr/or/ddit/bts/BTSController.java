package kr.or.ddit.bts;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

//@WebServlet(value = "/bts/getContent", loadOnStartup = 1)
@Controller
public class BTSController{

	@Inject
	private WebApplicationContext context;
	// ApplicationContext는 컨테이너중에서 최상위 객체이지만
	// 앞에 Web을 붙히면 Web 전용으로 쓸 수 있는 객체가 된다.
	
	private ServletContext application;
	
	@RequestMapping( "/bts/form" )
	public String btsForm() {
		return "btsForm";
	}
	
	@PostConstruct // 생성자가 객체를 생성한 후에 ( Lifecycle call back은 모든 injection이 완료된 후에 수행된다. )
	public void init() throws ServletException {
		
		application = context.getServletContext();
		
		Map<String, String[]> btsDB = new LinkedHashMap<>();
		btsDB.put("B001", new String[] {"RM", "bts/rm"});
		btsDB.put("B002", new String[] {"진", "/WEB-INF/views/bts/jin.jsp"});
		btsDB.put("B003", new String[] {"슈가", "/WEB-INF/views/bts/suga.jsp"});
		btsDB.put("B004", new String[] {"제이홉", "/WEB-INF/views/bts/jhop.jsp"});
		btsDB.put("B005", new String[] {"지민", "/WEB-INF/views/bts/jimin.jsp"});
		btsDB.put("B006", new String[] {"뷔", "/WEB-INF/views/bts/bui.jsp"});
		btsDB.put("B007", new String[] {"정국", "/WEB-INF/views/bts/jungkuk.jsp"});
		
		application.setAttribute("btsDB", btsDB);
	}
	

	@RequestMapping( "/bts/getContent" )
	protected String doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		
		Map<String, String[]> btsDB =  (Map) application.getAttribute("btsDB");
		
		req.setCharacterEncoding("UTF-8");
		String member = req.getParameter("member");
		int status = 200;
		if(member==null && member.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(!btsDB.containsKey(member)) {
			status = HttpServletResponse.SC_NOT_FOUND;
		}
		
		if(status==200) {
			String[] info = btsDB.get(member);
			String contentURL = info[1];
			return contentURL;
//			req.getRequestDispatcher(contentURL).forward(req, resp);
		}else {
			resp.sendError(status);
			return null;
		}
	}
}


















