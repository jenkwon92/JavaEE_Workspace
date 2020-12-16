package com.greeting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�����̶� ?�ڹ�Ŭ���� �� ���� ������������ �ؼ� �� ����Ǿ��� �� �ִ� Ŭ����
/*
 * ������ �����ֱ�:
�¾�� ����? ������ ��û�� ���� ��, �������̳�(��Ĺ)�� ���� �ν��Ͻ��� �޸𸮿� �ö��
                    �ν��Ͻ��� ������ ��, �������μ� ������ �� ������ ����̿��� �Ѱܹ��� �� �ִµ�,
                    �̶� ���Ǵ� �޼��尡 init() 
                    ��, ������ �¾ ��, �ʱ�ȭ �޼��忡 ���� ���� ������ ���Ե�*/
public class HelloServlet extends HttpServlet{
	
	// �� �޼���� ������ �¾ ���Ŀ�, �ʱ�ȭ �۾��� ����
	//����, �� �޼���� Tomcat�� ���� �������̳ʿ� ���� ȣ��ȴ�.
	//��, ������ ���� �� �����ֱ� �޼����� ȣ���ڴ� �ٷ� ��Ĺ�̴�
	/*
	 * public void init() throws ServletException { System.out.println("init() ȣ��");
	 * }
	 */
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String msg = config.getInitParameter("msg");
		System.out.println("init()ȣ��� �Ѱܹ��� �Ķ���� ������ "+msg);
		
		//jsp���尴ü �����ø����̼��� ������ ������ ���� ��ü ..application
		ServletContext context = config.getServletContext(); //jsp�� application���尴ü ����
		System.out.println(context.getRealPath("/"));
	}
	
	//������ �ϴ� ������ ��, �ʱ�ȭ���� ��ġ�� Ŭ���̾�Ʈ�� ��û�� ó���� �غ� �� ���̸�,
	//Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �޼��尡 �ٷ�, service�޼����̴�
	//���� �޼��尡 ��û�� ó���Ϸ���, Ŭ���̾�Ʈ�� ��û������ Ŭ���̾�Ʈ���� ���� ���������� �ʿ���ϱ� ������ 
	//service() �޼����� �Ű������� request, response��ü�� ���޵Ǿ���� (��Ĺ����)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ŭ���̾�Ʈ�� ������ ��û���� �� �Ķ���͸� ������ ���� ����غ���
		String id = request.getParameter("id");
		
		//Ŭ���̾�Ʈ�� ����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("����� ������ �Ķ���ʹ� "+id);
	}
	
	//������ �Ҹ��� �� ȣ��Ǵ� �޼���
	//������ ������ �ڿ��� �ݳ��� �� � ���(��Ʈ��, db����)
	public void destroy() {
		System.out.println("�� �׾��̤�");
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("Hello servlet do!");
	}
}
