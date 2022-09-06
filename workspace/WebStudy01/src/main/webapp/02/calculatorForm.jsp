<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>

	<script type="text/javascript" src = "<%= request.getContextPath() %>/resource/js/jquery-3.6.1.min.js"></script>
 
</head>
<body>

 이항 연산자로 4칙 연산 처리.
<form action="<%=request.getContextPath()%>/calculate" id="calcForm" method = "post">
   <input type="number" name="leftOp" />
   <select name = "operator">
       <%
          for(OperatorType single : OperatorType.values()){
             
             %>
               <option value = "<%=single.name() %>"><%=single.getSign() %></option>
             <% 
          }
       %>
  <!--     <option value="PLUS">+</option>
      <option value="MINUS">-</option>
      <option value="MULTIPLY">*</option>
      <option value="DIVIDE">/</option> -->
   </select>
   <input type="number" name="rightOp" />
   <input type="submit" value="=" id="result">
</form>

	vet vnearthEatn

<script type="text/javascript">
	$( document.calForm ).on( "submit", function(){
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
//		let data = $( this ).serialize(); // QueryStrings

		let jsObj = {
						leftOp : 23,
						rightOp : 28,
						operator : "PLUS"
					};

		// Marshalling할때는 stringify 함수 사용
		// UnMashalling할때는 parse 함수 사용
		let json = JSON.stringify( jsObjc );

		
		$.ajax({
			url : url,
			method : method,
			contentType : "application/json;charset=UTF-8",
			data : json,
			dataType : "html",
			success : function( resp ){
				concole.log( resp );
				calForm.afters( resp.expression )
				
				
			},
			error : function( errorResp ){
				console.log( errorResp.status );
			}
		});
		
		return false;
	} );
</script>

<%-- ${res } --%>


<!--  <script>
   
   $("#result").on("click",function(){
      $(this).submit();
      
   });
   
   $(document).on("submit","#calcForm",function(event){
      event.preventDefault();
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize();
   
   
     $.ajax({

      url : url, //어디로
      method : method, //어떻게
      data : data, //무엇을
      dataType : "json",
      success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함

         $("#result").html(resp.res);
         
      },
      error : function() {

         console.log(errorResp.status);
      }

   });
     
     return false;
      
   }); 

</script> -->


<!-- 2 + 2 = 4

1.동기처리
응답데이터 html

2.비동기
jason data mashaling 살펴보기  -->
<!-- 

   요약 : 둘의 차이점은 기능이 나눠져 있냐, 없냐의 차이

   Model1 - JSP든, 서블릿이든 한 곳에서 연산과 뷰의 역할을 '모두' 하는 것
   
   
   Model2 - 뷰와 컨트롤러의 역할이 나누어져 있는 것.



 -->
 
 
 
</body>
</html>
