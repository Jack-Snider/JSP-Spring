<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 복습.do -->    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 
		<meta http-equiv="Refresh" content = "1">
		 -->
		<title>05/autoRequest</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
	</head>
	<body>
	
		<h4>현재 서버의 시간 :  <span id = "timeArea"></span> </h4>
		<pre>
			HTTP 특성
				- ConnectLess : request vs response ( 1: 1 ) 현재 요청에 대한 응답이 전송된 후 연결 종료.
				- StateLess : 연결이 종료되면서 양 피어에 저장된 정보들이 삭제됨.
				
				주기적인 자동 요청
				1. Server side : Refresh 해더
				2. Client side
					1) HTML meta : http-equiv( Refresh )
					2) JavaScript : Scheduling 함수
									timeout : 지연시간
									interval( * ) : 주기적 갱신
				
				
				<%
					/*
					1번째 인자 : 세팅할 해더
					2번째 인자 : 그 해더의 값
					setIntHeader() : 현재 존재하는 헤더의 값을 주어진 정수값으로 교체, 존재하지 않으면 헤더와 값을 추가
					Refresh의 단점 : 새로고침
					*/
					//response.setIntHeader( "Refresh" ,  1 ); // 질문 1 : 1의 데이터는 무슨 의미인지?
				%>
		</pre>
		<script type="text/javascript">
			let timeArea = $( "#timeArea" );
			setInterval( function(){
				timeArea.html( "" );
				//location
				// .reload(); // reload() : 현재 페이지를 갱신
				$.ajax({
					url : "<%= request.getContextPath()%>/getServerTimeServlet.html",
					method : "GET",
					data : "",
					dataType : "html",
					success : function( resp ){
						/*
						제이쿼리는 셀렉터를 찾기 위해 html문서 전체를 탐색한다.
						*/
						timeArea.html( resp );
					},
					error : function( errorResp ){
						console.log( errorResp.status );
					}
				});
				
			} , 1000 );
		</script>
	</body>
</html>