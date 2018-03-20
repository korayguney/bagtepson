package com.bagtep.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {
	
	public static String hashPassword(String password){
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			password = new String(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	
	
}
