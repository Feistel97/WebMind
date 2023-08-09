package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {

	public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID()); // 유저 id,password,Email을 받아와 데이터를 데이터베이스로 넘겨준다.
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate(); //실제로 받은 데이터를 그대로 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return -1; //userID에 기본키가 걸려있어 ID가 중복 될경우 회원가입 실패하여 -1을 반환
	}
	
	public int login(String userID, String userPassword) { //로그인시 데이터베이스에 있는지 확인
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";//사용자 ID에 패스워드를 불러와 SQL에 넣어준다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //데이터를 조회하여 결과를 확인할때 사용 하는 함수/ 참이면 1을 반환한다.
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공시 1반환
				}
				else {
					return 0; // 로그인 실패시 0을 반환
				}
			}
			return -1; // 아이디가 존재를 하지 않을경우 -1을 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return -2;// 데이터베이스 오류가 발생할경우 -2반환
	}
	
	public boolean getUserEmailChecked(String userID) { //로그인시 유저 id와 password를 받아와 데이터베이스에서 
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";//셀렉트를 이용하여 같은 문자가 있는지 확인한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //데이터를 조회하여 결과를 확인할때 사용 하는 함수
			if(rs.next()) {
				return rs.getBoolean(1); // boolean 임으로 True, false로 반환을 받는다. 있을경우 1을 반환하여 True를 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;// false경우 데이터베이스 오류
	}
	
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";//셀렉트를 이용하여 같은 문자가 있는지 확인한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //데이터를 조회하여 결과를 확인할때 사용 하는 함수
			if(rs.next()) {
				return rs.getString(1); // 값이 있을경우 문자형태로 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return null;// 데이터베이스 오류
	}
	
	public String getUserID(String userEmail) {
		String SQL = "SELECT userID FROM USER WHERE userEmail = ?";//셀렉트를 이용하여 같은 문자가 있는지 확인한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery(); //데이터를 조회하여 결과를 확인할때 사용 하는 함수
			if(rs.next()) {
				return rs.getString(1); // 값이 있을경우 문자형태로 반환
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return null;// 데이터베이스 오류
	}
	
	
	public boolean setUserEmailChecked(String userID) { //로그인시 유저 id와 password를 받아와 데이터베이스에서 
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";//셀렉트를 이용하여 같은 문자가 있는지 확인한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;// false경우 데이터베이스 오류
	}
	
	public int ChagePassword(String userEmailHash, String userPassword) {
		String SQL = "UPDATE user SET userPassword = ? WHERE userEmailHash = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userPassword); 
			pstmt.setString(2, userEmailHash);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } //자원 공간을 사용을 자원할당 해제
			try { if(pstmt != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return 0; //userID에 기본키가 걸려있어 ID가 중복 될경우 회원가입 실패하여 -1을 반환
	}
}