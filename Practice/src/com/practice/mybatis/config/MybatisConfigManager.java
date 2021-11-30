package com.practice.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	String resource = "com/practice/mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MybatisConfigManager instance;
	
	private MybatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MybatisConfigManager(); //나의 생성자는 내가 호출가능
		}
		return instance;
	}

	//SqlSession을 반환하는 메서드
	public SqlSession getSqlSession() {
		SqlSession sqlSession= null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		if(sqlSession!=null) {	
			sqlSession.close();
		}
	}
}
