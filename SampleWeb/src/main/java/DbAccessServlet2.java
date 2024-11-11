

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DbAccessServlet2")
// DbAccessSample1.javaのサーブレット対応版(ブラウザ上にDB接続結果を表示する)
public class DbAccessServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 今回のケースでは基本的にdoGet()のみで問題なく動作するが、doPost()にも対応させ、そちらに実際の処理を書く
		doPost(request, response); // 逆にdoPostの中でdoGetを呼んでも同じ
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// JDBCドライバのロード(クラスの指定)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Timezone=JSTだと存在しないためにエラー（バージョンによる違い？）
			String url = "jdbc:mysql://localhost/test?serverTimezone=Asia/Tokyo";
			// 厳密に言えばソースコード中に平文でMySQLのパスワードを記述するのはセキュリティ的に危険(しかもroot)
			Connection connection = DriverManager.getConnection(url, "root", "rootpass");
			request.setAttribute("message", "DB Access OK!");
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// JSPへの転送
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/disp.jsp");
		dispatch.forward(request, response);
	}
}
