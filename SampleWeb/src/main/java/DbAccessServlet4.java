

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DbAccessServlet4")
// DB接続確立→SQL発行(SELECT以外のDBレコードに変更を加えるSQL文の場合)
public class DbAccessServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); // doPostの中でdoGetを呼んでも同じ
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/test?serverTimezone=Asia/Tokyo";
			Connection connection = DriverManager.getConnection(url, "root", "rootpass");
			// SQL文の設定(プレースホルダを利用した複数レコード更新)
			String sql = "INSERT INTO id_tbl VALUES(?,?,?,?)"; // 実行時まで内容が確定していないSQL文
			String[] id = {"456789", "567890", "678901"};
			String[] password = {"pass4", "pass5", "pass6"};
			String[] name = {"原田次郎", "本田四郎", "齋藤六郎"};
			int[] age = {31, 32, 33};
			PreparedStatement ps = connection.prepareStatement(sql);
			for (int i = 0; i < id.length; i++) {
				ps.setString(1, id[i]);
				ps.setString(2, password[i]);
				ps.setString(3, name[i]);
				ps.setInt(4, age[i]);
				// SQL文の実行(1件づつ新規レコード追加)
				ps.executeUpdate(); // 2回目以降の実行は重複登録になるため結果はnullが返る
			}

			// SQL文の実行結果メッセージ(手動作成)をJSPに受け渡す
			request.setAttribute("message", id.length + "件追加しました");
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// JSPへの転送(再び以前のJSPファイルの使いまわし)
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/disp.jsp");
		rd.forward(request, response);
	}
}
