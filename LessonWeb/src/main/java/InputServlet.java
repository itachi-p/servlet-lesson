

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// TODO Auto-generated method stub
		// リクエストの文字コードを指定(デフォルト値の"ISO-8859-1（Latin-1）"では日本語が文字化けする)
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// リクエストから値を取得
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String koibito = request.getParameter("koibito");
		String[] hobby;
		// 趣味に1つもチェックが入ってない場合nullになるので、新たに配列を確保する
		if (request.getParameterValues("hobby") != null) {
			// 同じnameで複数の値が存在する場合はgetParameterValues()で配列に格納する
			hobby = request.getParameterValues("hobby");
		} else {
			hobby = new String[1];
			hobby[0] = "無趣味";
		}
		
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>自己紹介ページ表示</title></head>");
		pw.println("<body>");
		pw. println("<p><font size=\"＋1\" color=\"red\">プロフィール</font></p>");
		
		if (name.length() > 0) {
			pw.println("氏名は" + name +"です<br />");
		} else {
			pw.println("氏名は空白です");
		}
		pw.println("年齢は" + age + "歳です<br />");
		
		if (koibito.equals("0")) {
			pw.println("恋人はいません<br />");
		} else {
			pw.println("恋人はいます<br />");
		}
		
		pw.println("趣味は"); // printlnだと後に半角スペースが入る
		int cnt = 0;
		for (int i = 0; i < hobby.length; i++) {
			if(hobby[i] != null) {
				if(cnt >= 1) { // 複数選択されている場合は間に「と」を入れる処理
					pw.print("と"); // 後ろに空白を開けない為にprintlnでなくprintを使う
				}
				pw.print(hobby[i]);
				cnt++;
			}
		}
		pw.println("です<br />");
		pw.print("性格は" + request.getParameter("seikaku") + "です");
		pw.println("<br><br><a href=\"/SampleWeb/html/profile.html\">戻る</a>");
		pw.println("</body></html>");
		pw.close();
	}

}
