package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/factorial" )
public class FactorialServlet extends HttpServlet {

	public long factorial( int number ){
	
		// 데이터 검증, number는 양수여야한다.
		if( number <= 0 ){
			// 들어온 데이터가 올바르지 않다면 
			// 연산도, 반환도 하면 안된다.
			throw new IllegalArgumentException( "연산은 양수만 처리함." );
		}
	
		if( number == 1 ){
			return 1;
		}else{
			return number * factorial( number - 1 );
		}
	}

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int number = 1;

		// 클라이언트가 보내준 데이터를 받아온다.
		String operand = request.getParameter( "operand" );
		
		// 정수형으로 변환해주기 전에 데이터를 검증해준다. ( 올바른 데이터인지 )
		if( operand != null && operand.matches( "\\d{1,3}" ) ){
			// 올바른 데이터 ( null이 아니면서 1~3글자 사이의 숫자만 있는 데이터 )
			number = Integer.parseInt( operand );
			long f = factorial( number );
			
			request.setAttribute( "number" , number );
			request.setAttribute( "result" , f );
			
			String json = String.format("{ \"operand\" : %d, \"result\" : %d }", number, f );
			request.setAttribute( "json", json );
			
			String view = "/02/factorial.jsp";
			request.getRequestDispatcher( view ).forward(request, response);
			
		}else if( operand != null && !operand.matches( "\\d{1,3}" ) ){
			// 올바른 데이터가 아닐경우 에러메세지 출력
			response.sendError( HttpServletResponse.SC_BAD_REQUEST );
			return;
		}
		
		int sum = 1;
		for( int i = 1; i <= number; i++ ){
			sum *= i;
		}
		
		String accept = request.getHeader( "Accept" );
		if( accept.contains( "json" ) ) {
			
			response.setContentType( "application/json;charset=UTF-8" );
			try( PrintWriter out = response.getWriter() ){
				out.print( request.getAttribute( "json" ) );
			}
				
			
			
		}else {
			String view = "02/factorial.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		
		
	}
	
	
	

	
}
