<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ParameterReceive</title>
	<%!
	// 変数の準備
	String para1 = null;
	String para2 = null;
	int num1 = 0;
	int num2 = 0;
	// メソッドの宣言
	public boolean inputCheck(String para1, String para2) {
		try {
			num1 = Integer.parseInt(para1);
			num2 = Integer.parseInt(para2);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	%>
</head>
<body>
	<%
	para1 = request.getParameter("firstnum");
	para2 = request.getParameter("secondnum");
	if (inputCheck(para1, para2)) {
	%>
	<h3>計算結果</h3>
	1つ目の数は「<%=num1 %>」です。<br>
	2つ目の数は「<%=num2 %>」です。<br>
	計算結果は「<font color="red"><%=num1 * num2 %></font>」です。<br><br>
	<%
	} else {
	%>
	<h3><font color="red">入⼒エラー</font></h3>
	<p>半角数字を入⼒してください。<br><br>
	<%
	}
	%>
	<button onClick="history.back()">戻る</button><%-- history.back()はJavaScriptの機能です --%>
</body>
</html>