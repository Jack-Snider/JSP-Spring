package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerUpdate.do")
public class BuyerUpdateServlet extends HttpServlet{
	private BuyerService service = new BuyerServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		
		req.setAttribute("command", "UPDATE");
		
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String buyerId = req.getParameter("what");
		
		if(StringUtils.isBlank(buyerId)) {
			resp.sendError(400);
			return;
		}
		
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		req.setAttribute("buyer", buyer);
		
		String viewName = "buyer/buyerForm";
		viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		Map<String, String> errors = new ValidateUtils<BuyerVO>().validate(buyer, UpdateGroup.class);
		req.setAttribute("errors", errors);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case OK:
				logicalViewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				break;

			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "buyer/buyerForm";
				break;
			}
		}else {
			logicalViewName = "buyer/buyerForm";
		}
		viewResolve(logicalViewName, req, resp);
	}
	
}












