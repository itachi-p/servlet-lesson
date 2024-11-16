import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.HumanBean;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// JSPの画面状態4分岐(0:全件一覧,1:1件選択編集,2更新内容確認,3:更新後確認)をセッションで管理
		HttpSession session = request.getSession(true);
		// 画面切り替えフラグの設定:初期状態(全件検索)時は0
		int sw = 0;
		// JSP側で押されたsubmitボタンの種類(nullの場合はエラー回避の為に空白文字を設定する) ※三項演算子を使用
		String pushedButton = (request.getParameter("submit") != null) ? request.getParameter("submit") : "";
		
		// JSP側でどのボタンが押されたかで処理を分岐
		// 「変更」ボタンが押された場合。「セッションのswの値」も取得しないと、常にここに入ってしまうので注意
		if (request.getParameter("id") != null && (Integer)session.getAttribute("sw") == 0) {
			sw = 1; // 編集画面へ
		} else if (pushedButton.equals("確認")) {
			sw = 2; // 入力された値の確認画面へ
			System.out.println("修正前最終確認画面へ");
		} else if (pushedButton.equals("確定")) {
			sw = 3; // DB更新
			
		} else if (pushedButton.equals("変更トップへ戻る")) {
			sw = 0;
		}
		// JSP側に画面切り替えフラグを渡す
		session.setAttribute("sw", sw);
		
		// DBアクセス用インスタンスの生成
		HumanBean bean = new HumanBean();
		UserDao dao = new UserDao();

		// 上記の切り替えフラグの値によりDBへの処理を分岐
		switch (sw) {
		case 0: // テーブル全件取得
			ArrayList<HumanBean> list = dao.getList();
			request.setAttribute("list", list);
			break;
		case 1: // 1レコード取得
			bean = dao.getRecord(request.getParameter("id"));
			// 3つの画面で同じidの情報1件が必要なため、リクエストではなくセッションに保存
			session.setAttribute("id", bean.getId()); // ID不変なら上記リクエストの値を再利用しても可
			session.setAttribute("name", bean.getName());
			session.setAttribute("age", bean.getAge());
			break;
		case 2: // 1レコードの変更内容をセッションに反映(まだDBには影響しない)
			// IDは編集させない仕様
			String name = "";
			int age = 0;
			if (request.getParameter("name") != null) {
				name = (String)request.getParameter("name");
			}
			if (request.getParameter("age") != null) {
				age = Integer.parseInt(request.getParameter("age"));
			}
			System.out.println(name + age);
			// ここではまだDBには影響せず、セッションのnameとageの値だけを上書き
			session.setAttribute("id", request.getParameter("id")); // 不要か？
			session.setAttribute("name", name);
			session.setAttribute("age", age);
			break;
		case 3: // 入力値でDBのレコードを上書き更新
			dao.update((String)session.getAttribute("id"), (String)session.getAttribute("name"), (int)session.getAttribute("age"));
			// より厳密にするなら、更新後の該当行を1件検索した結果を返す方がよい(この演習ではDB更新完了の確認は省略)
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/update.jsp");
		rd.forward(request, response);
	}
}
