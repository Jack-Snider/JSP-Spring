package kr.or.ddit.login.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthentiationServiceImpl implements AuthenticationService {

	@Inject
	private MemberDAO dao;
	
	@Resource(name="passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Override
	public MemberVO authenticate(MemberVO inputData) {
		MemberVO saved = dao.selectMember(inputData.getMemId());
		if(saved==null)
			throw new UserNotFoundException(inputData.getMemId());
		String inputPass = inputData.getMemPass();
		String savedPass = saved.getMemPass(); // 암호화되어 저장된 비밀번호
		if(passwordEncoder.matches(inputPass, savedPass)) {
			return saved;
		}else {
			throw new BadCredentialException("비밀번호 오류");
		}
	}

}
