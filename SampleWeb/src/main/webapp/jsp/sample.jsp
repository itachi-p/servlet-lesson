<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%-- ヘッダー部でも使用可能 --%>
	<%! String title = "JSP Sample"; // セミコロンがないと構文エラーなので要注意 %>
	<title><%= title %></title>
</head>
<body>
	<h1><%= title %></h1>
	<% for (int i = 0; i < 10; i++) { %>
		JSP count: <%= i %><br />
	<% } %>
</body>
</html>