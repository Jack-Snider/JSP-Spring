package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 특정 사용자를 대상으로 blind 처리.
 *
 */
public class BlindFilter implements Filter{
	
	/**
	 *  key : 사용자 IP, value : 사유
	 */
	private Map<String, String> blindUsers;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blindUsers = new LinkedHashMap<>();
		blindUsers.put("127.0.0.1", "그냥 나니까 차단");
		blindUsers.put("0:0:0:0:0:0:0:1", "역시 나니까 차단");
		blindUsers.put("192.168.143.3", "역시 나니까 차단");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
//		블라인드 대상자 여부
//		여 : 에러페이지 제공(reason).
//		부 : 통과 및 서비스 제공
		String ip = req.getRemoteAddr();
		String uri = req.getServletPath();
		String errorPage = "/errors/errorMessage.jsp";
		boolean pass = ! blindUsers.containsKey(ip) || errorPage.equals(uri) ;
		if(pass) {
			chain.doFilter(request, response);
		}else {
			String reason = blindUsers.get(ip);
			req.getSession().setAttribute("reason", reason);
			resp.sendRedirect(req.getContextPath() + errorPage);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}





















