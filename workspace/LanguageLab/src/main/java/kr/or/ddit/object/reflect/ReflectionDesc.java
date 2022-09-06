package kr.or.ddit.object.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * 리플렉션( Reflection )이란?
 * 	:	객체로부터 타입, property, method 등 해당 객체의 구조를 추측해 가는 과정.
 * 		java.lang.reflect 패키지의 API 들로 지원.
 * 		Class, Field, Method, Parameter... 
 * @author PC-04
 *
 */
public class ReflectionDesc {
	
	public static void main(String[] args) {
		
		Object retValue = ReflectionTest.getObject();
		//System.out.println( retValue );
		
		/*
		 * 미션
		 * mem_hp의 값을 자신의 휴대폰 번호로 바꾸기
		 * 
		 */
		
		System.out.println( retValue.getClass() ); 
		
		Class<? extends Object> clz = retValue.getClass();
		
		// 메소드 이름 가져오기
		Method[] method = clz.getDeclaredMethods();

		
		// 가져오고나서 출력
		for( Method m : method ) {
			String mName = m.getName(); // getName()은 필드의 정보중에서 이름만 가져온다.
			System.out.println( mName );
			
		}
		
		String setterName = "setMem_hp";
		
		try {
//			Method setter = clz.getDeclaredMethod( setterName ,  String.class );
//			setter.invoke( retValue, "010-5223-5124" ); // 주인객체( retValue ), 세팅할 데이터
//			
//			// 해당 필드의 값을 다시 확인하고자, 게터를 가져온다.
//			String getterName = "getMem_hp";
//			Method getter = clz.getDeclaredMethod( getterName ); // getter는 파라미터가 없어서 두번째 인자는 생략
//			getter.invoke( retValue ); // 마찬가지로 파라미터가 없음
//			
//			// 가져온 메소드를 사용한다. ( 파라미터 : 주인객체 )
//			// 모든 타입의 조상은 Object임으로 반환받을 변수의 타입을 Object로 한다.
//			Object fidValue = getter.invoke( retValue ); 
//			System.out.println( fidValue );
			
//------------------------------------------------------------------------------------------------------------------------------------------------------
			
			// 방법 2
			PropertyDescriptor pd = new PropertyDescriptor( "mem_hp" , clz ); // 파라미터 : 가져올 필드, 주인객체 ( 클래스 타입 )
			
			Method setter = pd.getWriteMethod(); // getWriteMethod는 반환받을 녀석( pd )의 setter가 된다.
			setter.invoke( retValue, "010-5223-5124" );
			
			Method getter = pd.getReadMethod(); // getReadMethod는 반환받을 녀석( pd )의 getter가 된다.
			Object fldValue = getter.invoke( retValue );
			System.out.println( fldValue );
			
			
			
			
		} catch (SecurityException  | IllegalArgumentException | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
