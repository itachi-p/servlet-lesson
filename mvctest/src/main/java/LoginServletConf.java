

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServletConf")
public class LoginServletConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// リクエストからIDとパスワードを取得
		String id = request.getParameter("id");
		String 	pw = request.getParameter("pass");
		// DAOをインスタンス化(作成手順的にはこの後でクラスを作成するので未存在)
		UserDao dao = new UserDao();
		// リクエストにDAOで取得したユーザー名称をセットする(ログイン判定もDAO内部で行う)
		request.setAttribute("login_name", dao.getName(id, pw));
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/loginconf.jsp");
		dispatch.forward(request, response);
	}
}
