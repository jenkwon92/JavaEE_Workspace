<%@page import="java.io.File"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//넘겨받은 board_id를 이용하여 삭제 
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");

	out.print("지우게 될 게시물은 board_id는 "+board_id);
	
	//삭제업무 (DB삭제 + 파일삭제)
	
	//파일 삭제(파일을 다룰 수 있는 클래스: java.io.File)
	File file = new File("D:/workspace/javaee_workspace/BoardApp/WebContent/data/"+filename);
	if(file.delete()){
		//db삭제
		ImageBoardDAO dao = new ImageBoardDAO();
		dao.delete(Integer.parseInt(board_id)); //db삭제
		out.print(getMsgURL("삭제처리되었습니다. ","/imageboard/list.jsp"));
	}else{
		out.print(getMsgBack("삭제되지 않았습니다."));
		
	}

%>