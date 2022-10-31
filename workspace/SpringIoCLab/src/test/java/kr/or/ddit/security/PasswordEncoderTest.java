package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void encrptTest() {
		String password = "java";
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(password);
		System.out.println(encoded);
		
		String saved = "{bcrypt}$2a$10$6r0EtH7hm7K7PB8MszQwUe54n9/5xReeNWnyBmnN2XdoLfCrgCMt.";
		System.out.println("인증 여부 : "+encoder.matches(password, saved));
	}
}
