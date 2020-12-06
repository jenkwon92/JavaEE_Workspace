/*
 * DAO��? 
 *-Data Access Object�� �ǹ��ϴ� ���ø����̼��� ����о� ���
 *-Data Access �� �����ͺ��̽����� CRUD �۾��� �����Ѵٴ� �ǹ�
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

	// ���뼺 ������� ���� swing���� ���� �ۼ�
	// insert�� �� �Ѱ� ~ �ϳ��� VO
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // �� ��� �� �� ����� ����

		con = dbManager.getConnection();

		String sql = "INSERT INTO notice(author, title, content) VALUES(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor()); // �ۼ���
			pstmt.setString(2, notice.getTitle()); // ����
			pstmt.setString(3, notice.getContent()); // ����

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	// ��� ���ڵ� ��������!
	public ArrayList<Notice> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>(); // rs�� ��ü�� �༮

		String sql = "SELECT * FROM notice order by notice_id desc";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// rs�� ���ڵ尡 ������ �̹Ƿ�, �� ������ �̹Ƿ�, VO���� �������� �ʿ��ϵ�
			// �� VO�� �Ѳ����� ��Ƽ� ��ȯ�ؾ��ϹǷ� ������ �ڷ����� �ʿ��ϴ�!
			// ��ü�� ��Ƴ��� ������ �����ϴ� �����ӿ��� CollectionFramework�̹Ƿ�,
			// �� �� �ϳ��� api�� �̿��غ���

			while (rs.next()) {
				Notice notice = new Notice(); // �ֺ� empty������ vo ����
				// notice�� rs�� ������ ���~ �Űܽ���
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));

				// notice������ ������� ����, �� list�� ����
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return list; // rs�� �ƴ� ArrayList�� ��ȯ����
	}

	// �Խù� 1�� ��������(�󼼺���)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null; // rs��� ������ 1���� ���� ��ü

		String sql = "SELECT * FROM notice WHERE notice_id=?";

		con = dbManager.getConnection(); // ���Ӱ�ü ���
		try {
			pstmt = con.prepareStatement(sql); // �����غ�
			pstmt.setInt(1, notice_id); // ���ε� ������ ����
			rs = pstmt.executeQuery();// ��������

			// ���� ź���� rs�� �� �״´� ���� rs�� ��ü�� ��ü�� �ʿ��ϴ�
			// rs�� ���ڵ� �Ѱ��� ��� ��ü�̹Ƿ�, ���ڵ� 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����
			if (rs.next()) {// ���ڵ尡 �����ϴ� ����! ���� �̶� VO�� �ø���!
				notice = new Notice(); // �ֺ� empty������ vo ����
				// notice�� rs�� ������ ���~ �Űܽ���
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}

			// ��ȸ�� ����
			sql = "UPDATE notice SET hit=hit+1 WHERE notice_id=?";
			pstmt = con.prepareStatement(sql); //���� pstmt�� ���������� �ϳ��� �ʿ��� 
			pstmt.setInt(1, notice_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}

	// �Խù� 1�� ����
	// �߸𸦰�� ��ȯ�� void�� �صΰ� �������� �����ص���
	public int update(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "UPDATE notice SET author=?, title=?, content=? WHERE notice_id=?";

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql); // �غ�
			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			result = pstmt.executeUpdate(); // ���� ����

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	// �����ϱ�
	public int delete(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE  FROM notice WHERE notice_id=?";
		int result = 0;

		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql); // �غ�
			pstmt.setInt(1, notice_id);
			result = pstmt.executeUpdate(); // ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
