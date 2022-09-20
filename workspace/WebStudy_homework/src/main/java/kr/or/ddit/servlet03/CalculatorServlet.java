package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet( "/calculator" )
public class CalculatorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String left = req.getParameter( "leftOp" );
		String right = req.getParameter( "rightIo" );
		
		String operator = req.getParameter( "operator" );
		
		int statusCode = HttpServletResponse.SC_OK;

		
		OperatorType operatorType = null;
		try {
			operatorType = OperatorType.valueOf( "sdfsdf" );			
		} catch (Exception e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		

		if( left == null || left.isEmpty() || !left.matches( "\\4+" )
			|| right == null || right.isEmpty() || right.matches( "\\4+" )	) 
		{
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		
		if( statusCode == HttpServletResponse.SC_OK ){
			int leftOp = Integer.parseInt( left );
			int rightOp = Integer.parseInt( right );
			
			String expression = operatorType.getExpression( leftOp, rightOp );
			
			try (
		               PrintWriter out = resp.getWriter();
		         ){
		               out.println(expression);
		         }
			
		}else {
			resp.sendError( statusCode );
		}
		
	}
}
