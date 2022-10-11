package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;

//@WebServlet("/calculate")
public class CalculateController{
   
   //같은 조건이라도 method를 보고 다르게 움직임.
   
   @RequestMapping(value="/calculate", method=RequestMethod.POST, 
         headers="content-type=application/json", produces="application/json")
   @ResponseBody
   public CalculateVO doPostJson(@RequestBody CalculateVO vo) throws ServletException, IOException {
      
      return vo;
   }
   
   @RequestMapping(value="/calculate", method=RequestMethod.POST)
   public void doPost(CalculateVO vo) throws ServletException, IOException {
      
      
   }
   
   //설정을 안하면 모든 do를 잡아서 처리함.
   // 지금상황에서의 ex) doPost를 제외한 모든 do~~~~~~~~
   
   @RequestMapping(value="/calculate")
   @ResponseBody
   public String doGet(int leftOp, int rightOp, OperatorType operator ) throws ServletException, IOException {

         // enum클래스인 operator의 getExpression 사용(연산을 처리한 후 String 데이터 반환)
         String expression = operator.getExpression(leftOp, rightOp);
         return expression;

      }
   }
   




