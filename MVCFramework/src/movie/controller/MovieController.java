/*
 * 
 * */

package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import movie.model.MovieAdvisor;

//MovieController �� controller �ڷ������� �������� 
public class MovieController implements Controller{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���� �ޱ� 
		request.setCharacterEncoding("utf-8");
		String movie = request.getParameter("movie");
		MovieAdvisor advisor = new MovieAdvisor();
		String msg=advisor.getAdvice(movie);
		
		//����� ���� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ� 
		//��� jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��� �ʿ䰡 �ִ�
		//����μ��� ���ǿ� ���� 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);	
	}
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}
