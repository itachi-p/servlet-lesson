

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DbAccessServlet3")
// DB接続確立→SQL発行(SELECT文)
public class DbAccessServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); // doPostの中でdoGetを呼んでも同じ
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// JDBCドライバのロード(クラスの指定)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Timezone=JSTだと存在しないためにエラー（バージョンによる違い？）
			String url = "jdbc:mysql://localhost/test?serverTimezone=Asia/Tokyo";
			// 厳密に言えばソースコード中に平文でMySQLのパスワードを記述するのはセキュリティ的に危険(しかもroot)
			Connection connection = DriverManager.getConnection(url, "root", "rootpass");
			// SQL文の設定
			String sql = "SELECT name FROM id_tbl WHERE id = '123456'";
			String name = null;
			PreparedStatement ps = connection.prepareStatement(sql);
			// SQL文の実行と結果の格納
			ResultSet rs = ps.executeQuery();
			
			// 検索結果の抽出（複数行取得にwhileループを回すが、今回はidを指定しているために検索結果は1行だけ）
			while(rs.next()) {
				// 文字列型データの取得はgetString, 数値型はgetInt, 日付データの場合getDate
				name = rs.getString("name");
			}
			
			// DBから取得した値をJSPに受け渡す
			request.setAttribute("message", "ID=123456の名前は「" + name + "」です");
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// JSPへの転送(前のJSPファイルの使いまわし)
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/disp.jsp");
		dispatch.forward(request, response);
	}
}
