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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// JSPの画面状態3分岐(0:全件一覧・1:1件選択削除確認・2:削除後確認)をセッションで管理
		HttpSession session = request.getSession();
		// 画面切り替えフラグの設定:初期状態(全件検索)時は0
		int sw = 0;
		// JSP側で押されたsubmitボタンの種類(nullか「確定」か「一覧へ戻る」)
		String pushedButton = request.getParameter("submit");
		if (pushedButton == null) pushedButton = ""; //条件分岐が(null).equalsでエラー発生を防ぐため
		// JSP側でトップに表示する文言
		String message = null;
		
		// まず画面切り替えフラグをJSP側で押されたボタンによって設定
		if (request.getParameter("id") != null) { // 「削除」ボタンを押された場合
			sw = 1;
		} else if (pushedButton.equals("確定")) {
			sw = 2;
		} else if (pushedButton.equals("一覧へ戻る")) {
			sw = 0;
		}
		// JSP側に画面切り替えフラグを渡す
		session.setAttribute("sw", sw);
		
		// DBのレコード1行分の入れ物用インスタンスを生成
		HumanBean bean = new HumanBean();
		// DAOのインスタンスを生成
		UserDao dao = new UserDao();

		// 上記の切り替えフラグの値によりDBへの処理を分岐
		switch (sw) {
		case 0: // テーブル全件取得
			message = "削除するユーザを選んでください";
			ArrayList<HumanBean> list = dao.getList();
			request.setAttribute("list", list);
			break; // switch-case文ではbreakがないとそのまま次のケースが実行される
		case 1: // 1レコード取得
			message = "以下の内容を削除します。よろしいですか？";
			bean = dao.getRecord(request.getParameter("id"));
			// 次の削除後確認画面でも必要なため、リクエストではなくセッションに保存
			session.setAttribute("id", bean.getId()); // フォームのidの値を使っても可
			session.setAttribute("name", bean.getName());
			session.setAttribute("age", bean.getAge());
			break;
		case 2: // 1レコード削除
			message = "以下の内容を削除しました";
			// DBから1件のレコードを削除し、削除後確認画面へ
			dao.delete((String)session.getAttribute("id"));
			break;
		}
		// 全てのケースで共通の処理
		request.setAttribute("message", message);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/delete.jsp");
		rd.forward(request, response);
	}
}
