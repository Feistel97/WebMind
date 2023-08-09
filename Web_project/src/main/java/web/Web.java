package web;

public class Web {
	
	private int num;  //게시글 번호
	private String webTitle; //게시물 제목
	private String userID; //작성자 아이디
	private String date; //작성된 날짜
	private String webContent; //글 내용
	private int num1;//현재 글이 삭제되었는지 안되었는지 확인 1이면 삭제 안됨
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWebTitle() {
		return webTitle;
	}
	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWebContent() {
		return webContent;
	}
	public void setWebContent(String webContent) {
		this.webContent = webContent;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	
	
	
}
	