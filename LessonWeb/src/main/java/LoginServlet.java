

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// リクエストの値を取得
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		// 全処理共通であるトップの見出しを表示
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>Login Servlet</title></head>");
		pw.println("<body><h2>ログイン画面</h2><br />");
		
		// 正当なユーザーであるかの判定（固定）
		if (user.equals("test") && pass.equals("pass")) {
			// TODO
			pw.println("とりあえずログイン成功");
		} else {
			pw.println("ユーザIDまたはパスワードが違います<br /><br />");
			pw.println("<a href=\"html/login.html\">ログイン画面へ</a>");
		}
	}

}
