<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.ArrayList, bean.HumanBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
  <table align="center" border="1">
    <tr>
      <th>ID</th>
      <th>NAME</th>
      <th>AGE</th>
    </tr>
    <%
    // リクエストに保存されたリストを取得
    ArrayList<HumanBean> list = (ArrayList<HumanBean>) request.getAttribute("list");
    for (int i = 0; i < list.size(); i++) {
    	HumanBean bean = list.get(i);
    %>
    <tr>
      <td><%=bean.getId()%></td>
      <td><%=bean.getName()%></td>
      <td><%=bean.getAge()%></td>
    <tr>
      <%
      }
      %>
  </table>
</body>
</html>