<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Language: 표현 언어)</h4>
<pre>
	: 표현식의 대체로 Scope 통해 공유되고 있는 속성(attribute)데이터를 출력하기 위해 사용.
	<%
		request.setAttribute("sample", "   ");
		session.setAttribute("sample", "세션속성");
		pageContext.setAttribute("list", Arrays.asList(""));
	%>
	${sample }, <%=request.getAttribute("sample") %> 
	${sessionScope.sample }, <%=session.getAttribute("sample") %> 
	
	1. EL 연산자
		산술연산자 : ${3+4 }, ${num+2 }, ${"3"+4 }, \${"a"+4 }
					${num-2 }, ${3/4 }
		논리연산자 : ${true and true }, ${alpha or false }, ${not alpha }		
		비교연산자 : eq, ne, gt, lt, ge, le
<%-- 				${3 le 4 }, ${2 eq 2 }, ${3 ne 2 } --%>
		단항연산자 : empty			
			${empty alpha }, ${empty sample }, empty List : ${empty list }
		삼항연산자 : 조건식? 참표현:거짓표현	
			${empty alpha ? "비어있음":"채워져있음" }
			${not empty list ? "채워져있음":"비어있음" }
	2. EL 에서 객체 사용
		<%
			MemberVO member = new MemberVO();
			member.setMemName("김은대");
			pageContext.setAttribute("member", member);
		%>
		${member.memName }, ${member['memName'] } ,${member.getMemName() }
		${member.memTest }, ${member['memTest'] }
	3. EL 에서 컬렉션 사용
	4. EL 기본 객체
		scope 객체 : pageScope(Map), requestScope(Map), sessionScope(Map), applicationScope(Map)
				${sessionScope.sample }, ${sessionScope['sample'] }
		request parameter 객체 : param(Map&gt;String,String&lt;), paramValues(Map&gt;String,String[]&lt;)
			<%=request.getParameter("param1") %> 		
			${param.param1 }, ${param['param1'] }
			<%=request.getParameterValues("param1") %>
			${paramValues.param1[1] }, ${paramValues['param1'][1] }
		request header 객체 : header(Map&gt;String,String&lt;), headerValues(Map&gt;String,String[]&lt;)
			<%=request.getHeader("Accept") %>	
			${header.accept }, ${header['accept'] }
			<%=request.getHeader("user-agent") %>
			${header.user-agent }, ${header['user-agent'] }
		cookie 객체 : cookie	
			<%=request.getCookies() %>
			${cookie.JSESSIONID }, ${cookie.JSESSIONID.value }
			${cookie['JSESSIONID'] }, ${cookie['JSESSIONID']['value'] }
		pageContext : ${pageContext }	 
			<%=session.getId() %>, ${pageContext.session.id }, ${pageContext['session']['id'] }
</pre>
<script>
	let sample = "스크립트변수";
	alert(`변수의 값 : \${sample}`);
</script>
</body>
</html>




























