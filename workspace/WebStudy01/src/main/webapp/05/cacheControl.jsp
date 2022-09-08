<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h4>케시 제어</h4>
		<pre></pre>
	
		Cache-Control 
					
		Pgrama : HTTMP 1.
		Pragma : HHTP 1, 0
		Expires : HTTP 1.* , 캐시 데이터 만료 시간 설정
		<%
			response.setHeader( "Cache-Control", "no-store" );
			request.setHeader( "Pragma" , "no-store");
			requestsetDeleteHeader( "Expires", 0 );
		%>
	
	
	</body>
</html>