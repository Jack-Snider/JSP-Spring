<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
</head>
<body>
   <h4>UserAgent</h4>
   당신의 브라우저는 "크롬[엣지, 사파리, 익스플로러]"입니다.
   
   <%
       
      /* String userAgent = request.getHeader("User-Agent").toLowerCase();
      
      Map<String, String> agentMap = new LinkedHashMap<>();
      agentMap.put("trident", "IE");
      agentMap.put("edg", "엣지");
      agentMap.put("chrome", "크롬");
      agentMap.put("safari", "사파리");
      agentMap.put("firefox", "파이어폭스");
      agentMap.put("other", "기타"); 
      
      String browserName = agentMap.get("other");
      for(Entry<String, String> entry : agentMap.entrySet()){
         if(userAgent.indexOf(entry.getKey())>-1){
            browserName = entry.getValue();
            break;
         }
      } */ 
      
      
      // enum을 썼을 때 : 책임의 분리화 -> JSP는 최대한 VIEW 역할만 ... 
      String browserName = BrowserType.searchBrowserName(request);
      out.println(String.format("<br>당신의 브라우저는 '%s'입니다.", browserName));
   
   %>
   
</body>
</html>