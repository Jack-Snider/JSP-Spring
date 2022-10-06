package kr.or.ddit.props.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet({"/properties", "/property"})
public class PropertiesControllerServlet extends HttpServlet{
	private PropertyService service = new PropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String servletPath = req.getServletPath();
		Object model = null;
		int statusCode = 200;
		HttpSession session = req.getSession();
		if("/properties".equals(servletPath)) {
			model = service.readProperties();
		}else {
			String name = req.getParameter("name");
			if(name==null  || name.isEmpty())
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
			else
				model = service.readProperty(name);
			String message =(String) session.getAttribute("message");
			session.removeAttribute("message"); // flash attribute
			System.out.println(message);
		}
		if(statusCode==200) {
			req.setAttribute("model", model);
			String viewName = "/jsonView.do";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(statusCode);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		PropertyVO newProp = new PropertyVO();
//		newProp.setPropertyName(req.getParameter("propertyName"));
		
		try {
			BeanUtils.populate(newProp, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		boolean valid = validate(newProp);
		if(valid) {
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















