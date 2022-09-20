package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.props.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.props.dao.FileSystemPropertyDAOImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
//	HighCohesionLooseCoupling
//	private PropertyDAO dao = new FileSystemPropertyDAOImpl();
	private PropertyDAO dao = new DataBasePropertyDAOImpl();

	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo==null)
			throw new PKNotFoundException(propertyName);
		return vo;
	}

	@Override
	public List<PropertyVO> readProperties() {
		List<PropertyVO> dataList = dao.selectProperties();
		dataList.forEach((vo)->{
			if(vo.getDescription()==null)
				vo.setDescription("라뽂이 만들었음.");
		});
		return dataList;
	}

	@Override
	public void createProperty(PropertyVO vo) {
		dao.insertProperty(vo);
	}

}















