

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KukuServlet
 */
@WebServlet("/KukuServlet")
public class KukuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		for (int x = 1; x <= 9; x++) {
			for (int y = 1; y <= 9; y++) {
				pw.println(x + "*" + y + "=" + (x * y) + "<br />");
			}
		}
		
		pw.println("</body></html>");
		pw.close();
	}

}
