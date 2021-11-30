/*
  기존의 jsp가 담당하고 있었던 컨트롤러로서의 업무를 현재 클래스로 분리 
  jsp는 순수한 디자인이 되기때문에 유지보수 시 교체 가능
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
		// 파라미터 받기
		String blood = request.getParameter("blood");
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
		
		// out.print(msg); //출력할 수 있지만, 컨트롤러에서 디자인을 담당하면 안됨
		// 결과에 대한 출력은 디자인인 View가 담당하므로, 이 서블릿에서 처리하면 안됨
		// 결과 jsp에서 메세지를 보여주려면, 서버의 메모리에 임시적으로 저장해놓아야 함
		// 현재는 세션에 담기 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		// 클라이언트로 하여금 지정한 url로 재접속을 유도. 명력하자		
		response.sendRedirect("/Practice1217/blood/blood_result.jsp");
	}	
}
