import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
@WebServlet("/SessionServlet")
	public class SessionServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	// リクエストから値を取得
	String animal = request.getParameter("animal");
	// セッションの取得
	HttpSession session = request.getSession(true);
	// セッションからオブジェクト取得
	ArrayList<String> list = (ArrayList<String>)session.getAttribute("animallist");
	// 初回時はlistがnullになるため初期化を行う
	if (list == null) {
	list = new ArrayList<String>();
	}
	//リストにリクエストの値を設定
	list.add(animal);
	//セッションにリストを設定
	session.setAttribute("animallist",list);
	PrintWriter pw = response.getWriter();
	pw.println("<html><head><title>SessionSample</title></head>");
	pw.println("<body bgcolor=\"#FFFFFF\" text=\"#000000\">");
	pw.println("<p><font size=\"+1\" color=\"red\">今まで選択した動物</font></p>");
	//リストの値を 1 件ずつ出力
	for (String name : list) {
		pw.println(name + "<br>");
	}
	pw.println("<br><br><a href=\"/SampleWeb/html/animal.html\">戻る</a>");
	pw.println("</body></html>");
	}
}