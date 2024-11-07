

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BMIAndObesity")
public class BMIAndObesityAssessmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html><head><title>BMI計算結果</title></head><body>");

		// 数字以外が入力された場合のエラーをキャッチする例外処理
		try {
			double height = Double.parseDouble(request.getParameter("height"));
			double weight = Double.parseDouble(request.getParameter("weight"));
			// BMIの計算
			int bmi = calculateBMI(height, weight);
			// 肥満度を判定
			String obesityLevel = assessObesityLevel(bmi);
			
			pw.println("あなたの身長は" + height + "cmです。<br />");
			pw.println("あなたの体重は" + weight + "kgです。<br />");
			pw.println("BMIは" + bmi + " 肥満度は" + obesityLevel + "です。");
		} catch (NumberFormatException e) {
			pw.println(e.getMessage() + "<br>数字を入力してください。");
		}

	} // doPost()
	
	private int calculateBMI(double height, double weight) {
		height /= 100; // 身長をメートル単位に変換
		int bmi = (int)(weight / (height * height));
		return bmi;
	}
	
	private String assessObesityLevel(int bmi) {
		String obesityLevel;
		if (bmi <= 18) {
			obesityLevel = "低体重";
		} else if (bmi < 25) {
			obesityLevel = "標準";
		} else if (bmi < 30) {
			obesityLevel = "肥満度１";
		} else if (bmi < 35) {
			obesityLevel = "肥満度２";
		} else if (bmi < 40) {
			obesityLevel = "肥満度３";
		} else {
			obesityLevel = "肥満度４";
		}
		return obesityLevel;
	}

}
