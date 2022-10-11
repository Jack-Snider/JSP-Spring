package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.vo.PropertyVO;

//@WebServlet({"/properties", "/property"})
@Controller
public class PropertiesControllerServlet{
	
	@Inject
	private PropertyService service;
	
	@RequestMapping( "/properties" )
	@ResponseBody
	public List<PropertyVO> properties() {
		return service.readProperties();
	}
	
	@GetMapping( "/property" )
	@ResponseBody
	protected PropertyVO doGet( @RequestParam( required=true ) String name ) throws ServletException, IOException {
		
	
		Object model = null;
		int statusCode = 200;	
		return service.readProperty( name );
		
//		String message =(String) session.getAttribute("message");
//		session.removeAttribute("message"); // flash attribute
//		System.out.println(message);
		
		
	}
	
	@PostMapping( "/property" )
	protected void doPost( @Valid PropertyVO newProp , Errors errors ) 
			throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8")
		
//		PropertyVO newProp = new PropertyVO();
//		newProp.setPropertyName(req.getParameter("propertyName"));
//		
//		try {
//			BeanUtils.populate(newProp, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
		
//		boolean valid = validate(newProp);
		if( errors.hasErrors() ) {
			service.createProperty(newProp);
			String message = "성공";
			req.getSession().setAttribute("message", message);
			String viewName = "/property?name="+newProp.getPropertyName();
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			resp.sendError(400);
		}
	}

	private boolean validate(PropertyVO newProp) {
		boolean valid = true;
		if(newProp.getPropertyName()==null) {
			valid = false;
		}
		if(newProp.getPropertyValue()==null) {
			valid = false;
		}
		return valid;
	}
}















