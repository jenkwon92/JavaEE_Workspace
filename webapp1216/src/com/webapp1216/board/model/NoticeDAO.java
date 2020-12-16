package com.webapp1216.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webapp1216.mybatis.config.MyBatisConfigManager;

public class NoticeDAO {
	MyBatisConfigManager manager = MyBatisConfigManager.getInstance(); //singleTon��ü���
	//CRUD
	public int insert(Notice notice) {
		int result = 0;
		//jdbc�� �ƴ� mybatis�� ������ ��������
		SqlSession sqlSession = manager.getSqlSession(); //������� ��ü (xml�� ������ ȣ���� �� �ִ�)
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();//DML�� ��� commitó��
		manager.close(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");
		manager.close(sqlSession);
		return list;
	}
	
	//�Ѱ� ��������
	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession = manager.getSqlSession();
		notice = sqlSession.selectOne("Notice.select",notice_id);
		manager.close(sqlSession);
		return notice;
	}
	
	//����
	public int update(Notice notice) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.update",notice);
		sqlSession.commit();//DML�̹Ƿ� commit();
		manager.close(sqlSession);
		return result; 
		
	}
	
	//���� 
	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = manager.getSqlSession();
		result = sqlSession.delete("Notice.delete",notice_id);
		sqlSession.commit();//DML�̹Ƿ� commit();
		manager.close(sqlSession);
		return result; 
	}
}