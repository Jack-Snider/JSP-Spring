<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	MemberVO member = (MemberVO) request.getAttribute("member");
	if (member == null)
		member = new MemberVO();

	Map<String, String> errors = (Map) request.getAttribute("errors");
	if (errors == null)
		errors = new HashMap();

	String message = (String) request.getAttribute("message");
	if (message != null && !message.isEmpty()) {
	%>

	<div class=error><%=message%></div>

	<%
	}
	%>
	<h4>가입양식</h4>
	<form method="post">
		<table class="table table-bordered">
			<tr>
				<th>회원아이디</th>
				<td><input type="text" name="memId" class="form-control"
					required value="${ member.memId }" /><span class="error">${ errors.memId }</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memPass" class="form-control"
					required value="${ member.memPass }" /><span class="error">${ errors.memPass }</span></td>
			</tr>
			<tr>
				<th>회원이름</th>
				<td><input type="text" name="memName" class="form-control"
					required value="<%=member.getMemName()%>" /><span class="error"><%=errors.get("memName")%></span></td>
			</tr>
			<tr>
				<th>MEM_REGNO1</th>
				<td><input type="text" name="memRegno1" class="form-control"
					value="<%=member.getMemRegno1()%>" /><span class="error"><%=errors.get("memRegno1")%></span></td>
			</tr>
			<tr>
				<th>MEM_REGNO2</th>
				<td><input type="text" name="memRegno2" class="form-control"
					value="<%=member.getMemRegno2()%>" /><span class="error"><%=errors.get("memRegno2")%></span></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="memBir" class="form-control"
					value="<%=member.getMemBir()%>" /><span class="error"><%=errors.get("memBir")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" class="form-control"
					required value="<%=member.getMemZip()%>" /><span class="error"><%=errors.get("memZip")%></span></td>
			</tr>
			<tr>
				<th>MEM_ADD1</th>
				<td><input type="text" name="memAdd1" class="form-control"
					required value="<%=member.getMemAdd1()%>" /><span class="error"><%=errors.get("memAdd1")%></span></td>
			</tr>
			<tr>
				<th>MEM_ADD2</th>
				<td><input type="text" name="memAdd2" class="form-control"
					required value="<%=member.getMemAdd2()%>" /><span class="error"><%=errors.get("memAdd2")%></span></td>
			</tr>
			<tr>
				<th>MEM_HOMETEL</th>
				<td><input type="text" name="memHometel" class="form-control"
					value="<%=member.getMemHometel()%>" /><span class="error"><%=errors.get("memHometel")%></span></td>
			</tr>
			<tr>
				<th>MEM_COMTEL</th>
				<td><input type="text" name="memComtel" class="form-control"
					value="<%=member.getMemComtel()%>" /><span class="error"><%=errors.get("memComtel")%></span></td>
			</tr>
			<tr>
				<th>MEM_HP</th>
				<td><input type="text" name="memHp" class="form-control"
					value="<%=member.getMemHp()%>" /><span class="error"><%=errors.get("memHp")%></span></td>
			</tr>
			<tr>
				<th>MEM_MAIL</th>
				<td><input type="text" name="memMail" class="form-control"
					required value="<%=member.getMemMail()%>" /><span class="error"><%=errors.get("memMail")%></span></td>
			</tr>
			<tr>
				<th>MEM_JOB</th>
				<td><input type="text" name="memJob" class="form-control"
					value="<%=member.getMemJob()%>" /><span class="error"><%=errors.get("memJob")%></span></td>
			</tr>
			<tr>
				<td colspan = "2">
					<input type = "reset" value = "취소" class = "btn btn-warning">
					<input type = "submit" value = "저장" class = "btn btn-primary">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>