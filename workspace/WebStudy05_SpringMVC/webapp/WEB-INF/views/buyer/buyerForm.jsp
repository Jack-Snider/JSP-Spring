<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>	
<form:form modelAttribute="buyer" method="post">
<%--==========수정시 필요한 거래처 코드============== --%>
	<input type="hidden" name="buyerId" value="${buyer.buyerId }" />	
<%--======================================== --%>
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="buyer.buyerName" /></th>
			<td>
				<form:input path="buyerName" class="form-control" required="true" />
				<form:errors path="buyerName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerLgu" /></th>
			<td>
				<form:select path="buyerLgu" class="form-control" required="true" >
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
					</c:forEach>
				</form:select>
				<form:errors path="buyerLgu" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBank" /></th>
			<td>
				<form:input path="buyerBank" class="form-control editable" />
				<form:errors path="buyerBank" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankno" /></th>
			<td>
				<form:input path="buyerBankno" class="form-control editable" />
				<form:errors path="buyerBankno" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerBankname" /></th>
			<td>
				<form:input path="buyerBankname" class="form-control editable" />
				<form:errors path="buyerBankname" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerZip" /></th>
			<td>
				<form:input path="buyerZip" class="form-control editable" />
				<form:errors path="buyerZip" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd1" /></th>
			<td>
				<form:input path="buyerAdd1" class="form-control editable" />
				<form:errors path="buyerAdd1" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerAdd2" /></th>
			<td>
				<form:input path="buyerAdd2" class="form-control editable" />
				<form:errors path="buyerAdd2" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerComtel" /></th>
			<td>
				<form:input path="buyerComtel" class="form-control editable" required="true" />
				<form:errors path="buyerComtel" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerFax" /></th>
			<td>
				<form:input path="buyerFax" class="form-control editable" required="true" />
				<form:errors path="buyerFax" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerMail" /></th>
			<td>
				<form:input path="buyerMail" class="form-control editable" required="true" />
				<form:errors path="buyerMail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerCharger" /></th>
			<td>
				<form:input path="buyerCharger" class="form-control editable" />
				<form:errors path="buyerCharger" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="buyer.buyerTelext" /></th>
			<td>
				<form:input path="buyerTelext" class="form-control editable" />
				<form:errors path="buyerTelext" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success"/>
				<input type="reset" value="취소" class="btn btn-danger"/>
			</td>
		</tr>
	</table>
</form:form>
<c:if test="${command eq 'UPDATE' }">
<script>
		$(":input[name]:not(.editable)").prop("readonly", true);
</script>
</c:if>
	
