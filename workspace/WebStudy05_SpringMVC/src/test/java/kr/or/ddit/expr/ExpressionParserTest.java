package kr.or.ddit.expr;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressionParserTest {

	@Test
	public void expressionParsingTest2() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("key1", "textvalue");
		dataMap.put("key2", 23);
		dataMap.put("key3", 21);
		ExpressionParser parser = new SpelExpressionParser();
		String exprTxt = "key2-key3+key1.length()";
		Expression expr = parser.parseExpression(exprTxt);
		StandardEvaluationContext context = new StandardEvaluationContext(dataMap);
		context.addPropertyAccessor(new MapAccessor());
		Object value = expr.getValue(context);
		log.info("{} = {}", exprTxt, value);
	}
	
	
	@Test
	public void expressionParsingTest1() {
		ExpressionParser parser = new SpelExpressionParser();
		String exprTxt = "12+21";
		Expression expr = parser.parseExpression(exprTxt);
		Object value = expr.getValue();
		log.info("{} = {}", exprTxt, value);
	}
}
