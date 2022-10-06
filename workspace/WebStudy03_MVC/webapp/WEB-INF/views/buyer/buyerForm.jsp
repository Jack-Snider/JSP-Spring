<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<form method="post">
	<input type="hidden" name="buyerId" value="${buyer.buyerId }" />	
	<table class="table table-bordered">
		<tr>
			<th>거래처명</th>
			<td><input type="text" name="buyerName" class="form-control"
				required value="${buyer.buyerName }" /><span class="error">${errors.buyerName }</span></td>
		</tr>
		<tr>
			<th class="fst-italic text-danger">분류코드</th>
			<td>
				<select name="buyerLgu" required class="form-control">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod.lprodGu }"
							${lprod.lprodGu eq buyer.buyerLgu ? "selected" :"" }
						>${lprod.lprodNm }</option>
					</c:forEach>
				</select>
				<span class="error">${errors.buyerLgu }</span>
			</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td><input type="text" name="buyerBank" class="form-control"
				value="${buyer.buyerBank }" /><span class="error">${errors.buyerBank }</span></td>
		</tr>
		<tr>
			<th>계좌</th>
			<td><input type="text" name="buyerBankno" class="form-control"
				value="${buyer.buyerBankno }" /><span class="error">${errors.buyerBankno }</span></td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td><input type="text" name="buyerBankname" class="form-control"
				value="${buyer.buyerBankname }" /><span class="error">${errors.buyerBankname }</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="buyerZip" class="form-control"
				value="${buyer.buyerZip }" /><span class="error">${errors.buyerZip }</span></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="buyerAdd1" class="form-control"
				value="${buyer.buyerAdd1 }" /><span class="error">${errors.buyerAdd1 }</span></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="buyerAdd2" class="form-control"
				value="${buyer.buyerAdd2 }" /><span class="error">${errors.buyerAdd2 }</span></td>
		</tr>
		<tr>
			<th>전번</th>
			<td><input type="text" name="buyerComtel" class="form-control"
				required value="${buyer.buyerComtel }" /><span class="error">${errors.buyerComtel }</span></td>
		</tr>
		<tr>
			<th>팩스</th>
			<td><input type="text" name="buyerFax" class="form-control"
				required value="${buyer.buyerFax }" /><span class="error">${errors.buyerFax }</span></td>
		</tr>
		<tr>
			<th>메일</th>
			<td><input type="text" name="buyerMail" class="form-control"
				required value="${buyer.buyerMail }" /><span class="error">${errors.buyerMail }</span></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td><input type="text" name="buyerCharger" class="form-control"
				value="${buyer.buyerCharger }" /><span class="error">${errors.buyerCharger }</span></td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td><input type="text" name="buyerTelext" class="form-control"
				value="${buyer.buyerTelext }" /><span class="error">${errors.buyerTelext }</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form>
<c:if test="${command eq 'UPDATE' }">
<script>
		$(":input[name]:not(.editable)").prop("disabled", true);
</script>
</c:if>
	
