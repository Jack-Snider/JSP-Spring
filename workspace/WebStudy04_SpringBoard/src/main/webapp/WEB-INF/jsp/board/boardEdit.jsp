<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="${pageContext.request.contextPath }/resources/js/ckeditor/ckeditor.js"></script>

<form:form id="boardForm" method="post" modelAttribute="board" enctype="multipart/form-data"> 
<%--===========================================--%>
	<form:hidden path="boNo"/>  
<%--===========================================--%>
<table class="table table-bordered">
	<tr>
		<th><spring:message code="board.boTitle"/></th>
		<td>
			<form:input path="boTitle" required="true" class="form-control"/>
			<form:errors path="boTitle" element="span" cssClass="error" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boWriter" /></th>
		<td>
			<form:input path="boWriter" class="form-control" readonly="true"/>
			<form:errors path="boWriter" element="span" class="error"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boPass" /></th>
		<td>
			<input type="password" name="boPass" class="form-control"/>
			<form:errors path="boPass" element="span" class="error"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boMail" /></th>
		<td>
			<form:input path="boMail" type="email" class="form-control" readonly="true"/>
			<form:errors path="boMail" element="span" class="error"/>
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boIp" /></th>
		<td>
			<form:input path="boIp" readonly="true"  class="form-control" />
			<form:errors path="boIp" element="span" class="error"/>
		</td>
	</tr>
	<tr>
		<th>기존 첨부파일</th>
		<td>
			<c:forEach items="${board.attatchList }" var="attatch">
				<span class="fileArea">
					${attatch.attFilename }
					<span class="btn btn-danger delBtn" data-att-no="${attatch.attNo }">삭제</span>
				</span>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th>신규 첨부파일</th>
		<td>
			<input type="file" name="boFiles" />
			<input type="file" name="boFiles" />
			<input type="file" name="boFiles" />
		</td>
	</tr>
	<tr>
		<th><spring:message code="board.boContent" /></th>
		<td>
			<form:textarea path="boContent"/>
			<form:errors path="boContent" element="span" class="error"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<form:button type="submit" class="btn btn-success">전송</form:button>
			<form:button type="reset" class="btn btn-danger">취소</form:button>
		</td>
	</tr>
</table>
</form:form>
<script>
	CKEDITOR.replace('boContent', {
		filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=image"
	});
	let boardForm = $("#boardForm");
	$(".delBtn").on("click", function(event){
		let attNo = $(this).data("attNo");
		let newInput = $("<input>").attr({
			type:"text"
			, name:"delAttNos"
		}).val(attNo);
		boardForm.append(newInput);
		$(this).parents("span.fileArea").hide();
	});
</script>



















