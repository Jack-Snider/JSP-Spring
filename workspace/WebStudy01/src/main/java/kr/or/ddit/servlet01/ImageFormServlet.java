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

//@WebServlet(value="/imageForm", loadOnStartup = 1)
public class ImageFormServlet extends HttpServlet{
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
		
		StringBuffer html = new StringBuffer();
		html.append(" <html>                                     ");
		html.append(" 	<body>                                   ");
		html.append(" 		<h4> 이미지 파일 선택</h4>           ");
		html.append(" 		<form action='"+req.getContextPath()+"/image'>        ");
		html.append(" 			<select name='name'>             ");
		html.append(options);
		html.append(" 			</select>                        ");
		html.append("<input type='submit' value='전송' />");
		html.append(" 		</form>                              ");
		html.append(" 	</body>                                  ");
		html.append(" </html>                                    ");

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println(html);
		}finally {
			if(out!=null)
				out.close();
		}
	}
}















