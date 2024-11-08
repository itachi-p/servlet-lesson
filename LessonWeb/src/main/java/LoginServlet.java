

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// リクエストの値を取得
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		// セッションの取得
		HttpSession session = request.getSession(true);
		String storeUser = (String)session.getAttribute("user");
		String storePass = (String)session.getAttribute("pass");
		
		// 最初の1回はセッションに正しい(ログイン可能な)ユーザー名とパスワード(固定)を登録
		if (storeUser == null) {
			storeUser = "test"; // 固定値
			session.setAttribute("user", storeUser);
		}
		if (storePass == null) {
			storePass = "pass"; // 固定値
			session.setAttribute("pass", storePass);
		} // ※ログイン情報は固定値なので、ユーザー入力値はログイン時の判別のみでセッションへの登録は行わない
		
		// 入力値が正しいユーザとパスワードならばログインOK
		boolean isLoginOk = (storeUser.equals(user) && storePass.equals(pass));
		
		// 共通処理部分であるトップの見出しまでを表示
		PrintWriter pw = response.getWriter();
		// login.htmlでDOCTYPE宣言をした場合、全体で統一しないとレイアウトがずれるので注意
		pw.println("<html><head><meta charset=\"UTF-8\"><title>ログイン結果画面</title></head>");
		pw.println("<body><h1>ログイン画面</h1>");
		
		// 正当なユーザーであるかの判定（固定）
		if (isLoginOk) {
			pw.println("ようこそ" + user + "さん<br><br><br>");
			pw.println("<a href=\"UserInfoServlet\">ユーザ情報を見る</a>"); // 相対パス
		} else {
			pw.println("ユーザIDまたはパスワードが違います<br /><br />");
			pw.println("<a href=\"html/login.html\">ログイン画面へ</a>"); // 相対パス
		}
	}
}
