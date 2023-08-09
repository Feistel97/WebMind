package util;

import java.security.MessageDigest;

public class SHA256 {
	
	public static String getSHA256(String input) { //이메일 값에 해쉬를 적용한 값을 가져와 이용한다
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hoseo Web Progreming Web Project 5team".getBytes(); //salt를 이용하여 해쉬값을 공격을 힘들게 만들어 준다.
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for ( int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
