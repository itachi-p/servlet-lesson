 import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DispatchServlet2")
public class DispatchServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
		// リクエスト単位のスコープでJSPに変数を受け渡し
		request.setAttribute("data", "TEST DATA");
		// 画面遷移先を設定
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/dispatch2.jsp");
		// リクエストの転送(実際のレスポンスは転送先のJSPが返す)
		dispatcher.forward(request, response);
	}
}