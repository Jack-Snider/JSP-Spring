<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 복습.do -->    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title> 05 / responseDesc.jsp </title>
	</head>
	<body>
		<h4> response ( HttpServletResponse ) </h4>
		<pre>
			
			Http는 서버에서 데이터 갱신이 일어나도 페이지를 새로 고침하지 않는 이상은
			적용이 안된다.
			
			: 서버가 클라이언트쪽으로 전송하는 컨텐츠에 대한 모든 정보를 가진 객체
			
			Http의 response packaging
			1.	Response Line : Status Code , Protocol / Version
					Status Code : 요청 처리 성공 여부를 알려주는 상태코드
							response.setStatus( sc )
							response.sendError( sc )
							response.sendRedirect( location )
						100~ : ING...( WebSocket을 이용한 실시간 양방향 처리 ), WebRTC
						200~ : OK
						300~ : 클라이언트의 추가 액션이 필요함. 
							301 / 302 / 307( Moved ) - Location 헤더와 병행, Redirect 이동구조에서 활용. 
							304( Not Modifed ) 
						400~ : 클라이언트측 오류로 실패
							404 ( Not Found, Not Exist )
							405( Method Not Allowed )
							400( Bad Request ) : 필수 데이터 누락, 데이터 타입 부적절, 데이터 길이 부적절...
							406( Not Acceptable, request Accept Header ) : 서버가 해당 컨텐츠 타입을 처리할 수 없음.
							401( UnAuthorized )/403( Forbidden ) : 보안 처리를 위한 접근 제어.
							406
							415( UnSupported Media Type, request Content-Type Header ) : 서버가 요청의 포함된 컨텐츠를 처리할 수 없음.
							( 서버에서 UnMarshalling을 해줘야하는데 마쉘링을 할 수 없을 떄 )
							
						500~ : 서버측 오류로 실패
						( 클라이언트측 오류 번호대( 400 )은 굉장히 디테일하게 나뉘어져있는데 
						  서버측의 오류는 누군인지도 모르는 클라이언트에게 오류내용을 공개하지 않기
						  위해서 통칭 500으로 알려준다. )
						<%
							//response.setStatus( sc );
							//response.sendError( sc );
							//response.sendRedirect( location );
						%>
					
			2.	Response Header - Contents metadata ( Body영역이 표현해야할 메인 데이터라면 헤더는 그 데이터들의 정보를 표현해주는 보조 데이터 느낌 )
					Content-Type( request Accept header pair )
					Content-Length
					Refresh( Reload ) - 자동요청
					Cache-XXX
				
					( Contents로 시작하는 헤더가 있다는건 반드시 Body Content도 있다는 뜻이다. )
					*GET방식은 Body가 없다, 즉 POST반 body가 있음.
				
			3.	Response Body( Content Body, Message Body ) - Contents ( 메인 컨텐츠 데이터 )
			
			
		</pre>
	</body>
</html>