<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

StringBuffer gugu = new StringBuffer();

String minDan = request.getParameter("minDan");
String maxDan = request.getParameter("maxDan");

int min = 0;
int max = 0;

// null체크와 isEmpty() 체크
if ((minDan == null && maxDan == null) || (minDan.isEmpty() && maxDan.isEmpty())) {
	min = 2;
	max = 9;
} else {

	// 파라미터가 정말 숫자가 맞는지에 대한 검증 ( 정규표현식 사용 )
	if (minDan.matches("[2-9]") && maxDan.matches("[2-9]")) {
		min = Integer.parseInt(minDan);
		max = Integer.parseInt(maxDan);
	} else {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "2단부터 9단까지 범위내 파라미터만 가능");
	}

}

for (int i = min; i <= max; i++) {
	gugu.append("<tr>");
	for (int j = 1; j <= 9; j++) {
		gugu.append(String.format("<td>%d * %d = %d</td>", i, j, i * j));
	}
	gugu.append("</tr>");
}

StringBuffer tmp = new StringBuffer();
for (int i = 2; i <= 9; i++) {
	tmp.append(String.format("<option value = %d > %d 단</option>", i, i));
}

/*
 	getHeader() 메소드를 통해 클라이언트의 다양한 정보를 습득할 수 있다.
 	
 	동기( Synchronous ) :	요청을 보낸 후 응답( 결과물 )을 받아야 다음 동작이
 						이루어지는 방식을 말한다.
 						모든 일은 순차적으로 실행되며 어떤 작업이 수행중이라면 다음 작업은
 						대기하게 된다.
 						
 						요약 : 요청과 응답이 동시에 받는다는 의미
 	
 	비동기( Asynchronous ) :	동시에 일어나지 않는다는 의미로 요청과 결과가 동시에
 							일어나지 않을거라는 약속이다. 요청과 응답이 다른 시간대 존재하기
 							때문에, 요청내용에 대해 지금 바로 혹은 당장 응답받지 않아도 된다.
 							( 바로 응답이 와도 되고 )
 	
 	
 */
String header = request.getHeader("X-Requested-with");

if ("XMLHttpRequest".equals(header)) {

	// try문 안에서 객체를 만들어버리면 객체를 close() 해주지 않아도 된다.
	
	out.println( gugu );
	return;

} 


%>    
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

</head>
<body>
	<h4>구구단 ( 8 * 9 )</h4>
	2단 부터 9단까지 구구단 랜더링. 출력되는 구구단 text 형식 : "2 * 2 = 4"
	
	<!-- form태그에서 action이 없다면 자기 자신이 사용하고
		 있는 브라우저에서 그대로 사용한다. 
	-->
	<form name="gugudanForm">
			최소단위 : <input id="minDan" type="number" name="minDan" min="2" max="9" required value="<%= min%>"> 
			최대단위 : <select name="maxDan" required>
						<option value><%= max %>단</option> 
						
						
						<%=tmp %>
						
						
			
					</select> 
					<input type="submit" value="전송">
	</form>

	
	<table id = "gugudanTable">
		<%=gugu%>
	</table>

	<script type="text/javascript">
		
			//document.gugudanForm.maxDan.value = "#maxDan#";
			$( "[name = 'maxDan']" ).val( "<%= max%>" );
			$( "form:first" ).on( "submit", function( event )
					//event.preventDefualt()
					//console.log( this );
					//console.log( event.target );
					//console.log( $(this) ).attr( "name" );
					
					let url = this.action;
					let method = this.method;
					let data = $( this ).serialize(); // query string 생성
					
					// 기본적인 ajax 고정형태 ( 이 형태에서 많이 안 바뀜 )
 					$.ajax({
	
						url : url,
					 	date : data,
						dataType : "html",
						success : function( res ){
					
						}, 	
						error : function( errorResp ){
						console.log( errorResp.status );
						},
				
				
					}); 
					
					return false;
					
					) );
		
		</script>

</body>
</html>