<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>   
<table class="table table-bordered">
	<thead>
		<tr>
			<th><spring:message code="member.rnum"/></th>
			<th><spring:message code="member.memId"/></th>
			<th><spring:message code="member.memName"/></th>
			<th><spring:message code="member.memMail"/></th>
			<th><spring:message code="member.memHp"/></th>
			<th><spring:message code="member.memAdd1"/></th>
			<th><spring:message code="member.memMileage"/></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty memberList }">
				<c:forEach items="${memberList }" var="member">
					<tr data-who="${member['memId'] }" 
							data-bs-toggle="modal" data-bs-target="#exampleModal">
						<td>${member.rnum }</td>
						<td>${member['memId'] }</td>
						<td>${member['memName'] }</td>
						<td>${member['memMail'] }</td>
						<td>${member['memHp'] }</td>
						<td>${member['memAdd1'] }</td>
						<td>${member['memMileage'] }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagingArea mb-3">
					${pagingVO.pagingHTML }
				</div>
				
				<div id="searchUI" class="border border-primary border-1 row g-3">
					<h4>검색조건 입력 UI - searchUI</h4>
					<div class="col-auto">
						<select name="searchType" class="form-select">
							<option value>전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="searchWord" placeholder="검색키워드"
							class="form-control"
						/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn"
							class="btn btn-primary"
						/>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" class="border border-danger border-3">
	<h4>검색조건 전송 UI - searchForm</h4>
	<input type="text" name="page" />
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
</form>
<form id='viewForm' action="${pageContext.request.contextPath }/member/memberView.do">
	<input type='hidden' name='who'  />
</form>
<form id='updateForm' action="${pageContext.request.contextPath }/member/memberUpdate.do">
	<input type='hidden' name='who'  />
</form>
<form id="deleteForm" action="${pageContext.request.contextPath }/member/memberDelete.do" method="post">
	<input type='hidden' name='who'  />
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">l
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="updateBtn">UPDATE</button>
        <button type="button" class="btn btn-danger" id="deleteBtn">DELETE</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/member/memberList.js'></script>
<script>
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
		let inputTags = searchUI.find(":input[name]");
		$.each(inputTags, function(index, inputTag){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.get(0)[name].value = value;
		});
		searchForm.submit();
	});
	let searchForm = $("#searchForm");
	let pageTag = $("[name=page]");
	$("[name=searchType]").val("${simpleCondition.searchType}");
	$("[name=searchWord]").val("${simpleCondition.searchWord}");
	$(".pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
</script>













