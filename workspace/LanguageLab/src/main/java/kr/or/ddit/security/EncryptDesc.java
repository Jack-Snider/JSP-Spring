package kr.or.ddit.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Encoding(부호화, decoding) - 전송이나 저장을 목적으로 매체를 이용하는 경우, 매체가 인지할 수 있는 방식으로 데이터의 표현 방식을 바꾸는 과정.
 * 	ex) %2T (percent encoding, URL encoding), base64
 * 한글->-0101010101, img src="data:image/png;base64,asdfassadfdasd"
 * Encrypting(암호화, decrypting) - 허가받지 않은 공격자의 도청(위변조)를 막기 위해 데이터의 표현 구조를 변경하는 과정.
 * 		단방향 암호화(해시함수) - 원래 평문 복원이 불가능함. 비밀번호 암호화
 * 			hashing - 데이터를 식별 할 수 있는 고정길이 코드값(해시코드)를 만드는 과정.
 * 		양방향 암호화 - key의 소유자가 평문 복원이 가능.
 * 			대칭키 방식 - 하나의 비밀키로 암복호화, 전송 데이터 암호화
 * 			비대칭키 방식 - 한쌍(공개키, 개인키)의 키로 암복호화, 전자 서명
 *
 */
public class EncryptDesc {
	public static void main(String[] args) throws Exception{
		String plain = "평문 데이터";
		aesSample(plain);
	}
	
	public static void rsaSample(String plain) throws Exception {
		KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
		pairGen.initialize(2048);
		KeyPair pair = pairGen.generateKeyPair();
		PublicKey publicKey = pair.getPublic();
		PrivateKey privateKey = pair.getPrivate();
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
	 	byte[] decoded = Base64.getDecoder().decode(encoded);
	 	cipher.init(Cipher.DECRYPT_MODE, publicKey);
	 	byte[] decrypted = cipher.doFinal(decoded);
	 	String original = new String(decrypted);
	 	System.out.println(original);
	}
	
	public static void aesSample(String plain) throws Exception{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey key = keyGen.generateKey();
		String ivValue = "초기화벡터";
		MessageDigest hasingAPI = MessageDigest.getInstance("MD5");
		byte[] iv = hasingAPI.digest(ivValue.getBytes());
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
		// 저장되어있던 암호문 복원
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded);
		String original = new String(decrypted);
		System.out.println(original);
	}
	
	public static void sha512Sample(String plain) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] input = plain.getBytes();
		byte[] encrypted = md.digest(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
		String savedPassword = "R2YcKsoqIoBmScGVpNiHgaQmA5AtS3Y+WmjUTT9xZ67CAKkrhB4+fynpA3p2/3iayqE1Lo+d/6igmtOjab3bvw==";
		System.out.println("인증 성공 여부 : "+ savedPassword.equals(encoded) );
	}
	
	public static void encodingSample(String plain) throws UnsupportedEncodingException {
		String encoded = URLEncoder.encode(plain, "UTF-8");
		System.out.println(encoded);
		String decoded = URLDecoder.decode(encoded, "UTF-8");
		System.out.println(decoded);
	}
}














