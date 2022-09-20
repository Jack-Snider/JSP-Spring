<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>08/implicit.jsp</title>
	</head>
	<body>
		<h4>기본 객체</h4>
		<pre>
			- 다음과 같은 객체들의 저장공간을 '스코프'라고 부른다 -
			- ★이 많을수록 '스코프' 범위가 넓음 -
			★ 	1.	request( HttpServletRequest ) : 클라이언트가 전송한 요청의 모든 정보를 가진 객체
				2.	response( HttpServletResponse ) : 서버가 전송한 응답의 모든 정보를 가진 객체
				3.	out( JspWriter ) : 응답데이터를 기록하거나, 응답 버퍼를 관리.
			★★	4. 	session( HttpSession ) :	한 클라이언트를 대상으로 해당 사용자를 식별할 수 있는 모든 정보를 가진 객체.
											( 한 세션과 관련된 모든 정보를 가진 객체 )
			★★★	5.	application( ServletContext ) : 서버와 현재 컨텍스트에 대한 모든 정보를 가진 객체.
				6.	page( Object ) - this
				7.	config( ServletConfg )
				8.	( exception-Throwable ) : 에러나 예외가 발생한 경우, 해당 정보를 가진 객체.
					( exception 갹체를 사용하려면 page에서 isErrorPage="true"를 추가해줘야함.  )
				9.	pageContext( *** - PageContext ) : 
						9가지 기본 객체중에서 가장 먼저 생성되는 기본 객체이며, 나머지 기본 객체의 참조를 소유. getXXX
												
		</pre>
	</body>
</html>