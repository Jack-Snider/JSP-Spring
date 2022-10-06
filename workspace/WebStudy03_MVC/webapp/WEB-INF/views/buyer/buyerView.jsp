<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<table class="table table-bordered">
	<tr>
		<th>거래처코드</th>
		<td>${buyer.buyerId }</td>
	</tr>
	<tr>
		<th>거래처명</th>
		<td>${buyer.buyerName }</td>
	</tr>
	<tr>
		<th>거래처분류</th>
		<td>${buyer.lprodNm }</td>
	</tr>
	<tr>
		<th>거래은행</th>
		<td>${buyer.buyerBank }</td>
	</tr>
	<tr>
		<th>계좌</th>
		<td>${buyer.buyerBankno }</td>
	</tr>
	<tr>
		<th>계좌주</th>
		<td>${buyer.buyerBankname }</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${buyer.buyerZip }</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${buyer.buyerAdd1 }</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${buyer.buyerAdd2 }</td>
	</tr>
	<tr>
		<th>전번</th>
		<td>${buyer.buyerComtel }</td>
	</tr>
	<tr>
		<th>팩스</th>
		<td>${buyer.buyerFax }</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>${buyer.buyerMail }</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>${buyer.buyerCharger }</td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td>${buyer.buyerTelext }</td>
	</tr>
	<tr>	
		<td colspan="2">
			<a class="btn btn-secondary" href="<c:url value='/buyer/buyerList.do'/>">목록</a>
		</td>
	</tr>
	<tr>
		<th>거래품목</th>
		<td>
			<table class="table table-bordered">
				<thead class="table thead-dark">
					<tr>
						<th>품목명</th>
						<th>구매가</th>
						<th>판매가</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody>
					<c:set value="${buyer.prodList }" var="prodList" />
					<c:choose>
						<c:when test="${not empty prodList }">
							<c:forEach items="${prodList }" var="prod">
								<c:url value="/prod/prodView.do" var="prodViewURL">
									<c:param name="what" value="${prod.prodId }" />
								</c:url>
								<tr>
									<td>
										<a href="${prodViewURL }">${prod.prodName }</a>
									</td>
									<td>${prod.prodCost}</td>
									<td>${prod.prodPrice }</td>
									<td>${prod.prodMileage }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">거래품목 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</td>
	</tr>
</table>