package br.com.alura.livraria.security;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityContext {
	
	public static String getPasswordHashAlgorithm() {
		return "SHA-512";
	}
	
	public static Charset getPasswordHashingCharset() {
		return StandardCharsets.UTF_8;
	}
	
	public static MessageDigest getPasswordMessageDigest() {
		try {
			return MessageDigest.getInstance(getPasswordHashAlgorithm());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
