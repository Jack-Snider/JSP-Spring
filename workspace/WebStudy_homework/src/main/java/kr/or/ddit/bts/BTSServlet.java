package kr.or.ddit.bts;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/bts/getContent", loadOnStartup = 1)
public class BTSServlet extends HttpServlet {
   private ServletContext application;
//    
//      1. 서블릿의 map에 담긴 정보를 jsp 페이지에서 사용할 수 있어야한다.
//      2. 서블릿으로 넘어오는 데이터 검증
//      3. init()에 선언된 map을 scope에 담아야됨
//      4. 데이터 검증이 끝나면 컨텐츠를 webinf안에 페이지로 이동시켜야함.
//    
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      application = getServletContext();
      
      Map<String, String[]> btsDB = new LinkedHashMap<String, String[]>();
      btsDB.put("B001", new String[] { "RM", "/WEB-INF/views/bts/rm.jsp" });
      btsDB.put("B002", new String[] { "뷔", "/WEB-INF/views/bts/bui.jsp" });
      btsDB.put("B003", new String[] { "제이홉", "/WEB-INF/views/bts/jhop.jsp" });
      btsDB.put("B004", new String[] { "지민", "/WEB-INF/views/bts/jimin.jsp" });
      btsDB.put("B005", new String[] { "정국", "/WEB-INF/views/bts/jungkuk.jsp" });
      btsDB.put("B006", new String[] { "슈가", "/WEB-INF/views/bts/suga.jsp" });
      btsDB.put("B007", new String[] { "진", "/WEB-INF/views/bts/jin.jsp" });
      
      application.setAttribute("btsDB", btsDB);
   }
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      Map<String, String[]>btsDB = (Map) application.getAttribute("btsDB");
      
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
         req.getRequestDispatcher(contentURL).forward(req, resp);
      }else {
         resp.sendError(status);
      }
   }
}