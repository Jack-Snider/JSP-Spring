package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.CalculateVO;

public class CalculateController{
	
	
	@RequestMapping(value="/calculate", method=RequestMethod.POST
					, headers="content-type=application/json"
					, produces="application/json")
	@ResponseBody
	public CalculateVO doPostJson(@RequestBody CalculateVO vo) throws ServletException, IOException {
		return vo;
	}
	
	@RequestMapping(value="/calculate")
	@ResponseBody
	public String doPost(CalculateVO vo) throws ServletException, IOException {
		return vo.getExpression();
	}
	
}


















