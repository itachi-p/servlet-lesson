package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CounterServlet")
public class CounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションの取得
		HttpSession session = request.getSession(true);
		// セッションからオブジェクト取得 (初回はcountオブジェクト未登録のため、必ずnullになる)
		Integer count = (Integer)session.getAttribute("count");
		// カウンタを+1 (初回はnull値を0に変換)
		if (count == null) {
			count = 0;
		}
		count++;
		// セッションにオブジェクトを格納
		session.setAttribute("count", count);
		
		response.setContentType("text/html");
		
		// 最後の行でPrintWriterを明示的にclose()するより更に安全な方法 try-with-resources文
		try (PrintWriter pw = response.getWriter()) {
//		PrintWriter pw = response.getWriter();
			pw.println("<html><head><title>Session Check</title></head>");
			pw.println("<body>");
			// セッションIDの表示
			pw.println("<p>sessionID=" + session.getId() + "</p>");
			// カウンタの表示
			pw.println("<p>count=" + count + "</p>");
			// このサーブレット自身へのリンク表示
			pw.println("<p><a href=\"" + request.getRequestURI() + "\">RELOAD</a></p>");
			pw.println("</body></html>");
//		pw.close(); // 必須ではないが、閉じておいた方が望ましい
		} // PrintWriterはtry-with-resources文により、自動的に閉じられる。
	}
}
