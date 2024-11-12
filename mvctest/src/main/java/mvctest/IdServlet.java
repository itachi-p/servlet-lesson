package mvctest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IdServlet")
public class IdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		SampleDao dao = new SampleDao(); // DB検索用モデルのインスタンスを生成
		// DAOのメソッドを呼び出し、戻り値をリクエストにセットする
		request.setAttribute("name", dao.getName());
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/id.jsp");
		dispatch.forward(request, response);
	}
}
