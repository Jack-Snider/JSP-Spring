<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="factorialForm" action="<%=request.getContextPath() %>/factorial">
	<input type="number" name="operand"  value="${number }"/>
</form>
<pre>
1. 반복문 : scriptlet
2. 재귀 호출 : declaration
3. 피연산자 선택 UI
4. Model1 -> Model2
5. 비동기.
<%-- ${number }! = ${result } --%>
<span id="resultArea"></span>
</pre>
<script type="text/javascript">
	const PATTERN = "%O! = %R";
	$(":input[name]").on("change", function(){
		$(this).parents("form:first").submit();
	});
	$(document).on("submit", "#factorialForm" ,function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); // query string 
		
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				$("#resultArea")
						.html( 
								PATTERN.replace("%O", resp.operand)
										.replace("%R", resp.result)
						);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		
		return false;
	})
</script>





































