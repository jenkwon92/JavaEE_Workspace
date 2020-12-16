/*
 * mybatis의 config.xml은 xml일 뿐 현재 실행중인 자바 어플리케이션과는 상관없는 상태이다
 * 따라서 자바코드에서 config.xml을 읽어들여야 한다
 * 최종목표: xml을 읽어들여서 실제 쿼리문을 수행해주는 객체인 SqlSession 얻기 위함
 * 이 클래스는 특히 new 할 떄 마다 인스턴스가 생성될 것이고, 그렇게되면 SqlSessionFactory로 다수가
 * 메모리에 올라오므로, 메모리 누수가 될것임... 따라서 SingleTon으로 정의하자*/
package com.webapp1216.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfigManager {
	String resource = "com/webapp1216/mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MyBatisConfigManager instance; //전세계개발자들이 사용하는 습관(싱글톤임을 쉽게 알 수 있음)
	
	private MyBatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static MyBatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MyBatisConfigManager(); //아무리 private이라도 나의 생성자는 내가 호출가능함
		}
		return instance;
	}
	
	//SqlSession을 반환하는 메서드
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		if(sqlSession != null) {			
			sqlSession.close();
		}
	}
}
