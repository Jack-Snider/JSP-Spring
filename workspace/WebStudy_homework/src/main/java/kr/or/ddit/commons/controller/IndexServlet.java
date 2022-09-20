package kr.or.ddit.commons.controller;

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

@WebServlet("/index.do")
public class indexServlet extends HttpServlet {
   
   private ServletContext application;
   
   @Override
   public void init(ServletConfig config) throws ServletException { // LifeCycle CallBack
      super.init(config);
      application = getServletContext();
      Map<String, String> commandDB = new LinkedHashMap<>();
      commandDB.put("CALENDAR", "/05/calender.jsp");
      commandDB.put("FACTORIAL", "/02/factorial.jsp");
      commandDB.put("CALCULATOR", "/02/calculateForm.jsp");
      application.setAttribute("commandDB", commandDB);
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Map<String, String> commandDB = (Map)application.getAttribute("commandDB");
      
      String command = req.getParameter("command");
      String commandPage = null;
      
      int status = 200;
      if(command==null || command.isEmpty()) { // isEmpty() -> 문자열의 길이가 0일때 True를 반환한다.
         commandPage = "/WEB-INF/views/index.jsp";
      }else {
         if(!commandDB.containsKey(command)) {
            status = HttpServletResponse.SC_NOT_FOUND;
         }
         commandPage = commandDB.get(command);
      }
      if(status==200) {
         req.setAttribute("commandPage", commandPage);   // application scope가 아니라 request scope를 쓰는 이유 ? -> 서버의 과부화를 최소화하기 위해서이다.
         String viewName = "/WEB-INF/views/template.jsp";
         req.getRequestDispatcher(viewName).forward(req, resp);
      }else {
         resp.sendError(status);
      }
   }
}