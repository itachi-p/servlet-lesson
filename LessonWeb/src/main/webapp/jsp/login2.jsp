<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- ただのHTMLで問題ないが、いちおうJSP --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
	<h1>ログイン</h1>
	<form method="post" action="/LessonWeb/LoginServlet2">
	<table>
		<tr>
			<td>ID：</td>
			<td><input type="text" name="id" size="20"></td>
		</tr>
		<tr>
			<td>PASS：</td>
			<td><input type="password" name="pass" size="20"></td>
		</tr>
	</table><br>
	<input type="submit" value="Login"/>
	<input type="reset" value="Reset"/>
	</form>
</body>
</html>