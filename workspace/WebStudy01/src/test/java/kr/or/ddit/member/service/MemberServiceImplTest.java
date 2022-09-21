package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.commons.exception.UserNotFoundException;

public class MemberServiceImplTest {

	MemberService service = new MemberServiceImpl();
	
	@Test
	public void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test(expected = UserNotFoundException.class)
	public void testRetrieveMember() {
		service.retrieveMember("dfasdfasdfasdf");
	}

	@Test
	public void testRetrieveMemberList() {
		assertNotNull(service.retrieveMemberList());
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}
