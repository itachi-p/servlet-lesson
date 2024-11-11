import java.sql.Connection;
import java.sql.DriverManager;

// 通常のJavaアプリケーションでDB接続確認(Webサーバ不要)
public class DbAccessSample1 {
	public static void main(String[] args) {
		try {
			// JDBCドライバのロード(クラスの指定)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 以下はtest?serverTimezone=JSTだと Unknown time-zone ID: JSTとエラーが発生する
			String url = "jdbc:mysql://localhost/test?serverTimezone=Asia/Tokyo";
			Connection connection = DriverManager.getConnection(url, "root", "rootpass");
			// ブラウザでなく、コンソール上にDB接続結果を表示
			System.out.println("DBに接続しました");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
