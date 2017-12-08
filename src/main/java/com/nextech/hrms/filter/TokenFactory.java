package com.nextech.hrms.filter;

import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.nextech.hrms.model.Employee;

@Component
public class TokenFactory {

	public static SecretKeySpec getSecretKeySpec() {
		String key = "lk8345ndkfdgclk8";
		byte[] secretCode = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretCode, "AES");
		return secretKeySpec;
	}

	public static String createAccessJwtToken(Employee employee) throws Exception {
		if (StringUtils.isEmpty(employee.getUserid()))
			throw new IllegalArgumentException(
					"Cannot create JWT Token without username");

		if (employee.getSalary() == null || employee.getEmailid().isEmpty())
			throw new IllegalArgumentException(
					"User doesn't have any privileges");

		String s = employee.getUserid() + "-" + employee.getPassword() + "-"
				+ new Date().getTime();
		return encrypt(s, getSecretKeySpec());
	}

	public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}