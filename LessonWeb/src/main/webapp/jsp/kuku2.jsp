<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>九九 (表)</title>
</head>
<body>
	<h1>九九表</h1>
	<table border="1">
		<tr>
			<th></th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th><th>9</th>
		</tr>
		<% for (int x = 1; x < 10; x++) { // 縦列 %>
			<tr><th><%= x %></th> <%-- 全体をout.printlnでもよい --%>
			<%-- HTML,スクリプトレット,式をあまり混在させ過ぎると逆に可読性が悪くなる場合がある --%>
			<% for (int y = 1; y < 10; y++) { // 横列
			out.println("<td width='20' align='right'>" + (x * y) + "</td>");
			}
			out.println("</tr>");
		} %>
	</table>
</body>
</html>