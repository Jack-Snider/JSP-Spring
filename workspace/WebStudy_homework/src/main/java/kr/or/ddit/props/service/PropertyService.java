package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

/**
 * Property 관리를 위한 Business Logic Layer( Service Logic Layers, Logic , Layer ) 
 * @author PC-04
 *
 *
 *
 *
 */
public interface PropertyService {
	
	// 프로미터 한쌍 태칭, 조회, 해당 쓰라ㅔ빠로 신고다니기라랍니다. )
	public PropertyVO readProperty( String propertyName );
	
	
	public List<PropertyVO> readProperList();
	
	public void createProperty( PropertyVO VO );
	
}
