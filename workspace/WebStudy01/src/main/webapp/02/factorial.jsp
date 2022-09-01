<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script type="text/javascript" src = "<%= request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
		
	</head>
	<body>
		
		<!-- form태그에 action이 없다면 현재 브라우저로 돌아온다. -->
		<form id = "factorialform">
			<!-- 이벤트 핸들러 안에서 this는 이벤트의 타겟이다. -->
			<input type = "number" name = "operand"/>
		</form>
	
		<pre>
		1. 반복문 : scriptlet
		2. 재귀 호출 : declaration
		3. 피연산자 선택 UI
		4. Model1 -> Model2
		5. 비동기
		10! = 123456789...
		<%-- ${ number }! = ${ result }  --%> 
		<span id = "">  </span>
		</pre>

		
		<script type="text/javascript">
			const PATTERN  = "%O! = %R";
			$( ":input[name]" ).on( "change", function(){
				$( this ).parents( "form:first" ).submit();
			} );
			$( document ).on( 'submit', '#factorialform' ,function( event ){
				
				event.preventDefault();
				
				let url = this.action;
				let method = this.method;
				let data = $( this ).serialize(); // query string
				
				$.ajax({
					
					url : "",
					date : "",
					dataType : "json",
					success : function( resp ){
						resp.operand;
						resp.result;
						$( "#resultArea" ).html(
							PATTERN.replace( "%O", resp.operand )
									.replace( "%R", resp.result )
								
							)
						
					}, 	
					error : function( errorResp ){
							console.log( errorResp.status );
					}
								
								
				});

				return false;
			} );
		</script>
		
	</body>
</html>