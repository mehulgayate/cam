package com.cam.utility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Base64;

import thep.paillier.PrivateKey;

public class EncryptionUtility {

	public static PrivateKey privkey = new PrivateKey(128);

	public String encriptString(String program,String key){
		
		byte[] raw;
        String encryptedString;
        SecretKeySpec skeySpec;
        byte[] encryptText = program.getBytes();
        Cipher cipher;
        try {
            raw = Base64.decodeBase64(key);
            skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
	}
	
	public String decryptString(String program, String key){
		
		Cipher cipher;
        String encryptedString;
        byte[] encryptText = null;
        byte[] raw;
        SecretKeySpec skeySpec;
        try {
            raw = Base64.decodeBase64(key);
            skeySpec = new SecretKeySpec(raw, "AES");
            encryptText = Base64.decodeBase64(program);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            encryptedString = new String(cipher.doFinal(encryptText));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        System.out.println("decrepted string "+encryptedString);
        return encryptedString;
		
	}
}
