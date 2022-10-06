<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/includee/preScript.jsp" />
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>	
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/skin-win8/ui.fancytree.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all-deps.min.js"></script>
<script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.36.1/jquery.fancytree-all.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tree1").fancytree({
			selectMode : 1,
			source: {
				url : "<%=request.getContextPath() %>/fileBrowser.do"
			  },
			lazyLoad : function(event, data) {
				data.result = {
					url : "<%=request.getContextPath() %>/fileBrowser.do"
					, data:{base : data.node.getKeyPath(), child:'DIR'}
				};
			},
			activate:function(event, data){
				$.ajax({
					url:"<%=request.getContextPath() %>/fileBrowser.do"
					, data:{base:data.node.getKeyPath()}
					, dataType:"json"
					, success:function(resp){
						let spans = [];
						$.each(resp, function(index, node){
							let span = $("<span class='fancytree-node'>")
								.append(
									$("<span class='fancytree-icon'>")		
									, $("<span class='fancytree-title'>").html(node.title)		
								);
							if(node.folder) span.addClass("fancytree-ico-cf");
							spans.push(span);
						});
						$("#detail").html(spans);
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<h4>Model2 구조로 webStudy01 컨텍스트의 익스플로러 구현</h4>
	<div class="container row">
		<div id="tree1" class="col">
		</div>
		<div id="detail" class="col border border-primary">
		</div>
	</div>
<jsp:include page="/includee/postScript.jsp" />	
</body>
</html>









