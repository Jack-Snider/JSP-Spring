package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.tmpl")
public class TMPLServlet extends HttpServlet {

	/*
	 * 요청/응답 : service() 메소드 
	 * 1. init() 메소드는 최초에 한 번만 수행되고 요청은 스레드로 실행된다. 
	 * 2. 각각 service() 메소드를 통해 doGet()이나 doPost()로 분기된다. 
	 * 3. 파라미터로 HttpServletRequest와 HttpServletResponse 클래스 타입인 request와 response 객체가 제공되며, 
	 *    사용자 요청 처리는 request로, 응답 처리는 response 객체로 처리한다.
	 * 
	 * 
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			StringBuffer tmplSource = readTemplate(req); //?

			String html = evaluateVariable(req, tmplSource);

			sendResponse(resp, html);
			
		} catch (FileNotFoundException e) {
			resp.sendError( HttpServletResponse.SC_NOT_FOUND, e.getMessage() );
		}
		
		
	}
	// 정규식 문법 : https://hbase.tistory.com/160
	private final String REGEX = "#(\\w+)#"; // ?...

	// 정규식 하는 메소드
	private String evaluateVariable(HttpServletRequest req, StringBuffer tmplSource) {
		/*
		    각 클래스별 메소드에 대한 자세한 사항 : https://girawhale.tistory.com/77
		    
		 	Pattern
		 	- 정규 표현식이 컴파일된 클래스, 정규 표현식에 대상 문자열을 검증하거나
		 	  활용하기 위해 사용되는 클래스이다.
		 	  
		 	Matcher
		 	- Pattern 클래스를 받아 대상 문자열과 패턴이 일치하는 부분을 찾거나
		 	  전체 일치 여부 등을 판별하기 위해 사용된다.
		 	  
		 */
		
		// compile() -> 주어진 정규식을 갖는 패턴을 생성
		Pattern pattern = Pattern.compile( REGEX );
		

		StringBuffer htmlSource = new StringBuffer();
		
		// matcher() -> 패턴에 매칭할 문자열을 입력해 Matcher를 생성
		Matcher matcher = pattern.matcher(tmplSource);
		while (matcher.find()) {
			
			// group( int group ) ->	매칭되는 문자열 중 group번째 그룹의 문자열 반환
			//							0은 그룹의 전체 패턴을 의미 group( 0 ) = group()
			String varName = matcher.group( 1 );
			Object value = req.getAttribute(varName);
			matcher.appendReplacement(htmlSource, Objects.toString(value, ""));

		}
		// appendTail() -> 더 이상 정규식 할 문자열이 없으면 나머지 문자들을 이어 붙힘.
		matcher.appendTail(htmlSource);
		return htmlSource.toString();

	}

	// 응답 보내는 메소드
	private void sendResponse(HttpServletResponse resp, String contents) throws IOException {

		resp.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = resp.getWriter();) {

			out.println(contents);
		}

	}

	// 뭔가 경로 문자열을 가공하는 메소드인듯
	// 아까 만든 템플릿 읽어오는 메소드
	private StringBuffer readTemplate(HttpServletRequest req) throws IOException {
		String servletPath = req.getServletPath();

		InputStream is = getServletContext().getResourceAsStream(servletPath);
		if( is == null ) {
			throw new FileNotFoundException( String.format( "%s 해당 파일이 없음" , servletPath ) );
		}
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);

		String temp = null;

		StringBuffer tmplSource = new StringBuffer();
		while ((temp = reader.readLine()) != null) {
			tmplSource.append(temp + "\n");
		}
		return tmplSource;
	}

}