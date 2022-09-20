package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet{
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String bodyContentType = req.getContentType();
      int statusCode = HttpServletResponse.SC_OK;
      
      if(!bodyContentType.contains("json")) {
         
         statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
         
         
         
      }
      
      String accept = req.getHeader("Accept");
      if(statusCode == 200 && !accept.contains("json")) {
         statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
      }
      
      if(statusCode == HttpServletResponse.SC_OK) {
         resp.setContentType("application/json;charset=UTF-8");
         try(
            InputStream is = req.getInputStream();
            PrintWriter out = resp.getWriter();
         ){
            ObjectMapper mapper = new ObjectMapper();
            CalculateVO vo = mapper.readValue(is, CalculateVO.class);
            mapper.writeValue(out, vo);
         }
      }else {
         resp.sendError(statusCode);
      }
      
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 파라미터 받아오기
      String left = req.getParameter("leftOp");
      String right = req.getParameter("rightOp");
      String opParam = req.getParameter("operator");
      
      System.out.println("left" + left);
      System.out.println("right" + right);
      System.out.println("op" + opParam);
      
      // 데이터 검증 상태 반환을 위한 응답 코드 설정
      int statusCode = HttpServletResponse.SC_OK;
      
      // enum 클래스 선언
      OperatorType operator = null;
      
      // opParam의 데이터가 정상적인 데이터가 아니라면 nullpointer exception이 생김
      // 비정상적 데이터라면 응답 코드에 오류코드 설정
      try {
         operator = OperatorType.valueOf(opParam);
      } catch (Exception e) {
         statusCode = HttpServletResponse.SC_BAD_REQUEST;
      }
      
      // 받아온 파라미터 검증 -> null, 공백확인, 정규식에 부합하는지( \d : 숫자 한글자, + : 하나 혹은 그이상 )
      // 비정상적 데이터라면 응답 코드에 오류코드 설정
      if(
            left == null || left.isEmpty() || !left.matches("\\d+")
         || right == null || right.isEmpty() || !right.matches("\\d+")
      ) {
         statusCode = HttpServletResponse.SC_BAD_REQUEST;
      }
      
      // statusCode가 SC_OK(200코드)이라면 데이터가 정상적으로 받아와졌다는 뜻
      // left, right 데이터 정수형 변환
      if(statusCode == HttpServletResponse.SC_OK) {
         int leftOp = Integer.parseInt(left);
         int rightOp = Integer.parseInt(right);
         
         // enum클래스인 operator의 getExpression 사용(연산을 처리한 후 String 데이터 반환)
         String expression = operator.getExpression(leftOp, rightOp);
         
         try(
            // try-with-resource 문으로 writer 선언(try문 종료시 자동으로 자원 해제)
            PrintWriter out = resp.getWriter();
         ){
            // 받아온 결과 출력
            resp.setContentType("text/html;charset=UTF-8");
            out.println(expression);
         }
         
      }else {
         // status 코드가 200이 아니라면 에러 출력
         resp.sendError(statusCode);
      }
   }
   
}