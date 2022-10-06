package kr.or.ddit.servlet01;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/imageForm", loadOnStartup = 1)
public class ImageFormServlet_version3 extends HttpServlet{
	private ServletContext application;
	private String imageFolderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolderPath = application.getInitParameter("imageFolderPath");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		File imageFolder = new File(imageFolderPath);
		String[] children = imageFolder.list((file, name)->{
			String mime = application.getMimeType(name);
			return mime!=null && mime.startsWith("image");
		});
		
		String pattern = "<option>%s</option>\n";
		StringBuffer options = new StringBuffer();
		for(String image : children) {
			options.append(String.format(pattern, image));
		}
		
		
		req.setAttribute("cPath", req.getContextPath());
		req.setAttribute("options", options);
		
		req.getRequestDispatcher("/02/imageForm.jsp").forward(req, resp);

	}
}















