<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<table class="table table-bordered">
	<tr>
		<th><spring:message code="prod.prodId" /></th>
		<td>${prod.prodId }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodName" /></th>
		<td>${prod.prodName }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodLgu" /></th>
		<td>${prod.lprodNm }</td>
	</tr>
	<tr>
	<th><spring:message code="prod.prodBuyer" /></th>
	<td>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><spring:message code="buyer.buyerName"/></th>
					<th><spring:message code="buyer.buyerAdd1"/></th>
					<th><spring:message code="buyer.buyerCharger"/></th>
					<th><spring:message code="buyer.buyerComtel"/></th>
					<th><spring:message code="buyer.buyerBankname"/></th>
					<th><spring:message code="buyer.buyerBankno"/></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="buyer" value="${prod.buyer }" />
				<c:url value="/buyer/buyerView.do" var="buyerViewURL">
					<c:param name="what" value="${buyer.buyerId }" />
				</c:url>
				<tr>
					<td><a href="${buyerViewURL }">${buyer.buyerName }</a></td>
					<td>${buyer.buyerAdd1 }</td>
					<td>${buyer.buyerCharger }</td>
					<td>${buyer.buyerComtel }</td>
					<td>${buyer.buyerBankname }</td>
					<td>${buyer.buyerBankno }</td>
				</tr>
			</tbody>
		</table>
	</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodCost" /></th>
		<td>${prod.prodCost }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodPrice" /></th>
		<td>${prod.prodPrice }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodSale" /></th>
		<td>${prod.prodSale }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodOutline" /></th>
		<td>${prod.prodOutline }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodDetail" /></th>
		<td>${prod.prodDetail }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodImg" /></th>
		<td><img src="<c:url value='/resources/prodImages/${prod.prodImg }'/>"/></td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodTotalstock" /></th>
		<td>${prod.prodTotalstock }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodInsdate" /></th>
		<td>${prod.prodInsdate }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodProperstock" /></th>
		<td>${prod.prodProperstock }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodSize" /></th>
		<td>${prod.prodSize }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodColor" /></th>
		<td>${prod.prodColor }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodDelivery" /></th>
		<td>${prod.prodDelivery }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodUnit" /></th>
		<td>${prod.prodUnit }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodQtyin" /></th>
		<td>${prod.prodQtyin }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodQtysale" /></th>
		<td>${prod.prodQtysale }</td>
	</tr>
	<tr>
		<th><spring:message code="prod.prodMileage" /></th>
		<td>${prod.prodMileage }</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:url value="/prod/prodUpdate.do" var="updateURL">
				<c:param name="what" value="${prod.prodId }" />
			</c:url>
			<input type="button" class="btn btn-primary" value="수정" onclick="location.href='${updateURL}';"/>
			<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록</a>
		</td>
	</tr>
	<tr>
		<th><spring:message code="prod.memCount"/></th>
		<td>
			<table class="table table-bordered">
				<thead class="table-dark">
					<tr>
						<th><spring:message code="member.memId"/></th>
						<th><spring:message code="member.memName"/></th>
						<th><spring:message code="member.memAdd1"/></th>
						<th><spring:message code="member.memMileage"/></th>
					</tr>
				</thead>
				<tbody>
				<c:set var="memberList" value="${prod.memberList }" />
				<c:if test="${not empty memberList }">
					<c:forEach items="${memberList }" var="member">
						<c:url value="/member/memberView.do" var="memberViewURL">
							<c:param name="who" value="${member.memId }" />
							<c:param name="layout" value="GRID" />
						</c:url>
						<tr>
							<td>${member['memId'] }</td>
							<td>
								<a href="${memberViewURL }">${member['memName'] }</a>
							</td>
							<td>${member['memAdd1'] }</td>
							<td>${member['memMileage'] }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty memberList }">
					<tr>
						<td colspan="4">구매 기록 없음.</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</td>
	</tr>
</table>
