<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hello JSP!</title>
</head>
<body>
	ようこそ︕ JSP＆サーブレットの世界へ︕<br>
	現在の時刻は
	<%
		Calendar calendar = new GregorianCalendar();
		out.println(calendar.get(GregorianCalendar.HOUR_OF_DAY ));
		out.println(":");
		out.println(calendar.get(GregorianCalendar.MINUTE ));
	%>
	です。
</body>
</html>