package com.practice.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.board.model.Notice;

public class RegistServlet extends HttpServlet{
	//�۾��⸦ ó���ϴ� �����̹Ƿ�, Ŭ���̾�Ʈ�� ��û�� post�� ����
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Ŭ���̾�Ʈ�� �Ķ���� �ޱ�*/
		request.setCharacterEncoding("utf-8"); //�Ķ���� ���ڵ� ó��
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//���� �±� ��밡��? no -jsp������ ������
		
		//vo�� ��� 
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//Ŭ���̾�Ʈ�� �������� ����� �����͸� ���䰴ü �ɾ����
	}
}
