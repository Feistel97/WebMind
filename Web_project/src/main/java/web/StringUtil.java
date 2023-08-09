package web;

public class StringUtil {
	public static String nvl(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	public static String nvl(String str, String repl) {
		if (nvl(str).equals("")) {
			return repl;
		}
		return str;
	}
}
