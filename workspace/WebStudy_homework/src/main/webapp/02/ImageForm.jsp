<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
    		select{
    			background-color : aqua;
    		}
    	</style>
    	
    	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    	
    
    	
    </head>                      
	<body>                      
         <h4> 이미지 파일 선택 </h4>    
      	   <form action = '${ cPath }/image'>
     	   		<select name = 'name'>
     	   			${ options }                   
      			</select>
      			<input type='submit' value='전송' />
      		</form>                      
      		<div id = "imageArea">
	      		
      		</div>
    </body>                       
</html>                           