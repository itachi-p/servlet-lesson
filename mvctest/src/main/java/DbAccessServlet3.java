

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DbAccessServlet3")
public class DbAccessServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// セッションの取得
		HttpSession session = request.getSession(true);
		// JSP側のsubmitボタンの値によって処理分け_分岐1
		if(request.getParameter("submit").equals("確認")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			// JSPへ移動した際の画面を指定する
			request.setAttribute("top1", "確認画面へ"); // 実際は値がnullか否かしか判別しない
			// リクエストから取得した各種データをセッションに保存
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("age", age);
		}
		// JSP側のsubmitボタンの値によって処理分け_分岐2
		if(request.getParameter("submit").equals("登録")) {
			// JSPへ移動した際の画面を指定する
			request.setAttribute("top2", "登録画面へ"); // 実際は値がnullか否かしか判別しない
			// 登録ボタンが押される際にはリクエストに値が入っていない為、セッションから取得する
			String id = (String)session.getAttribute("id");
			String name = (String)session.getAttribute("name");
			int age = (int)session.getAttribute("age");
			// DBアクセス
			UserDao dao = new UserDao();
			dao.input(id, name, age); // 記述時点ではメソッド未存在
		}
		// 画面遷移(登録完了後の確認画面へ)
		RequestDispatcher rd = request.getRequestDispatcher("jsp/input.jsp");
		rd.forward(request, response);
	}
}
