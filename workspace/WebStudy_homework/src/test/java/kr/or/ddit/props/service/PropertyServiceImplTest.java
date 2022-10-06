package kr.or.ddit.props.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImplTest {
	
	PropertyService service = new PropertyServiceImpl();

	@Test
	public void testReadPropertyExist() {
		PropertyVO vo = service.readProperty("prop1");
		assertNotNull(vo);
	}
	
	@Test(expected = PKNotFoundException.class)
	public void testReadPropertyNotExist() {
		PropertyVO vo = service.readProperty("prop1asdfas");
	}

	@Test
	public void testReadProperties() {
		List<PropertyVO> dataList = service.readProperties();
		assertNotNull(dataList);
		assertNotEquals(0, dataList.size());
		assertNotNull(dataList.get(0).getDescription());
	}

	@Test
	public void testCreateProperty() {
//		Builder Pattern
		PropertyVO vo = new PropertyVO();
		vo.setPropertyName("prop4");
		vo.setPropertyValue("value4");
		
		service.createProperty(vo);
	}

}















