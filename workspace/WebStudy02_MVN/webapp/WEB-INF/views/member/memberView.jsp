<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<table>
	<tr>
		<th>회원아이디</th>
		<td>${member['memId'] }</td>
	</tr>
	<tr>
		<th>MEM_PASS</th>
		<td>${member['memPass'] }</td>
	</tr>
	<tr>
		<th>MEM_NAME</th>
		<td>${member['memName'] }</td>
	</tr>
	<tr>
		<th>MEM_REGNO1</th>
		<td>${member['memRegno1'] }</td>
	</tr>
	<tr>
		<th>MEM_REGNO2</th>
		<td>${member['memRegno2'] }</td>
	</tr>
	<tr>
		<th>MEM_BIR</th>
		<td>${member['memBir'] }</td>
	</tr>
	<tr>
		<th>MEM_ZIP</th>
		<td>${member['memZip'] }</td>
	</tr>
	<tr>
		<th>MEM_ADD1</th>
		<td>${member['memAdd1'] }</td>
	</tr>
	<tr>
		<th>MEM_ADD2</th>
		<td>${member['memAdd2'] }</td>
	</tr>
	<tr>
		<th>MEM_HOMETEL</th>
		<td>${member['memHometel'] }</td>
	</tr>
	<tr>
		<th>MEM_COMTEL</th>
		<td>${member['memComtel'] }</td>
	</tr>
	<tr>
		<th>MEM_HP</th>
		<td>${member['memHp'] }</td>
	</tr>
	<tr>
		<th>MEM_MAIL</th>
		<td>${member['memMail'] }</td>
	</tr>
	<tr>
		<th>MEM_JOB</th>
		<td>${member['memJob'] }</td>
	</tr>
	<tr>
		<th>MEM_LIKE</th>
		<td>${member['memLike'] }</td>
	</tr>
	<tr>
		<th>MEM_MEMORIAL</th>
		<td>${member['memMemorial'] }</td>
	</tr>
	<tr>
		<th>MEM_MEMORIALDAY</th>
		<td>${member['memMemorialday'] }</td>
	</tr>
	<tr>
		<th>MEM_MILEAGE</th>
		<td>${member['memMileage'] }</td>
	</tr>
	<tr>
		<th>MEM_DELETE</th>
		<td>${member['memDelete'] }</td>
	</tr>
	<tr>
		<th>구매기록</th>
		<td>
			<table>
				<c:set value="${member.prodList }" var="prodList" />
				<c:choose>
					<c:when test="${not empty prodList }">
						<c:forEach items="${prodList }" var="prod">
							<tr>
								<td>${prod.prodName }</td>
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
			</table>
		</td>
	</tr>
</table>















