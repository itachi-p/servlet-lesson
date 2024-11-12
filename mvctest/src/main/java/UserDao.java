import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// DbAccessクラスは使い回す
public class UserDao extends DbAccess {
	public String getName(String id, String pass) {
		// SQL文の準備
		//String sql = "SELECT name FROM id_tbl WHERE id='" + id + "' AND password='" + pass + "'";
		// プレースホルダ(INパラメータ)を使ったやり方
		String sql = "SELECT name FROM id_tbl WHERE id=? AND password=?";
		// 変数の宣言
		String name = null;
		try {
			// DBに接続するConnectionオブジェクトを取得(DB接続を確立する)
			connect();
			// Connectionオブジェクトを取得し、ステートメントを作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			// ID,passwordのプレイスホルダに値を設定
			ps.setString(1, id);
			ps.setString(2, pass);
//			System.out.println(id + pass);
			// SQLを発行し、検索結果から取得したユーザ名を変数へ代入
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// ユーザ名を返す
//		System.out.println("name:" + name);
		return name;
	}
}
