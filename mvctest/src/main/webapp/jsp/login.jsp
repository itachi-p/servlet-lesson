<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv ="Content-Type" content="text/html; charset= UTF-8">
	<title>Login</title>
</head>
<body>
	<strong>ログイン画面</strong>
	<form method="post" action="/mvctest/LoginServletConf">
		<table>
			<tr>
				<td>ID:</td><td><input type="text" size="20" name="id"></td>
			</tr>
			<tr>
				<td>PASS:</td><td><input type="password" size="20" name="pass"></td>
			</tr>
		</table><br />
		<input type="submit" value="ログイン">
		<input type="reset" value="リセット">
	</form>
</body>
</html>