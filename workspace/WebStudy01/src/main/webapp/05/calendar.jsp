<%@page import="java.util.TimeZone"%>
<%@page import="org.apache.tomcat.jni.Local"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="static java.util.Calendar.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 복습.do -->    
<%
   Locale locale = request.getLocale();

   String yearParam = request.getParameter("year");
   String monthParam = request.getParameter("month");
   String language = request.getParameter("language");
   
   Calendar calendar = getInstance();
   if(yearParam != null && yearParam.matches("\\d{4}")
      && monthParam != null && monthParam.matches("^1[0-1]$|^[0-9]$")){
      calendar.set(YEAR, Integer.parseInt(yearParam));
      calendar.set(MONTH, Integer.parseInt(monthParam));
   }
   
   if(language != null && !language.isEmpty()){
      locale = Locale.forLanguageTag(language);
   }

   String title = String.format(locale, "%1$tY, %1$tB", calendar);
   
   // 한달 전 년도, 월 구하기
   calendar.add(MONTH, -1);
   int beforeYear = calendar.get(YEAR);
   int beforeMonth = calendar.get(MONTH);
   
   // 한달 후 년도, 월 구하기
   calendar.add(MONTH, 2);
   int nextYear = calendar.get(YEAR);
   int nextMonth = calendar.get(MONTH);
   
   // 현재 달 복원
   calendar.add(MONTH, -1);
   int year = calendar.get(YEAR);
   int month = calendar.get(MONTH);
   
   calendar.set(DAY_OF_MONTH, 1);
   int dayOfWeek = calendar.get(DAY_OF_WEEK);
   int offset = dayOfWeek - 1;
   calendar.add(DAY_OF_MONTH, -offset);
   
   DateFormatSymbols dfs = new DateFormatSymbols(locale);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/calendar.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4>
<a href="#" class="changeBtn" data-year="<%=beforeYear %>" data-month="<%=beforeMonth%>">&lt;&lt;&lt;</a>
<%=title %>
<a href="#" class="changeBtn" data-year="<%=nextYear %>" data-month="<%=nextMonth %>">&gt;&gt;&gt;</a>
</h4>
<form name="calForm" method="post">
   <input type="text" name="year" pattern="\d{4}" placeholder="2022" value="<%=year%>"/>
   <select name="month">
      <%
         String[] months = dfs.getMonths();
         for(int idx=JANUARY;idx<=DECEMBER;idx++){
            out.println(String.format("<option value='%d'>%s</option>", idx, months[idx]));
         }
      %>
   </select>
   <select name="language">
      <%
         Locale[] locales = Locale.getAvailableLocales();
         for(Locale tmp : locales){
            String display = tmp.getDisplayLanguage(tmp);
            if(display.isEmpty()) continue;
            
            out.println(String.format("<option value='%s'>%s</option>"
                  ,tmp.toLanguageTag(), display));
         }
      %>
   </select>
   <select name="timeZoneId">
      <%
      %>
      <option value="Asia/Seoul">아시아/서울</option>
   </select>
</form>
<table>
   <thead>
      <tr>
         <%
         String[] weekDays = dfs.getShortWeekdays();
         for(int col=SUNDAY;col<=SATURDAY;col++){
            out.println(String.format("<th>%s</th>", weekDays[col]));
         }
         %>
      </tr>
   </thead>
   <tbody>
   <%
      for(int row=1;row<=6;row++){
         out.println("<tr>");
         for(int col=SUNDAY;col<=SATURDAY;col++){
            out.println(String.format("<td>%te</td>", calendar));
            calendar.add(DAY_OF_MONTH, 1);
         }
         out.println("</tr>");
      }
   %>
   </tbody>
</table>
<script type="text/javascript">
   let yearTag = $("[name=year]").val("<%=year%>");
   let monthTag = $("[name=month]").val("<%=month%>");
   let languageTag = $("[name=language]").val("<%=language%>");
   
   $(".changeBtn").on("click", function(event){
      event.preventDefault();
      let year = $(this).data("year");
      let month = $(this).data("month");
      yearTag.val(year);
      monthTag.val(month);
      calForm.submit();
      return false;
   });
   
   let calForm = $(document.calForm).on("change",":input[name]",function(event){
      //event.target == this
      //this.form.submit(); // submit 이벤트는 발생하지 않는다.
      this.form.requestSubmit(); // submit 이벤트 발생
   });
</script>
</body>
</html>