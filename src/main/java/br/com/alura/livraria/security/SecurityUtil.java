package br.com.alura.livraria.security;

import java.security.MessageDigest;
import java.util.Base64;

public class SecurityUtil {

	public static String hashPassword(String password) {
		MessageDigest digest = SecurityContext.getPasswordMessageDigest();
		byte[] hash = digest.digest(password.getBytes(SecurityContext.getPasswordHashingCharset()));
		return Base64.getEncoder().encodeToString(hash);
	}
	
	
	
}
