package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();
	MemberVO member; 
	@Before
	public void setUp() {
		member = new MemberVO();
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemZip("000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemMail("aa@naver.com");
	}
	
	@Test
	public void testInsertMember() {
		int rowcnt = dao.insertMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
	}
	
	@Test
	public void testSelectMemberNotExist() {
		MemberVO member = dao.selectMember("asdfasdfasdfasdf");
		assertNull(member);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		assertNotNull(memberList);
		assertNotEquals(0, memberList.size());
		assertNotNull(memberList.get(0).getMemName());
	}

	@Test
	public void testUpdateMember() {
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}












