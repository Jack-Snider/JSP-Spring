package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDAOImplTest {
//	private static final  Logger log = LoggerFactory.getLogger(MemberDAOImplTest.class);
	
	MemberDAO dao = new MemberDAOImpl();
	
	MemberVO member; 
	@Before
	public void setUp() {
		member = new MemberVO();
		member.setMemId("b003");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemZip("000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemMail("aa@naver.com");
	}
	@Test(expected = PersistenceException.class)
	public void testInsertMember() {
		int rowcnt = dao.insertMember(member);
		log.info("rowcnt : {}", rowcnt);
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		log.info("memId : {}", member.getMemId());
		log.info("memBir : {}", member.getMemBir());
		log.info("prodList : {}", member.getProdList());
	}

	@Test
	public void testSelectMemberList() {
		PagingVO<MemberVO> pagingVO = new PagingVO<MemberVO>();
		pagingVO.setCurrentPage(1);
		List<MemberVO> memberList = dao.selectMemberList(pagingVO);
		log.info("memberList : {}", memberList);
	}

	@Test
	public void testUpdateMember() {
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember(member.getMemId());
		assertEquals(1, rowcnt);
	}

}













