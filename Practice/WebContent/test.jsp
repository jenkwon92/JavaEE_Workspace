<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//데이터 베이스와 연결이 된다면 모든 설정이 완료된 것임
	InitialContext context = new InitialContext(); //JNDI 검색객체
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/mariadb"); //톰캣서버에 설정한 리소스 찾자
	
	//찾아낸 커넥션 풀을 이용하여 커넥션 한개를 끄집어내소 주소값이 나오면 성공
	Connection con = ds.getConnection();
	
	out.print(con);
%>