<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<jsp:include page="/includee/preScript.jsp" />
	</head>
	<body>
	   <form action="<%=request.getContextPath() %>/bts/getContent">
	   <select name="member">
	   <%
	      Map<String, String[]> btsDB = (Map)application.getAttribute("btsDB");
	      StringBuffer options = new StringBuffer();
	      btsDB.forEach((k,v)->{
	         options.append(
	            String.format("<option value='%s'>%s</option>\n",k,v[0])   
	         );
	      });
	      out.print(options);
	   %>
	   </select>
	</form>
	
	<script type="text/javascript">
		$( document ).on( "submit", "form:first", function( event ){
			event.preventDefault();
			let form = this;
			let url = this.action;
			let method = this.method;
			let data = $( this ).serialize();
			$.ajax({
				url : url,
				data : data,
				dataType : "html",
				success : function(res) {
					$( form ).after( resp );
				},
				error : function(errorResp) {
					console.log(errorResp.status);
				}
			});
		} );
	</script>
	
	</body>
</html>