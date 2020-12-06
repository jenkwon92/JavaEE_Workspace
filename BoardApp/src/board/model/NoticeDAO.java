/*
 * DAO란? 
 *-Data Access Object를 의미하는 어플리케이션의 설계분야 용어
 *-Data Access 란 데이터베이스와의 CRUD 작업을 전담한다는 의미
 *CRUD(Create=insert Read=select Update Delete)
 * */

package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();

	// 재사용성 고려하지 않은 swing만의 로직 작성
	// insert는 글 한건 ~ 하나의 VO
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // 글 등록 후 그 결과값 보관

		con = dbManager.getConnection();

		String sql = "INSERT INTO notice(author, title, content) VALUES(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor()); // 작성자
			pstmt.setString(2, notice.getTitle()); // 제목
			pstmt.setString(3, notice.getContent()); // 내용

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	// 모든 레코드 가져오기!
	public ArrayList<Notice> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>(); // rs를 대체할 녀석

		String sql = "SELECT * FROM notice order by notice_id desc";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// rs는 레코드가 복수개 이므로, 즉 여러개 이므로, VO또한 여러개가 필요하도
			// 이 VO를 한꺼번에 모아서 반환해야하므로 집합형 자료형이 필요하다!
			// 객체를 모아놓은 집합을 지원하는 프레임웍은 CollectionFramework이므로,
			// 이 중 하나의 api를 이용해본다

			while (rs.next()) {
				Notice notice = new Notice(); // 텅빈 empty상태의 vo 생성
				// notice에 rs의 정보를 모두~ 옮겨심자
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				// notice변수가 사라지기 전에, 얼른 list에 담자
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return list; // rs가 아닌 ArrayList를 반환하자
	}

	// 게시물 1건 가져오기(상세보기)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null; // rs대신 데이터 1건을 담을 객체

		String sql = "SELECT * FROM notice WHERE notice_id=?";

		con = dbManager.getConnection(); // 접속객체 얻기
		try {
			pstmt = con.prepareStatement(sql); // 쿼리준비
			pstmt.setInt(1, notice_id); // 바인드 변수값 지정
			rs = pstmt.executeQuery();// 쿼리실행

			// 지금 탄생한 rs는 곧 죽는다 따라서 rs를 대체할 객체가 필요하다
			// rs는 레코드 한건을 담는 객체이므로, 레코드 1건을 담아 전달용으로 사용되는 VO를 이용하자
			if (rs.next()) {// 레코드가 존재하는 것임! 따라서 이때 VO를 올리자!
				notice = new Notice(); // 텅빈 empty상태의 vo 생성
				// notice에 rs의 정보를 모두~ 옮겨심자
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}

			// 조회수 증가
			sql = "UPDATE notice SET hit=hit+1 WHERE notice_id=?";
			pstmt = con.prepareStatement(sql); //원래 pstmt는 쿼리문마다 하나씩 필요함 
			pstmt.setInt(1, notice_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}

	// 게시물 1건 수정
	// 잘모를경우 반환을 void로 해두고 마지막에 변경해도됨
	public int update(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "UPDATE notice SET author=?, title=?, content=? WHERE notice_id=?";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql); // 준비
			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			result = pstmt.executeUpdate(); // 쿼리 수행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	// 삭제하기
	public int delete(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE  FROM notice WHERE notice_id=?";
		int result = 0;

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql); // 준비
			pstmt.setInt(1, notice_id);
			result = pstmt.executeUpdate(); // 수행
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
