<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Dispatch</title>
</head>
<body>
	<p>サーブレットからの値の受け渡し</p>
	<%= (String)request.getAttribute("data") %>
</body>
</html>