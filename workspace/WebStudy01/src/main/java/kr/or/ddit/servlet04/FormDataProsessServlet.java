package kr.or.ddit.servlet04;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.vo.FormDataVO;

/**
 * 
 * 문자열의 상태로 전성되는 파라미티ㅓ 핸들링,
 * Request Line ( GET )
 * 		- 서버의 설정으로 특수문자 처리
 * 			ex) server.xml, -> connector -> URIEncoding/ URO\
 * Request Body ( POST, PUT/Patch )
 * 		- request.setCharacterEncoding( encoding )  
 * 
 * 
 * ***x 특수문자가 포함된 경우. - 해당 데이터에 접근하기 위해 charset 설정 필요
 * 
 * 	String getPara,eter( name )
 * 	String[] getParameter( name )
 *	Map<String, String[]> getP  
 * 
 *
 */

@WebServlet( "/formDataProcess" )
public class FormDataProsessServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 req.setCharacterEncoding( "UTF-8" );
		
		 Map<String, String[]> parameterMap = req.getParameterMap();
		 for( Entry<String, String[]> entry : parameterMap.entrySet() ) {
			 String paramName = entry.getKey();
			 String[] paramValues = entry.getValue();
			 System.out.printf( "%s : %s \n", paramName, paramValues ,Arrays.toString( paramValues ));
		 }
	
		 FormDataVO vo = new FormDataVO();
//		 vo.setParamIpt1( req.getParameter( "paramIpt1" ) );
//		 vo.setParamIpt2( req.getParameter( "paramIpt2" ) );
		 
		 
		 try {
			BeanUtils.populate( vo, parameterMap );
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 req.setAttribute( "vo",vo );
		 
		 String view = "/WEB-INF/views/formDataView.jsp";
		 req.getRequestDispatcher( view ).forward( req, resp );
		 
	}
	
	
	
}
