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

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

//@WebServlet(value = "/bts/getContent", loadOnStartup = 1)
@Controller
public class BTSController{
	@Inject
	private WebApplicationContext context;
	
	private ServletContext application;
	
	@PostConstruct
	public void init() throws ServletException {
		application = context.getServletContext();
		
		Map<String, String[]> btsDB = new LinkedHashMap<>();
		btsDB.put("B001", new String[] {"RM", "bts/rm"});
		btsDB.put("B002", new String[] {"진", "bts/jin"});
		btsDB.put("B003", new String[] {"슈가", "bts/suga"});
		btsDB.put("B004", new String[] {"제이홉", "bts/jhop"});
		btsDB.put("B005", new String[] {"지민", "bts/jimin"});
		btsDB.put("B006", new String[] {"뷔", "bts/bui"});
		btsDB.put("B007", new String[] {"정국", "bts/jungkuk"});
		
		application.setAttribute("btsDB", btsDB);
	}
	
	@RequestMapping("/bts/form")
	public String btsForm() {
		return "btsForm";
	}
	
	@RequestMapping("/bts/getContent")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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


















