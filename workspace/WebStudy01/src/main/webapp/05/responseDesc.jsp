<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4> response (HttpServletResponse) </h4>
<pre>
	: 서버가 클라이언트쪽으로 전송하는 컨텐츠에 대한 모든 정보를 가진 객체.
	
	Http 의 response packaging
	1. Response Line : Status Code, Protocol/version
	
		Status Code : 요청 처리 성공 여부를 알려주는 상태코드
			response.setStatus(sc)
			response.sendError(sc)
			response.sendRedirect(location)
		100~ : ING... (WebSocket 을 이용한 실시간 양방향 처리), WebRTC
		200~ : OK
		300~ : 클라이언트의 추가 액션이 필요함. 
			301 / 302 / 307(Moved) - Location 헤더와 병행. Redirect 이동구조에서 활용.
			304(Not Modified)
		400~ : 클라이언트측 오류로 실패
			404(Not Found, Not Exist)
			405(Method Not Allowed)
			400(Bad Request) : 필수 데이터 누락. 데이터 타입 부적절, 데이터 길이 부적절...
			401(UnAuthorized)/403(Forbidden) : 보안 처리를 위한 접근 제어.
			406(Not Acceptable , request Accept Header) : 서버가 해당 응답 컨텐츠 타입을 처리할 수 없음.
			415(UnSupported Media Type , reqeust Content-Type Header) : 서버가 요청의 포함된 컨텐츠를 처리할 수 없음.
		500~ : 서버측 오류로 실패
		
	2. Response Header - Contents metadata
		Content-Type(request Accept header pair)
		Content-Length
		Refresh(Reload)
		Cache-XXX
	3. Response Body(Content Body, Message Body) - Contents
</pre>
</body>
</html>