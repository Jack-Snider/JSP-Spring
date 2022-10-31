package kr.or.ddit;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

public class HelloMaven {
	public static void main(String[] args) throws IOException {
		System.out.println("hello maven");
		try(
			InputStream is = HelloMaven.class.getResourceAsStream("");	
		){
		}
	}
}
