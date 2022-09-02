package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Stream 이용한 IO
 * @author PC-04
 *
 * Stream : 연속성을 지닌 일반화 데이터 집합 이면서
 *          동시에 데이터를 읽거나 쓰기 위한 단방향( *** ) 통로
 *
 * 스트림을 사용할 100ek 단계
 * 스트림을 사용한 IO 단계
 *    1. media(매체)를 어플리케이션에서 제어할 수 있는 객체의 형태로 생성.
 *       ex) new File(filesystem Path), Socket, memory
 *    2. 1차 스트림을 매체에 연결.
 *       ex) new FileInputStream(file) *    
 *       
 *	  3. 2차 스트림을 먼저 1차 스트림에 연결
 *		 ex) new BufferedInputSpream( input youare name \        
 *
 *	  ***	
 *	  1차와 2차의 구분법은 1차는 매개변수가 경로나 파일 값이고 2차의 매개변수는
 *    그 1차의 객체이다.
 *
 *
 * stream 종류
 * 1. 전송 데이터 크기
 *		1) byte stream
 *		2) charactor stream
 *
 * 2. stream 생성 방법에 따라
 * 		1) 1차 stream
 * 		2) 2차 stream( 연결한  스트림 ) 
 */
public class StreamDesc {

	public static void main(String[] args) throws IOException, URISyntaxException {
		//readAndPrintClassPathResource();
		readAndWriteToFileSystemResource();
	}
	
	
	private static void readAndWriteToFileSystemResource() throws URISyntaxException, IOException {
		
		// 내가 받아올 주소
		String logo = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png";
		
		// 내가 저장할 주소
		String filePath = "D:\\contents\\googleLogo.png";
		
		/*
		 * URI -> 자원의 위치뿐만 아니라 자원에 대한 고유 식별자로서 URL을 의미를 포함
		 * URL -> 자원이 실제 존재하는 위치를 가리킨다.
		 */
		
		URI logoURI = new URI( logo );
		URL logoURL = logoURI.toURL();
		
		// step 1
		File file = new File( filePath );
		
		try(
		// 1차 스트림 생성
		InputStream is = logoURL.openStream();
		FileOutputStream fos = new FileOutputStream( file );
		
		BufferedInputStream bis = new BufferedInputStream( is );
		BufferedOutputStream bos = new BufferedOutputStream( fos );
		){
			
			//byte[] b =new byte[ 512 ];
			int len = 0;
			
			while( ( len = bis.read() ) != -1 ) {
				bos.write( len );
			}
			
			
			
			
		}
	}
	
	private static void readAndPrintClassPathResource() throws IOException {
		
		//String resCpPath = "/kr/or/ddit/medias/오래된 노래_utf8.txt"; // 논리 주소
		String resCpPath = "/kr/or/ddit/medias/오래된 노래.txt"; // 논리 주소
	      try (
	            // step2
	            InputStream fis = StreamDesc.class.getResourceAsStream(resCpPath);
	            // step3
	            InputStreamReader isr = new InputStreamReader(fis, "MS949" );
	            BufferedReader br = new BufferedReader(isr);
	      ){
	            String temp = null;
	            // step4
	            while((temp = br.readLine()) != null) {
	               System.out.println(temp);
	            }
	      } 

		
		
		
	}
	
	private static void readAndPrintFilesSystemResource() throws IOException {
		String resPath = "D:\\contents\\another day.txt";
		
		// step 1
		File file = new File( resPath );
		
		// try with resource 문법
//		try(
//				Closable 객체 생성. -> 자동으로 finally가 추가되고 , close됨
//			){
//			
//		} catch( Exception e ) {
//			
//		}
		
		try(
				// 이 안에 들어가는 객체들은 Closeable 인터페이스를 상속 받고 있어야 한다.
					
				// step 2
				FileReader reader = new FileReader( file );
				
				// step 3
				BufferedReader br = new BufferedReader( reader );
			){
			
				String temp = null;
				while( ( temp = br.readLine() ) != null ) {
					System.out.println( temp );
				}
		}
		
		
		// step 4
		
		// step 5
//		br.close();
//		reader.close();
		
	}
	
}
