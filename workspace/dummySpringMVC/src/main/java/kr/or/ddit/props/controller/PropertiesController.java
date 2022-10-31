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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.vo.PropertyVO;

//@WebServlet({"/properties", "/property"})
@Controller
public class PropertiesController{
	@Inject
	private PropertyService service;
	
	@RequestMapping("/properties")
	@ResponseBody
	public  List<PropertyVO> properties() {
		return service.readProperties();
	}
	
	@GetMapping("/property")
	@ResponseBody
	protected PropertyVO doGet(@RequestParam(required=true) String name) throws ServletException, IOException {
		Object model = null;
		int statusCode = 200;
		return service.readProperty(name);
			
//			String message =(String) session.getAttribute("message");
//			session.removeAttribute("message"); // flash attribute
//			System.out.println(message);
	}
	
	@PostMapping("/property")
	public String doPost(@Valid PropertyVO newProp, Errors errors, RedirectAttributes redirectAttributes) 
			throws ServletException, IOException {
		
		
		if(!errors.hasErrors()) {
			service.createProperty(newProp);
			String message = "성공";
//			req.getSession().setAttribute("message", message);
			redirectAttributes.addFlashAttribute("message", message);
			String viewName = "redirect:/property?name="+newProp.getPropertyName();
			return viewName;
//			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			return "property/form";
		}
	}
}















