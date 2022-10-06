package kr.or.ddit.designpattern.templatemethod;

import java.util.HashMap;
import java.util.LinkedHashMap;

import kr.or.ddit.designpattern.templatemethod.example2.SqlMapClient;
import kr.or.ddit.designpattern.templatemethod.example2.SqlMapClientImpl;

public class Example2Ground {
	public static void main(String[] args) {
		SqlMapClient client = new SqlMapClientImpl();
		client.queryForObject("selectMember", "a001", HashMap.class);
		client.queryForObject("selectProd", "P101000001", LinkedHashMap.class);
	}
}
