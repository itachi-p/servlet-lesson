package mvctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; // DBドライバ
	private static final String DB_URL = "jdbc:mysql://localhost/test?serverTimezone=Asia/Tokyo"; // DBパス
	private static final String DB_USER = "root"; // ユーザID
	private static final String DB_PWD = "rootpass"; // パスワード
	private Connection connection = null; // DBコネクション保持用オブジェクト
	
	public Connection getConnection() {
		return this.connection;
	}
	public void connect() {
		try {
			Class.forName(DB_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
		try {
			if(this.connection != null) {
				this.connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.connection = null;
		}
	}
}
