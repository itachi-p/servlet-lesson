import java.sql.PreparedStatement;
import java.sql.ResultSet;
// DbAccessクラスは使い回す
public class UserDao extends DbAccess {
	public String getName(String id, String pass) {
		// SQL文の準備
		String sql = "SELECT name FROM id_tbl WHERE id='" + id + "' AND password='" + pass + "'";
		// 変数の宣言
		String name = null;
		try {
			// DBに接続するConnectionオブジェクトを取得(DB接続を確立する)
			connect();
			// Connectionオブジェクトを取得し、ステートメントを作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			// SQLを発行し、検索結果から取得したユーザ名を変数へ代入
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// ユーザ名を返す
		return name;
	}
}
