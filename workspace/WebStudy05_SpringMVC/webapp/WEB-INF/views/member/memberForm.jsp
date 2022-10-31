<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h4>가입양식</h4>
<form:form modelAttribute="member" method="post" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="member.memId" /></th>
			<td>
				<form:input path="memId" class="form-control" required="true" />
				<form:errors path="memId" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memPass" /></th>
			<td>
				<input type="password" name="memPass" class="form-control editable" required="true" />
				<form:errors path="memPass" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memName" /></th>
			<td>
				<form:input path="memName" class="form-control" required="true" />
				<form:errors path="memName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memImg" /></th>
			<td>
				<input type="file" name="memImage" class="form-control editable"/>
				<form:errors path="memImage" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memRegno1" /></th>
			<td>
				<form:input path="memRegno1" class="form-control" required="true" />
				<form:errors path="memRegno1" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memRegno2" /></th>
			<td>
				<form:input path="memRegno2" class="form-control" required="true" />
				<form:errors path="memRegno2" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memBir" /></th>
			<td>
				<form:input type="date" path="memBir" class="form-control" />
				<form:errors path="memBir" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memZip" /></th>
			<td>
				<form:input path="memZip" class="form-control editable" required="true" />
				<form:errors path="memZip" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memAdd1" /></th>
			<td>
				<form:input path="memAdd1" class="form-control editable" required="true" />
				<form:errors path="memAdd1" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memAdd2" /></th>
			<td>
				<form:input path="memAdd2" class="form-control editable" required="true" />
				<form:errors path="memAdd2" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memHometel" /></th>
			<td>
				<form:input path="memHometel" class="form-control editable" />
				<form:errors path="memHometel" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memComtel" /></th>
			<td>
				<form:input path="memComtel" class="form-control editable" />
				<form:errors path="memComtel" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memHp" /></th>
			<td>
				<form:input path="memHp" class="form-control editable" />
				<form:errors path="memHp" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memMail" /></th>
			<td>
				<form:input path="memMail" class="form-control editable" required="true" />
				<form:errors path="memMail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memJob" /></th>
			<td>
				<form:input path="memJob" class="form-control editable" />
				<form:errors path="memJob" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memLike" /></th>
			<td>
				<form:input path="memLike" class="form-control editable" />
				<form:errors path="memLike" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memMemorial" /></th>
			<td>
				<form:input path="memMemorial" class="form-control editable" />
				<form:errors path="memMemorial" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="member.memMemorialday" /></th>
			<td>
				<form:input type="date" path="memMemorialday" class="form-control editable" />
				<form:errors path="memMemorialday" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="reset" value="취소" class="btn btn-warning" /> 
				<input type="submit" value="저장" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form:form>
<c:if test="${command eq 'UPDATE' }">
	<script type="text/javascript">
		$(":input:not(.editable)").prop("readonly", true);
	</script>
</c:if>






















