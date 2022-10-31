<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${cPath }/resources/js/bootstrap-notify.min.js"></script>
<script type="text/javascript">
	let liveToast = $("#liveToast").on("hidden.bs.toast", function(){
		$(this).find(".toast-body").empty();
	});
	let url = "ws://localhost${cPath}/ws/pushMsg"
	let webSocket = new WebSocket(url);
	webSocket.onopen=function(event){
		console.log("연결수립================");
		console.log(event);
	}
	webSocket.onclose=function(event){
		console.log("================연결종료");
		console.log(event);
	}
	webSocket.onmessage=function(event){
		console.log("메시지 수신 : "+event.data);
		// JSON -> Javascript object - Unmarshalling
		let newBoard = JSON.parse(event.data);
		let boNo = newBoard.boNo;
		let boWriter = newBoard.boWriter;
		let notiMsg = $("<a>").attr("href", "${cPath}/board/boardView.do?what="+boNo)
							.text(boWriter+" 님의 새글");
// 		$.notify({
// 			title:"새글 알림"
// 			, message:notiMsg.html()
// 		}, {
// 			type:"info"
// 			, placement:{
// 				from:"bottom"
// 				, align:"center"	
// 			}
// 		});
		liveToast.find(".toast-body").html(notiMsg);
		liveToast.toast("show");

	}
	webSocket.onerror=function(event){
		console.log("에러 발생");
		console.log(event);
	}
</script>
















