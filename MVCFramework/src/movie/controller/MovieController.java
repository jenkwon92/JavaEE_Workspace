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

//MovieController 를 controller 자료형으로 정의하자 
public class MovieController implements Controller{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 
		request.setCharacterEncoding("utf-8");
		String movie = request.getParameter("movie");
		MovieAdvisor advisor = new MovieAdvisor();
		String msg=advisor.getAdvice(movie);
		
		//결과에 대한 출력은 디자인인 View가 담당하므로, 이 서블릿에서 처리하면 안된다 
		//결과 jsp에서 메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해놓을 필요가 있다
		//현재로서는 세션에 담자 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);	
	}
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}
