<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<form:form modelAttribute="prod" method="post" enctype="multipart/form-data">
	<%--==============수정 폼 =============--%>      
	<form:hidden path="prodId"  />
	<%--================================--%>      
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="prod.prodName" /></th>
			<td>
				<form:input path="prodName" class="form-control" required="true" />
				<form:errors path="prodName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodLgu" /></th>
			<td>
				<form:select path="prodLgu" required="true" class="form-control editable">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
					</c:forEach>
				
				</form:select>
				<form:errors path="prodLgu" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodBuyer" /></th>
			<td>
				<form:select path="prodBuyer" required="true" class="form-control editable">
					<option value>거래처</option>
<%-- 					<form:options items="${buyerList }" itemValue="buyerId" itemLabel="buyerName" /> --%>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option value="${buyer.buyerId }" label="${buyer.buyerName }" class="${buyer.buyerLgu }" />
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodCost" /></th>
			<td>
				<form:input path="prodCost" class="form-control editable" required="true" />
				<form:errors path="prodCost" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodPrice" /></th>
			<td>
				<form:input path="prodPrice" class="form-control editable" required="true" />
				<form:errors path="prodPrice" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodSale" /></th>
			<td>
				<form:input path="prodSale" class="form-control editable" required="true" />
				<form:errors path="prodSale" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodOutline" /></th>
			<td>
				<form:input path="prodOutline" class="form-control" required="true" />
				<form:errors path="prodOutline" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodDetail" /></th>
			<td>
				<form:input path="prodDetail" class="form-control" />
				<form:errors path="prodDetail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodImg" /></th>
			<td>
				<form:input path="prodImg" class="form-control" required="true" />
				<form:errors path="prodImg" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodTotalstock" /></th>
			<td>
				<form:input path="prodTotalstock" class="form-control" required="true" />
				<form:errors path="prodTotalstock" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodInsdate" /></th>
			<td>
				<form:input type="date" path="prodInsdate" class="form-control" />
				<form:errors path="prodInsdate" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodProperstock" /></th>
			<td>
				<form:input path="prodProperstock" class="form-control editable" required="true" />
				<form:errors path="prodProperstock" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodSize" /></th>
			<td>
				<form:input path="prodSize" class="form-control editable" />
				<form:errors path="prodSize" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodColor" /></th>
			<td>
				<form:input path="prodColor" class="form-control editable" />
				<form:errors path="prodColor" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodDelivery" /></th>
			<td>
				<form:input path="prodDelivery" class="form-control editable" />
				<form:errors path="prodDelivery" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodUnit" /></th>
			<td>
				<form:input path="prodUnit" class="form-control editable" />
				<form:errors path="prodUnit" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodQtyin" /></th>
			<td>
				<form:input path="prodQtyin" class="form-control editable" />
				<form:errors path="prodQtyin" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodQtysale" /></th>
			<td>
				<form:input path="prodQtysale" class="form-control editable" />
				<form:errors path="prodQtysale" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="prod.prodMileage" /></th>
			<td>
				<form:input path="prodMileage" class="form-control editable" />
				<form:errors path="prodMileage" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="reset" class="btn btn-danger" value="취소" />
				<input type="submit" class="btn btn-success" value="전송" />
				<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록</a>
			</td>
		</tr>
	</table>
</form:form>	
<script type="text/javascript">
	let prodBuyerTag = $("[name=prodBuyer]");
	$("[name=prodLgu]").on("change", function(event){
		let lgu = $(this).val();
		prodBuyerTag.find("option."+lgu).show();
		prodBuyerTag.find("option:first").show();
		prodBuyerTag.find("option:not(."+lgu+")").hide();
	});
	
	<c:if test="${command eq 'UPDATE' }">
		$(":input[name]:not(.editable)").prop("readonly", true);
	</c:if>
</script>