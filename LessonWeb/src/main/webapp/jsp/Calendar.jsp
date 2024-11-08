<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>万年カレンダー</title>
</head>
<body>
	<%
	// Calendar クラスのインスタンスを取得
	Calendar today = Calendar.getInstance();
	// 本日が⻄暦何年かを取得
	int today_y = today.get(Calendar.YEAR);
	// 本日が何月かを取得 (仕様によりCalendar.MONTHは0から始まるため、表示時には+1の調整が必要)
	int today_m = today.get(Calendar.MONTH);
	// 当月１日に日付を設定
	today.set(Calendar.DATE, 1);
	// 当月１日の曜日番号-１の値を代入(当月最初の1日の開始位置を調整)
	int first = today.get(Calendar.DAY_OF_WEEK) - 1;
	// 当月の最終日の日付に設定 (翌月の0日目 = 前月の最終日、という計算)
	today.set(today_y, today_m + 1, 0); //第3引数に0を指定することで前月の最終日を取得
	// 当月最終日の日付を代入
	int last = today.get(Calendar.DATE);
	%>
	<%-- CalendarクラスのMONTHは0スタートでDAY_OF_WEEKは1スタートなどで不統一であり、直感的でない
	そのため、java.time.LocalDate 及び java.time.YearMonth を使った方がわかりやすい --%>
	
	<%-- 下の行はデバッグコード --%>
	first:<%= first%> last:<%= last %>
	
	<%-- 表示上の当月には+1の調整が必要 --%>
	<h1>万年カレンダ(<%=today_y %>年<%=today_m + 1 %>月)</h1>
	<table border="1">
	<tr>
	<th>日</th><th>月</th><th>火</th><th>水</th><th>木</th><th>⾦</th><th>土</th>
	</tr>
	<% // カレンダーの表示
	for (int i = 1; i <= first + last; i++) {
		if (i % 7 == 1) {
		out.print("<tr>");
		}
		if (i > first) {
			out.print("<td>" + (i - first) + "</td>");
		} else {
			// 特殊文字「&nbsp;」は改⾏されない半角スペースのようなもの
			out.print("<td>&nbsp;</td>");
		}
		if (i % 7 == 0) {
		out.println("</tr>");
		}
	}
	%>
	</table>
</body>
</html>