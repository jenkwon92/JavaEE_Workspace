package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {
	
	//throws�� ����޼��忡�� �ش翹�ܸ� ó������ �ʰ�, �� �޼��带 ȣ���� �ڿ��� ���ѱ�� ��
	public void insert() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement("inset");
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		ExApp app = new ExApp();
		app.insert();
	}
}
