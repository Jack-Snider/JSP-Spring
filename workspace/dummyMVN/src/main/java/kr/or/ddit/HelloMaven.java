package kr.or.ddit;

import java.io.IOException;
import java.io.InputStream;

public class HelloMaven {
	
	public static void main(String[] args) throws IOException {
		System.out.println( "Hello Maven" );
	
		try( 
				InputStream is = HelloMaven.class.getResourceAsStream( "" );
			){
			
		}
		
		
	}
	
}
