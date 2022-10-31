<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<table class="table table-bordered">
	<tr>
		<th><spring:message code="member.memId"/></th>
		<td>${member['memId'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memPass"/></th>
		<td>${member['memPass'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memName"/></th>
		<td>${member['memName'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memImg"/></th>
		<td>	
			<img src="data:image/*;base64,${member.base64Img }"/>
			
		</td>
	</tr>
	<tr>
		<th><spring:message code="member.memRegno1"/></th>
		<td>${member['memRegno1'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memRegno2"/></th>
		<td>${member['memRegno2'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memBir"/></th>
		<td>${member['memBir'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memZip"/></th>
		<td>${member['memZip'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memAdd1"/></th>
		<td>${member['memAdd1'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memAdd2"/></th>
		<td>${member['memAdd2'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memHometel"/></th>
		<td>${member['memHometel'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memComtel"/></th>
		<td>${member['memComtel'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memHp"/></th>
		<td>${member['memHp'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMail"/></th>
		<td>${member['memMail'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memJob"/></th>
		<td>${member['memJob'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memLike"/></th>
		<td>${member['memLike'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMemorial"/></th>
		<td>${member['memMemorial'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMemorialday"/></th>
		<td>${member['memMemorialday'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memMileage"/></th>
		<td>${member['memMileage'] }</td>
	</tr>
	<tr>
		<th><spring:message code="member.memDelete"/></th>
		<td>${member['memDelete'] }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a class="btn btn-secondary" href="#" onclick="history.back();">뒤로가기</a>
		</td>
	</tr>
	<tr>
		<th>구매기록</th>
		<td>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><spring:message code="prod.prodId"/></th>
						<th><spring:message code="prod.prodId"/></th>
						<th><spring:message code="prod.prodName"/></th>
						<th><spring:message code="prod.prodLgu"/></th>
						<th><spring:message code="prod.prodBuyer"/></th>
					</tr>
				</thead>
				<tbody>
					<c:set value="${member.prodList }" var="prodList" />
					<c:choose>
						<c:when test="${not empty prodList }">
							<c:forEach items="${prodList }" var="prod">
								<c:url value="/prod/prodView.do" var="prodViewURL">
									<c:param name="what" value="${prod.prodId }" />
								</c:url>
								<tr>
									<td><a href="${prodViewURL }">${prod.prodName }</a></td>
									<td>${prod.lprodNm }</td>
									<td>${prod.buyer.buyerName }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">구매기록 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</td>
	</tr>
</table>















