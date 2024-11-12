package mvctest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleDao extends DbAccess {
	
	public String getName() {
		// SQL文は型変換で自動的に「id = '123456'」となる
		String sql = "SELECT name FROM id_tbl WHERE id = 123456";
		String name = null;
		try {
			connect(); // DB接続
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); // SELECT文の実行
			while(rs.next()) {
				name = rs.getString("name");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(); // 切断
		}
		return name; // DBから取得した名前を返す
	}
}
