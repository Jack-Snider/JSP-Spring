<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<table class="table table-bordered">
	<tr>
		<th>글번호</th>
		<td>${board.boNo }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${board.boTitle }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.boWriter }</td>
	</tr>
	<tr>
		<th>아이피</th>
		<td>${board.boIp }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${board.boMail }</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>${board.boPass }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.boContent }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${board.boDate }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${board.boHit }</td>
	</tr>
	<tr>
		<th>추천수</th>
		<td>${board.boRec }</td>
	</tr>
	<tr>
		<th>부모글</th>
		<td>${board.boParent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:url value="/board/boardUpdac vte.do" var="updateURL">
				<c:param name="what" value="${board.boNo }" />
			</c:url>
			<a href="${updateURL }" class="btn btn-primary">글 수정</a>
			<a href="#" id="deleteBtn" class="btn btn-danger">글 삭제</a>
		</td>
	</tr>
</table>

<!-- TestDrivenDevelopment vs EventDrivenDevelopment -->
<form name="deleteForm" method="post" action="${cPath }/board/boardDelete.do">
	<input type="hidden" name="boNo" value="${board.boNo }"/>
	<input type="hidden" name="boPass" />
</form>
<script>
	$("#deleteBtn").on("click", function(event){
		event.preventDefault();
		let password = prompt("비밀번호");
		if(password){
			document.deleteForm.boPass.value=password;
			$(document.deleteForm).submit();
// 			document.deleteForm.boPass.value="";
			document.deleteForm.reset();
		}
		return false;
	});
</script>	
	
	
	
	
	
	
	
	
	
	
	
	
	
	