/*
  ������ jsp�� ����ϰ� �־��� ��Ʈ�ѷ��μ��� ������ ���� Ŭ������ �и� 
  jsp�� ������ �������� �Ǳ⶧���� �������� �� ��ü ����
  */
package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blood.model.BloodAdvisor;

public class BloodController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ķ���� �ޱ�
		String blood = request.getParameter("blood");
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		// out.print(msg); //����� �� ������, ��Ʈ�ѷ����� �������� ����ϸ� �ȵ�
		// ����� ���� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵ�
		// ��� jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��ƾ� ��
		// ����� ���ǿ� ��� 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		// Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ����. �������		
		response.sendRedirect("/Practice1217/blood/blood_result.jsp");
	}	
}
