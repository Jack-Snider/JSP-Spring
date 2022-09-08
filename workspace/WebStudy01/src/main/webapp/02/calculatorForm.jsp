<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<!-- 
이항 연산자로 4칙 연산 처리.
 
1. 동기처리
2. 비동기처리 json 데이터로

연산 결과 : 2 + 2 = 4
-->
Send Data Type
<input type="radio" name="sendDataType" value="json" checked/>JSON
<input type="radio" name="sendDataType" value="parameter"/>Parameter
<hr/>
Receive Data Type
<input type="radio" name="receiveDataType" value="xml"/>XML
<input type="radio" name="receiveDataType" value="json" checked/>JSON
<form action="<%=request.getContextPath()%>/calculate" name="calForm" method="post">
   <input type="number" name="leftOp" value='${ leftOp }'/>
   <select name="operator">
      <%
         for( OperatorType single : OperatorType.values()){
            %>
            <option value="<%=single.name() %>"><%=single.getSign() %></option>
            <%
         }
      %>
   </select>
   <input type="number" name="rightOp" value='${ rightOp }'/>
   <input id="sub" type="submit" value="=">
</form>
   <div id="resultArea">
   
   </div>
<script type="text/javascript">
   /* $('select[name="operator"]').val('${operator}').prop('selected',true);
   
   const PATTERN = "%L %O %R = %RE";
   $(document).on('submit', 'form',function(event){
      event.preventDefault();
      
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize(); //query string 생성
      $.ajax({
         url : url,
         method : method,
         data : data, 
         dataType : "json",
         success : function(resp) {
            $("#resultArea").html( 
                  PATTERN.replace("%L", resp.leftOp)
                        .replace("%O", resp.oper)
                        .replace("%R", resp.rightOp)
                        .replace("%RE", resp.result)
            );
         },
         error : function(errorResp) {
            console.log(errorResp.status);
         }
      });
      return false;
   });    */
</script>
<script type="text/javascript">

   let makeSendData = function(settings){
      let inputs = calForm.find(':input[name]');
      let data = {};
      $.each(inputs, function(index, input){
         let name = this.name;
         let value = $(this).val();
         let type = this.type;
         if(type=='number'){
            data[name] = parseInt(value);
         }else{
            data[name] = value;
         }
      });
      
      
      console.log(data);
      
      let sendDataType = $("[name=sendDataType]:checked").val();
      
      if(sendDataType == 'json'){
         settings.data = JSON.stringify(data);
         settings.contentType = "application/json;charset=UTF-8";
      }else{
         settings.data = data;
      }
   }
   
   let makeReceiveDataType = function(settings){
      let receiveDataType = $("[name=receiveDataType]:checked").val();
      let dataType = receiveDataType;
      
      settings.dataType = dataType;
   }


   let calForm = $(document.calForm).on('submit',function(event){
      event.preventDefault();
      
      let url = this.action;
      let method = this.method;
      
      
      let settings = {
            url : url,
            method : method,
            success : function(resp) {
               console.log(resp);
               calForm.after(resp.expression);
            },
            error : function(errorResp) {
               alert(errorResp.status);
            }
         };
      
      makeSendData(settings);
      makeReceiveDataType(settings);
      
      $.ajax(settings);
      return false;
   });
</script>
</body>
</html>