<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp"%>

<%
	String qna_id = request.getParameter("qna_id");
	QnADAO qnaDAO = new QnADAO();
	
	int result = qnaDAO.delete(Integer.parseInt(qna_id));

	if(result == 0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제성공","/qna/list.jsp"));
	}
%>






