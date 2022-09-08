<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%!
	public static String temp = "TEMP";
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>02/standard.jsp</title>
	</head>
	<body>
		
		<h4>JSP( Java Server Page )</h4>
		<%= new Date() %>
		<%
			
		%>
		<pre>
			:	웹상에서 랜더링할 웹 페이지를 생성하기 위한 스펙, script, 토큰의 종유가 여러가지.
				표준 JSP 구성 요소
				1. 정적 테스트
					HTML, CSS, JS, text
				2. 스크립틀릿
				 	1) directive( 지시자 ) : JSP 페이지에 대한 기본 설정 정보 ( page - requesred ). &ls;%@ %&gt;
				 		include : 정적 내포,
				 		
				 	<!-- 스크립틀릿 <%%> 안에 들어가는 모든 변수들은 지역변수의 역할을 한다. -->	
				 	2) scriptlet : <%  // java code %>
				 		<%
				 			// 지역 코드 _JSPService
				 			String temp = "TEMP";
				 		%> 
				 	3) expression( 표현식 ) : &lt;%= 출력할 값이나 연산식. %&gt;
				 	4) declartion( 선언부 ) : <%! private void test(){} private String tx; %>
				 	5) comment( 주석 ) <%-- --%>
				 		- client side comment : HTML, CSS, J -->
				 		<!-- HTML comment --> 		
				 		- server side comment : java, JSP
				 		<!-- JSO comment-->
				 
				 3. 기본 객체
				 4. action tag 
				 5. EL( Expression Language , 표현 언어 )
				 6. JSTL( JSP StandardTagLibrary )
		</pre>
	</body>
</html>