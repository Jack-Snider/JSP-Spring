package kr.or.ddit.method;

import java.util.HashMap;
import java.util.Map;

public class MethodCallDesc {
	
	public static void main(String[] args) {
		int number = 10;
		String str = "original";
		StringBuffer sb = new StringBuffer( "original" );
		Map<String, Object> map = new HashMap<>();
		
		numberCount( number ); // call by value
		System.out.println( number );
		
		stringAppend( str );
		System.out.println( str );
		
		stringBufferConcat( sb );
		System.out.println( sb );
		
		mapAppend( map );
		System.out.println( map );
		
	}
	
	private static void numberCount( int num ) {
		num = num + 1;
	}
	
	private static void stringAppend( String str ) {
		str = str + "APPEND";
	}
	
	private static void stringBufferAppend( StringBuffer sb ) {
		sb.append( "APPEND" );
	}
	
	private static void stringBufferConcat( StringBuffer sb ) {
		sb = new StringBuffer( sb + "APPEND" );
	}
	
	private static void mapAppend( Map<String, Object> map ) {
		map.put( "key" , "value" );
	}
	
}
