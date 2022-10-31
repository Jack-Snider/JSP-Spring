package kr.or.ddit.props.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.props.dao.FileSystemPropertyDAOImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

@Service
public class PropertyServiceImpl implements PropertyService {
//	HighCohesionLooseCoupling
	private PropertyDAO dao;

//	@Inject
	public PropertyServiceImpl(PropertyDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo==null)
			throw new RuntimeException(propertyName);
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















