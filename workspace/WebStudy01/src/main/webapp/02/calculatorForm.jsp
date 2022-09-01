<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- 이항 연산자로 4칙 연산 처리. -->
		<form action = "<%= request.getContextPath() %>/caculate">
			<input type = "number" name = "leftOp"/>
			<select name = "operator">
				<option value = "PLUS">+</option>
				<option value = "MINUS">-</option>
				<option value = "MULTIPLY">*</option>
				<option value = "DIVIDE">/</option>
			</select>
			<input type = "number" name = "rigjtOp">
			<input type = "submit" value = "=">
		</form>
		2 + 2 = 4
	</body>
</html>