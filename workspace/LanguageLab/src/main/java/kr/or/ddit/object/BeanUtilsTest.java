package kr.or.ddit.object;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsTest {
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<>();
		map.put( "prop1", "VALUE1" );
		map.put( "prop2", "VALUE2" );
		
		// ----- 방법 1 -----
		// 위의 데이터를 2가지 방법으로 VO에 담아본다.
		TestVO vo = new TestVO(); // 붕어빵 틀을 메모리에 올림
		
		
		// 어떤 클래스의 인스턴스에 대해 추적하고 분석하는 것이 Reflection이라고 한다.
		// Reflection의 딱 1가지 전제조건은 불확실성을 기준으로 한다.
		
		
		// 메모리에 할당된 클래스로더를 가져오기 ( 붕어빵 틀에 대한 정보를 가져옴 , 붕어빵을 하나 사오고 그 틀에 대한 정보를 분석하는 것과 같음 )
		//vo.getClass(); 
		
		// 이렇게도 클래스로더를 가져올수 있음
		//TestVO.class.
		
		Class<? extends TestVO> clz = vo.getClass(); // clz에는 상태와 행동에 대한 정보가 들어있다.( 클래스와 메소드의 형태로 )
		
		//Field[] fields = clz.getFields(); // 해당 클래스의 필드를 Field 클래스의 배열로 반환함.
		// 근데 위와 같이하면 public필드만 접근할 수 있어서 안나옴. 우리는
		// private이라 다른 메소드 사용해야함.
		
		// 이 녀석은 접근 private, public, protected, default 전부 접근 가능
		Field[] fields = clz.getDeclaredFields(); 
		
		for( Field fid : fields ) {
			String fidName = fid.getName(); // getName()은 필드의 정보중에서 이름만 가져온다.
			System.out.println( fidName );
			
			// setAccessible은 필드에 대한 접근권한을 설정하는 것.
			// true을 주게 되면 원래 private이었던 필드도 public으로 바뀌어버림
			fid.setAccessible( true );  
			
			try {
				fid.set( vo,  map.get( fidName ) );
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println( vo );
		
		
		
		// ----- 방법 2 -----
		// vo를 다시 초기화해서 property에는 아무것도 없을것이다.
		vo = new TestVO();
		for( Entry<String, Object> entry : map.entrySet() ) {
			System.out.println( entry.getKey() );
			String key = entry.getKey();
			Object value = entry.getValue();

			try {
				Field fid = clz.getDeclaredField( key );
				//vo.setProp1( "VALUE1" );+9
				String setterName= "set" + key.substring(0,1).toUpperCase() + key.substring( 1 ); // set으로 시작하는 메소드만 추출하기 위해서
				Method setter = clz.getDeclaredMethod( setterName , value.getClass() ); // 해당 이름의 메소드를 찾아라
				setter.invoke( vo , value ); // invoke는 메소드를 불러오는 것, ( 불러올 객체, 파라미터 )
					
			} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println( vo );
		
		//----- 방법 3 -----
		try {
			BeanUtils.populate( vo, map );
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println( vo );
		
		
	}
	
	
}
