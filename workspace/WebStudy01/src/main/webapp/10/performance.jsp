<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Model1 구조를 이용하여, -->
<!-- 'a001' 사용자의 이름을 조회하라. -->

<%
	String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = ?";
	String memName = null;
	
	long currTime = System.currentTimeMillis();
	long endTime = 0;
	double runTime = 0;
	for(int i=1; i<=100; i++){
			try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
					pstmt.setString(1, "a001");
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()){
						memName = rs.getString("MEM_NAME");
					}
			}
	} // for end
	endTime = System.currentTimeMillis();
	runTime = endTime - currTime; 
%>
<h4>'a001' 멤버 이름 : <%=memName %></h4>
<h4>데이터 조회에 걸린 시간 : <%=runTime %>ms</h4>

<h4>전체 소요 시간(response time) 확인</h4>
<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력 : 8 ms</h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 700 ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 10 ms</h4>
<hr />
<h4> connection pooling 이후 </h4>
<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력 : ? ms</h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 10 ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 5 ms</h4>
</body>
</html>













