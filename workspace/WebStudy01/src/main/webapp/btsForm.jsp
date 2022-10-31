<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
<form action="<%=request.getContextPath() %>/bts/getContent">
	<select name="member" onchange="this.form.requestSubmit();">
		<%
			Map<String, String[]> btsDB = (Map) application.getAttribute("btsDB");
			StringBuffer options = new StringBuffer();
			btsDB.forEach((k,v)->{
				options.append(
					String.format("<option value='%s'>%s</option>\n", k, v[0])		
				);
			});
			out.print(options);
		%>
	</select>
</form>

<script type="text/javascript">
	$(document).on("submit", "form:first", function(event){
		event.preventDefault();
		let form = this;
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "html",
			success : function(resp) {
				$(form).after(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		return false;
	});
</script>
</body>
</html>





















