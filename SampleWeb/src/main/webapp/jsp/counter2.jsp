<html>
<head>
	<meta http-equiv ="Content-Type" content="text/html; charset= UTF-8">
	<title>Counter2</title>
</head>
<body>
	<%
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		for (int i = 1; i <= cnt; i++) { %>
			Hello JSP count=<%= i %><br>
		<% } %>
</body>
</html>