<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>九九 1</title>
</head>
<body>
	<% for (int x = 1; x < 10; x++) { // 縦列
		for (int y = 1; y < 10; y++) { // 横列 %>
			<%= x %>*<%= y %>=<%= x * y %>,	
		<% }
		out.println("<br />");
	} %>
</body>
</html>