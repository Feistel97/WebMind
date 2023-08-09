package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WebDAO {

	private Connection conn;
	private ResultSet rs;
	
	//�⺻ ������
	public WebDAO() {
		try {
			String dbURL = "****";
			String dbID = "****";
			String dbPassword = "****";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//�ۼ����� �޼ҵ�
	public String getDate() {
		String sql = "select now()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //�����ͺ��̽� ����
	}
	
	//�Խñ� ��ȣ �ο� �޼ҵ�
	public int getNext() {
		//���� �Խñ��� ������������ ��ȸ�Ͽ� ���� ������ ���� ��ȣ�� ���Ѵ�
		String sql = "select num from web order by num desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; //ù ��° �Խù��� ���
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	
	//�۾��� �޼ҵ�
	public int write(Web web) {
		String sql = "insert into web(webTitle, userId, date, webContent, num1) values(?, ?, ?, ?, 1)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, web.getWebTitle());
			pstmt.setString(idx++, web.getUserID());
			pstmt.setString(idx++, getDate());
			pstmt.setString(idx++, web.getWebContent());
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
    
	public ArrayList<Web> getList(int pageNumber){
		String sql = "select * from web where num < ? and num1 = 1 order by num desc limit 10";
		ArrayList<Web> list = new ArrayList<Web>();
		try {
			String output = sql;
			output = output.replaceFirst("[?]", pageNumber + "");
			System.out.println(output);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Web web = new Web();
				web.setNum(rs.getInt(1));
				web.setWebTitle(rs.getString(2));
				web.setUserID(rs.getString(3));
				web.setDate(rs.getString(4));
				web.setWebContent(rs.getString(5));
				web.setNum1(rs.getInt(6));
				list.add(web);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//����¡ ó�� �޼ҵ�
	public boolean nextPage(int pageNumber) {
		String sql = "select * from web where num < ? and num1 = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Web getWeb(int num) {
		String sql = "select * from web where num = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Web web = new Web();
				web.setNum(rs.getInt(1));
				web.setWebTitle(rs.getString(2));
				web.setUserID(rs.getString(3));
				web.setDate(rs.getString(4));
				web.setWebContent(rs.getString(5));
				web.setNum1(rs.getInt(6));
				return web;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(Web web) {
		String sql = "update web set webTitle = ?, webContent = ? where num = ?";
		try {
			String output = sql;
			output = output.replaceFirst("[?]", String.format("'%s'", web.getWebTitle()));
			output = output.replaceFirst("[?]", String.format("'%s'", web.getWebContent()));
			output = output.replaceFirst("[?]", String.format("%d", web.getNum()));
			System.out.println(output);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, web.getWebTitle());
			pstmt.setString(2, web.getWebContent());
			pstmt.setInt(3, web.getNum());
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	
	//�Խñ� ���� �޼ҵ�
	public int delete(int num1) {
		//���� �����͸� �����ϴ� ���� �ƴ϶� �Խñ� ��ȿ���ڸ� '0'���� �����Ѵ�
		String sql = "DELETE FROM web WHERE num = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num1);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
}