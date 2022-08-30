package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/*
 	서블릿에는 init, service, destroy 세개의 함수가 정의 되어있다.
 	
 	Tips.
 	
 	생명주기 메소드들은 다음과 같다.
 	
 	init() -> 서블릿 초기화를 담당하고 초기에 한 번만 실행된다.
 	
 	service()
 	doGet()
 	doPost()
 	그 외 doXXX..() 함수들
 	
 	-> 요청/응답을 담당하며 스레드를 통해 동시에 실행된다.
 	
 	destroy() ->	서블릿 종료를 담당하고 종료할 때 한 번만 실행 된다.
 					1. 컨테이너로부터 서블릿 종료 요청이 있을 때 destroy() 메소드를 호출한다.
 					2. 서블릿이 종료되면서 정리해야 할 작업이 있을 때는 destroy()메소드를 오버라이딩해서 구현하면 된다.
 */


@SuppressWarnings("serial") // ?..
@WebServlet(value = "/imageForm", loadOnStartup = 1) // ?..
public class ImageFormServlet_version2 extends HttpServlet{
   
   /*
	
	서블릿 컨테이너란?
	1. 서블릿들의 생성, 실행, 파괴를 담당한다.
	2. 서블릿들을 위한 상자( Container ) 이다.
	
   */	
   private ServletContext application; //  서블릿 컨테이너와 통신하기 위해서 사용되는 메소드를 지원하는 인터페이스
   private String imageFolderPath; // 이미지 폴더의 경로를 저장할 변수
   
   /*
    
     서블릿 초기화 : init() 메소드
     1. 클라이언트 요청이 들어오면 컨테이너는 해당 서블릿이 메모리에 있는지 확인한다.
     2. 해당 서블릿이 메모리에 없을 경우, 서블릿을 메모리에 다시 적재해야 한다.
     3. 이때, init() 메소드가 호출되며 각종 초기화 작업을 수행
     4. 해당 서블릿에 각각의 스레드에서 공통적으로 사용하기 위해 필요한 작업이 있다면 init() 메소드를
        오버라이딩 해서 구현한다.
     
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      application = getServletContext(); // 페이지에 대한 서블릿 실행 환경 정보를 담고 있는 application 내장 객체를 리턴한다.
      
      // getInitParameter( 매개변수 ) -> 미리 web.xml에 지정해둔 파라미터들의 값을 얻어올 수 있다. 
      imageFolderPath = application.getInitParameter( "imageFolderPath" );
   }
   
   

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
      File imageFolder = new File(imageFolderPath); // imageFolderPath경로로 파일객체 생성
      
      String[] children = imageFolder.list((file, name)->{
         String mime = application.getMimeType(name);
         return mime != null && mime.startsWith("image");
      });
      
      String pattern = "<option>%s</option>";
      StringBuffer options = new StringBuffer();
      for(String image : children) {
         options.append(String.format(pattern, image));
      }
      
      /* 
       	getContextPath() -> 프로젝트 path만 얻어온다.
       	예) : http://localhost:8081/board/list.jsp
 		반환값) : /board   	
       */
      req.setAttribute( "cPath" ,  req.getContextPath() );
      req.setAttribute( "options" ,  options );
      req.getRequestDispatcher( "/01/imageForm2.tmpl" ).forward( req, resp );
      /*
       
       1. forward 
       		- 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다. ( 파라미터를 넘길 수 있다. )
       		- 상대방에게 페이지 주소를 숨길 때 사용할 수 있으며, redirect보다 성능이 좋다.
       		- URL은 바뀌지 않으며, 내부에서만 접근이 가능하다.
       
       
       2. redirect
       		- 다른 페이지로 넘어가도록 한다. ( 직접 파라미터를 넘길 수 없다. )
       		- 요청받게되면 url을 클라이언트에게 전달하고, 클라이언트가( web ) 새로운 url을 요청하고
       		  그에 따른 응답을 한다.
       		- URL값이 넘어가기 때문에 길이에 제한이 있다.
       		- 즉, 성격이 다른 페이지 서블릿이 그 길을 알려주고 브라우저가 값을 받아오게 하는 것이다.
       
       */
      
   }
}