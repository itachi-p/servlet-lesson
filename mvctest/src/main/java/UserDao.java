import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.HumanBean;

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
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(); // DB切断
		}
	}

	// 全件検索メソッド
	public ArrayList<HumanBean> getList() {
		// Beansを格納するリストの宣言
		ArrayList<HumanBean> list = new ArrayList<>();
		// 全件検索のSQL文を記述
		String sql = "SELECT id, name, age FROM id_tbl ORDER BY id";
		// DB接続を確立
		connect();

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Beanの生成
				HumanBean bean = new HumanBean();
				// 生成したBeanにDBから取得したレコード1行の値をセット
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				// レコード1行分のBeanをリストに格納
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		// Beansを格納したリストを返す
		return list;
	}
	
	// 指定idのレコード1件を検索
	public HumanBean getRecord(String id) {
		HumanBean bean = new HumanBean();
		String sql = "SELECT id, name, age FROM id_tbl WHERE id = ?";
		connect();
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return bean;
	}
	
	// レコードを1件DBから削除
	public void delete(String id) {
		String sql = "DELETE FROM id_tbl WHERE id = ?";
		connect();
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// レコードを1件更新(上書き)
	public void update(String id, String name, int age) {
		String sql = "UPDATE id_tbl SET name= ?, age = ? WHERE id = ?";
		connect();
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
}
