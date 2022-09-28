<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- // 상품아이디, 상품명, 판매가, 구매가, 마일리지.  -->
<!-- // + 분류명, 거래처명, 해당 상품의 구매자수(mem_count) -->
<table class="table table-bordered table-striped">
	<thead class="table-dark">
		<tr>
			<th>상품명</th>
			<th>분류명</th>
			<th>판매가</th>
			<th>구매가</th>
			<th>마일리지</th>
			<th>거래처명</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty prodList }">
				<c:forEach items="${prodList }" var="prod">
					<c:url value="/prod/prodView.do" var="viewURL">
						<c:param name="what" value="${prod.prodId }" />
					</c:url>
					<tr>
						<td><a href="${viewURL }">${prod.prodName }</a></td>
						<td>${prod.lprodNm }</td>
						<td>${prod.prodPrice }</td>
						<td>${prod.prodCost }</td>
						<td>${prod.prodMileage }</td>
						<td>${prod.buyer.buyerName }</td>
						<td>${prod.memCount }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">상품 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>











