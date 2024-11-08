

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			// セッションの取得
			HttpSession session = request.getSession(true);
			// セッションからユーザー情報を取得
			String user = (String)session.getAttribute("user");
			String pass = (String)session.getAttribute("pass");
			
			// HTMLを構成
			PrintWriter pw = response.getWriter();
			pw.println("<html><head><title>ログイン情報</title></head>");
			pw.println("<body><h1>ログイン情報</h1>");
			
			pw.println("ユーザ名は" + user + "ですね。<br />");
			pw.println("パスワードは" + pass + "ですね。<br><br><br>");
			pw.println("<a href=\"html/login.html\">ログイン画面へ</a>"); // 相対パス
			pw.println("</body></html>");
			pw.close();
	}

}
