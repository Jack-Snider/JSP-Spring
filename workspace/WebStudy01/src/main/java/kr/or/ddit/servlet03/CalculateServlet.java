package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet{
	private CalculateVO parseRequest(HttpServletRequest req) throws IOException{
		String bodyContentType = req.getContentType();
		CalculateVO vo = null;
		if(bodyContentType.contains("json")) {
			try(
				InputStream is = req.getInputStream();
			){
				ObjectMapper mapper = new ObjectMapper();
				vo = mapper.readValue(is, CalculateVO.class);
			}
		}else {
			vo = new CalculateVO();
			String opParam = req.getParameter("operator");
			int rightParam = Integer.parseInt(req.getParameter("rightOp"));
			int leftParam = Integer.parseInt(req.getParameter("leftOp"));
			
			vo.setLeftOp(leftParam);
			vo.setRightOp(rightParam);
			vo.setOperator(OperatorType.valueOf(opParam));
			
//			try {
//				BeanUtils.populate(vo, req.getParameterMap());
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				throw new IOException(e);
//			}
		}
		return vo;
	}
	
	private void sendResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CalculateVO vo = (CalculateVO) req.getAttribute("calculator");
		int statusCode = HttpServletResponse.SC_OK;
		String accept = req.getHeader("Accept");
		String contentType = null;
		
		ObjectMapper mapper = null;
		if(accept.contains("json")) {
			contentType = "application/json;charset=UTF-8";
			mapper = new ObjectMapper();
		}else if(accept.contains("xml")) {
			contentType = "application/xml;charset=UTF-8";
			mapper = new XmlMapper();
		}else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		
		if(statusCode == 200) {
			resp.setContentType(contentType);
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, vo);
			}
		}else {
			resp.sendError(statusCode);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CalculateVO vo = parseRequest(req);
		req.setAttribute("calculator", vo);
		sendResponse(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		
		
 		String opParam = req.getParameter("operator");
 		
 		int statusCode = HttpServletResponse.SC_OK;
 		OperatorType operatorType = null;
 		try {
 			operatorType = OperatorType.valueOf(opParam);
 		}catch (Exception e) {
 			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		if(
			left==null || left.isEmpty() || !left.matches("\\d+")
			|| right==null || right.isEmpty() || !right.matches("\\d+")	
		) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		
		if(statusCode==HttpServletResponse.SC_OK) {
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			
 			String expression = operatorType.getExpression(leftOp, rightOp);
			
 			try(
 				PrintWriter out = resp.getWriter();	
 			){
 				out.println(expression);
 			}
 			
		}else {
			resp.sendError(statusCode);
		}
	}
}


















