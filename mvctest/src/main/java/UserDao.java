import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// DbAccessクラスは使い回す
public class UserDao extends DbAccess {
	public String getName(String id, String pass) {
		// SQL文の準備
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
		return name;
	}
	
	// レコードの追加メソッド
	public void input(String id, String name, int age) {
		String sql = "INSERT INTO id_tbl(id, name, age) VALUES(?,?,?)";
		try {
			connect(); // DB接続を確立
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			// プレースホルダ('?' : INパラメータ、バインド変数とも呼ばれる)に値を設定
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			// INSERT文の実行(DBを更新するため、SELECTとはメソッドが違う)
//			int rs = ps.executeUpdate(); // 戻り値はDBのレコードに影響を与えた行数だが、使っていない
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(); // DB切断
		}	
	}
}
