<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv ="Content-Type" content="text/html; charset= UTF-8">
	<title>Result</title>
</head>
<body>
	<%
		String req_id = (String)request.getAttribute("login_name");
		// DB検索結果(getName())がnullでなければログイン成功
		if (req_id != null) {
	%>
		ようこそ!<%= req_id %>さん!<br />
	<%
		} else {
	%>
		ユーザまたはパスワードが違います<br />
		<a href="/mvctest/LoginServlet">ログイン画面へ</a>
	<%
		}
	%>
</body>
</html>