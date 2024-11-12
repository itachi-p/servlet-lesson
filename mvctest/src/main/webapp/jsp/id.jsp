<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ID表示</title>
</head>
<body>
	ID=123456の氏名は「 <%= (String)request.getAttribute("name") %>」です。
</body>
</html>