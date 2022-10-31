<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<table class="table table-bordered">
	<tr>
		<th><spring:message code="board.boNo"/></th>
		<td>${board.boNo }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boTitle"/></th>
		<td>${board.boTitle }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boWriter"/></th>
		<td>${board.boWriter }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boIp"/></th>
		<td>${board.boIp }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boMail"/></th>
		<td>${board.boMail }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boDate"/></th>
		<td>${board.boDate }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boHit"/></th>
		<td>${board.boHit }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boRec"/></th>
		<td>
			<span id="recArea">${board.boRec }</span>
			<span class="btn btn-success" id="recBtn" data-target="#recArea">추천</span>	
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boParent"/></th>
		<td>${board.boParent }</td>
	</tr>
	<tr>
		<th><spring:message code="board.boFiles"/></th>
		<td>
			<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
				<c:url value="/board/download.do" var="downloadURL">
					<c:param name="what" value="${attatch.attNo }" />
				</c:url>
				<a href="${downloadURL }">${attatch.attFilename }</a>(${attatch.attFancysize })
				${not vs.last?"|":"" }
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boContent"/></th>
		<td>${board.boContent }</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<c:url value="/board/boardUpdate.do" var="updateURL">
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
	$("#recBtn").on("click", function(event){
		let selector = $(this).data("target");
		
		$.ajax({
			url : "${cPath}/board/boardRecommend.do",
			data : {
				what:${board.boNo}
			},
			dataType : "json",
			success : function(resp) {
				if(resp.success){
					$(selector).html(resp.boRec);
				}
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
	});
</script>	
	
	
	
	
	
	
	
	
	
	
	
	
	
	