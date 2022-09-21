package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
