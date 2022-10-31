package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class TextEncryptTest {
	String password = "java";
	String salt = "소금";
	
	@Test
	public void encryptTest() {
		String plain = "평문데이터";
		
		String hexSalt = new String(Hex.encode(salt.getBytes()));
		System.out.println(hexSalt);
		TextEncryptor encryptor = Encryptors.text(password, hexSalt);
		String encoded = encryptor.encrypt(plain);
		System.out.println(encoded);
		
		String original = encryptor.decrypt(encoded);
		System.out.println(original);
	}
}
