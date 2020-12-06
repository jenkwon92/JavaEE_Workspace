<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.*"%>
<%@ include file="/inc/lib.jsp"%>
<%
	String notice_id = request.getParameter("notice_id"); //웹에서 주고받는 모든 데이터는 문자
	NoticeDAO noticeDAO = new NoticeDAO();
	
	int result = noticeDAO.delete(Integer.parseInt(notice_id)); //DML수행

	if(result==0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제성공","/board/list.jsp"));
	}
%>



	