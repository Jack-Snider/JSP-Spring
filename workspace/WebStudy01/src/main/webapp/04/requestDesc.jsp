<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h4>request : HttpServletRequest</h4>
		<pre>
		
			GET과 POST 방식의 명확한 차이는
			
			GET은 클라이언트가 서버로 요청을 보내고 답장을 받아야 할때 이다
			즉, 서버의 자원을 가져오는게 목적이다.
			
			POST는 클라이언트의 데이터를 서버에 보내기만 하는게 목적이다. ( 고지서같은 느낌 ) 
		
			Http 에 의해 패키징되는 요청의 구조
			
			1. Request Line : URL HttpMethod( Request Method ) Protocol / Versions
				Http Method : 요청의 목적( 동사, 행위 )이면서 동사에 패키징 구조 표현.
				GET ( R ) : 기본 METHOD ( 내가 서버에 요청을 보내고 응답을 받아야 할 때 )
				POST ( C ) : 서버에 요청만 보낼때 ( 회원가입, 로그인 등.. )
				PUT ( U ) : 수정 ( 일부 삭제 x , 전체 수정만 가능 )
				DELETE ( D ) : 삭제
				
				-OPTION- 
					preflight 요청 으로 본 요청의 method 지원여부를 확인
					
					모든 서버에는 GET과 POST방식을 지원하지만 그 외의 메소드는 지원을 할 수도 있고
					안할수도 있다. 그래서 해당 메소드의 지원여부를 알려주는 것이 OPTION이다.
					
				HEADER : 응답에 body가 없는 상태로 요청할 때 (  요청을 보냈는데 응답은 필요없고 내 요청이 잘 처리 되었는지만 확인할 때 )
				TRACE : 서버를 디버깅하기 위해 사용한다. 요청을 보냈을 때 서버가 정상적으로 처리를 했는지 등.. 
			
				
				<%= request.getRequestURI() %> <!-- 주소기져오기 -->
				<%= request.getMethod() %> <!-- 메소드 방식 -->
				<%= request.getProtocol() %> <!-- 프로토콜 -->
				
			
			2. Request Header : Metadata ( name / value - String )
			3. Request Body( Content[ Message ] Body, only POST ) : 전송 데이터 자체 영역
			   	parameter : 문자열로 전송
			   	part : 2진 데이터 전송 ( multipart -> 동시에 여러 형태의 데이터 전송 )
			   	
			   	<%
			   		if( "POST".equals( request.getMethod().toUpperCase() ) ){
				   		out.println( request.getInputStream().available() ); 
			   			out.println( request.getContentLength() );
			   			out.println( request.getContentType() );
			   		}
			   	%>
			   	
			   	
		</pre>
		
		<!-- form에서 액션 속성에 아무것도 없으면 자기 자신을 호출한 브라우저로 돌아감 -->
		<form method = "POST" enctype="multipart/form-data">
			<input type = "text" name = "param">
			<input type = "file" name = "filePart">
			<input type = "submit" value = "전송">
		</form>
		
		<h4>요청 헤더들</h4>
		<table>
			<thead>
				<tr>
					<th>헤더이름</th>
					<th>헤더값</th>
				</tr>
			</thead>
			 <tbody>
			<!--    <tr>
			           <td>Accept</td>
			           <td>text/html</td>
			        </tr>   -->
			        <%
			            StringBuffer trTag = new StringBuffer();
			            String pattern = "<tr><td>%s</td><td>%s</td></tr>";
			            
			            
			           // collection view            
			            Enumeration<String> en = request.getHeaderNames();
			        
			        
			            while(en.hasMoreElements()){
			           
			           	String headerName = en.nextElement();
			                                
			           	String headerValue = request.getHeader(headerName);
			           
			           	trTag.append(
			              	String.format(pattern, headerName, headerValue)      
			           	);
			        
			         }
			           
			         out.println(trTag);   
			        %>

	    	</tbody>
		</table>
		
	</body>
</html>