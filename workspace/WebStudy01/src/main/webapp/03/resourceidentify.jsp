<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URI"%>
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>03/resourceIdentify.jsp</title>
	</head>
	<body>
		<h4>자원의 종류와 식별 방법</h4>
		<pre>
			자원의 위치와 접근하기 위한 경로 표기 방법에 따른 분류
			1.	File System Resource : 파일 시스템의 특정 드라이브 아래, 파일시스템 절대 경로 형태( 드라이브 루트부터 시작 )
				D:\contents\images\cat1.jpg
				
			2.	Class path Resource : classpath 아래. classpath이후의 qualified name 형태 접근.
				\kr\or\ddit\images\cat2.jpg
				
				<%
					// 스크립트플릿의 코드 동작 단계
					/*
						1. 클래스로더가 Date라는 클래스를 찾아낸다음 클래스 메모리에 적재
						2. 그럼 그걸 본따서 instance( 객체 )가 생성되고
						   그 객체가 heap메모리에 적재가 됨
						3. 그 메모리 주소를 참조주소라 표현하고 today라는 변수가
						   그 주소를 참조한다.
						   
						   
						그래서 클래스로더가 그 클래스를 찾아낼수 있게 도와주는게 바로 
						JSP상단에 위치해있는 <%@ 안에 있는 코드이다.
					*/
					Date today = new Date();
			
					// 실제로 클래스 로더를 찾아보자.
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					URL cpResource = DescriptionServlet.class.getResource("../images/cat2.png");
					String cpPath = cpResource.toURI().getPath();
				%>
				
					--> <%= cpPath %>
					
				
			3.	Web Resource : web, URL 형태 접근.
				https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png
			
				\WebStudy01\resources\images\cat3.jpg
				<%
					URL webResource = application.getResource( "/WebStudy01/resources/images/cat3.jpg" );
				%>
	
			*** 웹상의 자원을 식별하기 위한 주소 체계
			URI : Unified Resource Identifier
			URL : Unified Resource Locator
			URN : Unified Resource Naming
			URC : Unified Resource Contents
	
	
			URL
			절대 경로 -> protocol://IP[ domain ]: port/contextName/depth..../resourceName
			
			client side :	/WebStudy01/resources/images/cat3.png
							이렇게 프로젝트명으로 쓰면 개발할땐 저렇게 했어도 배포할땐
							프로젝트 이름을 다르게 할 수 있음, 근데 저렇게 하드코딩으로
							해버리면 배포할때 그걸 싹다 고쳐줘야됨 그래서
							<%= request.getContextPath() %>/resources/images/cat3.png
							이런식으로 함.
							
			server side : context path 이후의 경로 / resources/images/cat3.png
			
		</pre>
		<img src="/resources/images/cat3.png">
		<img src="/WebStudy01/resources/images/cat3.png">
		<img src="//localhost/WebStudy01/resources/images/cat3.png">
		<img src="http://localhost/WebStudy01/resources/images/cat3.png">
		
		<img src="../resources">
		
	</body>
</html>