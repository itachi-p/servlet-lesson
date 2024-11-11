<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン結果</title>
</head>
<body>
	<%-- 下記の1行でも結果は同じ。(String)でキャストしなくても文字列が表示される --%>
	<%= request.getAttribute("message") %>
	
	<%-- より丁寧（冗長？）な書き方 --%>
	<% String message = (String)request.getAttribute("message"); %>
	<br />ログイン結果：<%= message %>
</body>
</html>