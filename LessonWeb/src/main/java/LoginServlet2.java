import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet2")
//ServletとJSPの連携版
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// JSPで表示するメッセージ用変数
		String message = null;
		// リクエストからIDとPASSを取得
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		// IDとパスワードのチェック（固定値）
		if (id.equals("test") && pass.equals("0000")) {
			message = "ようこそ" + id + "さん！";
		} else {
			message = "IDまたはPASSが違います。";
		}
		// リクエストにmessageを保存
		request.setAttribute("message", message);
		// 遷移先JSPの設定と実際の送信
		RequestDispatcher dispach = request.getRequestDispatcher("jsp/result.jsp");
		dispach.forward(request, response);
	}

}
