package kr.or.ddit.object.marshalling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.object.TestVO;

/**
 * Marshalling / UnMarshalling
 * 

 * 
 * json / xml - 이기종 시스템간 메세지 교환시 사용되는 '공통 데이터 표현 방식' ( 인간의 언어로 치면 '영어'와 같은 공용어라고
 * 생각하면 된다. ) ( 원래는 xml이 대세였는데 너무 길고 무거워서 더 가볍고 간단한 json으로 옮기는 추세 )
 * 
 * -------------------------------------- Marshalling ----------------------
 * Marshalling : native data -> JSON / XML ( Native한 언어들( Java, Python , 등등.. )을
 * JSON이나 XML으로 바꾸는 행위 ) , Marshalling 구조에 대해 이해하려면 필수적으로 알고있어야 하는 개념이 직렬화( Serialization )이다.
 * 
 * UnMarshalling : JSON / XML -> Native data
 * ---------------------------------------------------------------------
 * 
 * 
 * -------------------------------------- Serialization -----------------------------
 * Serialization : 전송이나 저장의 목적으로 객체의 상태를 byte
 * array( bit stream )로 변환하는 과정 ( JSON으로 번역을 하고 그걸 보내려면 이진 데이터( 0과 1 )로 바꿔야 하는데
 * 이렇게 바꾸는 과정을 직렬화라고 한다.
 * 
 * DeSerialization : byte array( bit stream ) 으로부터 객체의 상태를 복원하는 과정 ( 0과 1의 데이터 ( bit stream )을 객
 * 체 상태의 데이터로 복원하는 과정 )
 * ---------------------------------------------------
 * 
 * ------------------------------------- 전체 프로세스 -------------------------------------
 *  native data ------> JSON /XML 	------> byte array 	------> JSON /XML 	------> native data
				Marshalling 		Serialization		DeSerialization 	UnMarshalling
 * 
 * 
 * native data	<------ JSON /XML	<------ byte array	<------ JSON /XML	<------ native data 
 * 				UnMarshalling	 	UnSerialization 	Serialization 		Marshalling 
 * --------------------------------------------------------------------------------------
 * 
 * 
 * 
 * @author PC-04
 *
 */
public class JSONMarshallingDesc {

	public static void main(String[] args) throws JsonProcessingException {

		TestVO vo = new TestVO();
		vo.setProp1("VALUE1");
		vo.setProp2("VALUE2");

//		serialize( vo );
//		deSerialize();

//		String json = marshalling( vo );
//		TestVO vo2 = unMarshalling( json, TestVO.class );

//		System.out.println( vo2 );
		
		
		
		
		
	}

	// 데이터 전송 data transfer
	private static void transfer( Object target ) {
		
		// Marshalling
		String json = marshalling( target );
		
		// Serialize
		serialize( json );
		
	}
	
	
	// UnMarshalling
	private static <T> T unMarshalling( String json , Class<T> javaType ) {
		// 제네릭으로 두번째 매개변수로 들어오는 클래스타입의 객체를 반환한다.
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue( json,  javaType ); // javaType은 반환할 클래스 타입
		} catch( IOException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	// Marshalling
	private static String marshalling( Object target ) {
		try {			
			// JSON객체를 자유럽게 다루는 클래스
			ObjectMapper mapper = new ObjectMapper();
			
			// Marshalling
			String json = mapper
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString( target );
			
			return json;
			
		}catch( IOException e ) {
			throw new RuntimeException( e );
		}
	}

	// 반직렬화
	private static void deSerialize() {
		// 역직렬화 1단계
		File objFile = new File("d:/contents", "obj.dat");

		try (
				// 역직렬화 2단계
				// 1차 스트림
				FileInputStream fis = new FileInputStream(objFile);

				// 2차 스트림
				ObjectInputStream ois = new ObjectInputStream(fis);

		) {

			// 역직렬화 3단계
			Object obj = ois.readObject();
			System.out.println(obj);

		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e); // 원래 에러 파라미터를 넘겨준다.
		}
	}

	// 직렬화
	private static void serialize(Object target) {
		// --------------------- 직렬화 작업 ---------------------
		File objFile = new File("d:/contents", "obj.dat");

		try (

				// 1차 스트림
				FileOutputStream fos = new FileOutputStream(objFile);

				// System.out : 1차 스트림
				// 2차 스트림이면서 객체를 직렬화를 시킬수 있다. ( Object이기 때문에 )
				ObjectOutputStream oos = new ObjectOutputStream(fos); // System.out
		) {
			oos.writeObject(target); // ??
		} catch (IOException e) {
			// 예외를 강제로 발생시키기
			throw new RuntimeException(e);
		}

//----------------------------------------------------------------------------
	}

}
