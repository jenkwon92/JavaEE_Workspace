<%@ page import="board.model.QnA"%>
<%@ page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//넘겨받은 파라미터 값을 이용하여 답글 달자
	//답글을 달기 위한 쿼리문을 알아야 한다
	//DAO 에서 수행할거지만, 일단 이해를 위해 여기에 적겠다
	request.setCharacterEncoding("utf-8");

	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String team = request.getParameter("team"); //내가본글  team
	String rank = request.getParameter("rank"); //내가본글  rank
	String depth = request.getParameter("depth"); //내가본글  depth
	
	//넘겨받은 파라미터드를 하나의 VO에 보관해두자 
	QnA qna = new QnA();
	
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	qna.setTeam(Integer.parseInt(team));
	qna.setRank(Integer.parseInt(rank));
	qna.setDepth(Integer.parseInt(depth));
	//1단계: 후발로 등록된 글이 들어갈 자리 확보(기존 글들을 밀어내는 효과)
	/* String sql ="UPDATE  qna SET rank = rank+1 WHERE team = "+team+" and rank >"+rank;
	out.print(sql);
	out.print("<br>");
	
	//2단계: 내가 본 글의 바로 아래쪽에 답변 insert
	sql = "INSERT INTO qna(team,rank,depth) VALUES("+team+","+(rank+1)+","+(depth+1)+")";
	out.print(sql); */
	
	QnADAO dao = new QnADAO();
	int result = dao.reply(qna);
	if(result == 0){
		out.print(getMsgBack("답변등록실패"));
	}else {
		out.print(getMsgURL("답변이 등록되었습니다", "/qna/list.jsp"));
	}
%>