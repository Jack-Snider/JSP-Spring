package kr.or.ddit.servlet02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/calculate" )
public class CarculateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		String n = request.getParameter( "leftOp" );
		String m = request.getParameter( "rightOp" );
		
		int numLeft = 0;
		int numRight = 0;
		
		if( numDataCheck( n ) && numDataCheck( m )) {
			
			// 올바른 데이터 양식일 때
			
			numLeft = Integer.parseInt( n );
			numRight = Integer.parseInt( m );
			
			
			
		}else {
			throw new IllegalArgumentException( "숫자만 입력 가능합니다." );
		}
		
		
		
		
		
		
		
	}
	
	
	// 정규식으로 클라이언트로부터 받은 데이터가 오직 숫자만 있는지 검사
	public boolean numDataCheck( String number ) {
		
		boolean status = false;
		
		if( number != null && number.matches( "[0-9]+" ) ) {
			status = true;
		}
		
		
		return status;
	}
	
	
	
}
