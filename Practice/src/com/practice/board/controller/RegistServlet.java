package com.practice.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.board.model.Notice;

public class RegistServlet extends HttpServlet{
	//글쓰기를 처리하는 서블릿이므로, 클라이언트의 요청이 post로 들어옴
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*클라이언트의 파라미터 받기*/
		request.setCharacterEncoding("utf-8"); //파라미터 인코딩 처리
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//빈즈 태그 사용가능? no -jsp에서만 가능함
		
		//vo에 담기 
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//클라이언트의 브라우저에 출력할 데이터를 응답객체 심어놓기
	}
}
