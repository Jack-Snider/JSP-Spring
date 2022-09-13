package kr.or.ddit.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class MessageBundleRead {
	
	public static void main(String[] args) {
		
		/*
		 * 오늘의 주제 :	ibatis를 쓸떄 우리가 properties파일을 사용했는데
		 * 				과연 뭐가 이 properties파일을 읽어와서 그 내용들을 해석하고
		 * 				실행했을까?
		 * 
		 * Propertie파일을 읽기 위해 필요한 라이브러리 = ResourceBundle
		 * 
		 * 
		 * Properties는 Map과 같은 방식으로 쓴다
		 * 둘의 차이점은 Map은 휘발성이지만 Properties는
		 * PC가 꺼지더라도 사라지지 않는다.
		 * 
		 * 
		 * Properties와 ResourceBundle은 둘다 properties파일을 읽는데 사용된다.
		 * 하지만 둘의 가장 큰 차이점은 Properties는 리소스를 읽기 및 제어 할 수 있고
		 * ResourceBundle은 readonly이다.
		 * 
		 */
		
		// propertiesRead();
		
		/*
		 * baseName의 규칙
		 * 1.	확장자를 포함하지 않는다.
		 * 		/egovframework/message/com/message-common_ko.properties
		 * 		==>
		 * 		/egovframework/message/com/message-common_ko
		 * 
		 * 2.	Locale이 포함되지 않는다.
		 * 		/egovframework/message/com/message-common_ko
		 * 		==>
		 * 		/egovframework/message/com/message-common
		 * 		
		 * 
		 * 3.	슬래쉬 대신 .을 사용한다.
		 * 		/egovframework/message/com/message-common
		 * 		==> 
		 * 		egovframework.message.com.message-common
		 */		
		String baseName = "egovframework.message.com.message-common";
		
		// ResourceBundle은 readonly이다.
		ResourceBundle bundle = ResourceBundle.getBundle( baseName , Locale.ENGLISH );
		System.out.println( bundle.getString( "fail.common.msg" ) );
		
	}
	
	
	
	
	
	
	
	private static void propertiesRead() {
		Properties properties = new Properties();
		
		String path = "/egovframework/message/com/message-common_ko.properties"; // 실행파일의 경로
		
		
		try(
			// properties파일을 먼저 stream으로 읽어온다.
			InputStream is = MessageBundleRead.class.getResourceAsStream( path );		
			){
			properties.load( is );
			
			for( Entry<Object, Object> pro : properties.entrySet() ) {
				String key = Objects.toString( pro.getKey() );
				String value = Objects.toString( pro.getKey() );
				System.out.printf( "%s : %s \n" , key, value);
			}
			
		}catch( IOException e ) {
			System.err.println( e.getMessage() );
			throw new RuntimeException( e );
		}
		
	}
	
	
	
	
}
