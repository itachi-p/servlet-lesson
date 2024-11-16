<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.ArrayList, bean.HumanBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete</title>
</head>
<body>
  <h2 align="center"><%= request.getAttribute("message") %></h2><br />
  <table align="center" border="1">
  
  <%-- 3種類の処理画面をフラグによって切り替え --%>
  <%
  switch ((Integer)session.getAttribute("sw")) { // (int)より(Integer)でキャストする方が望ましい
  case 0: // 初期画面(全件検索)
  %>
    <tr>
      <th>ID</th><th>NAME</th><th>AGE</th><td></td>
    </tr>
    <%
    // リクエストに保存されたリストを取得
    ArrayList<HumanBean> list = (ArrayList<HumanBean>)request.getAttribute("list");
    for (int i = 0; i < list.size(); i++) {
    	HumanBean bean = list.get(i);
    %>
    <tr>
      <td><%=bean.getId()%></td>
      <td><%=bean.getName()%></td>
      <td><%=bean.getAge()%></td>
      <%-- 削除ボタンを押された行のIDをvalueではなく、JavaScriptによってURLの一部として渡す --%>
      <td><input type="submit" name="del<%=i%>" value="削除" onclick="location.href='/mvctest/DeleteServlet?id=<%=bean.getId()%>'"></td>
    <tr>
      <%
      }
      %>
  </table>
  <% break;
  case 1: // 削除前確認
  %>
    <tr>
      <th>ID</th><th>NAME</th><th>AGE</th>
    </tr>
    <tr>
      <td><%=session.getAttribute("id") %></td>
      <td><%=session.getAttribute("name") %></td>
      <td><%=session.getAttribute("age") %></td>
    </tr>
  </table><br><br>
  <div align="center">
    <form method="post" action="/mvctest/DeleteServlet">
      <input type="submit" name="submit" value="確定">
    </form>
  </div>
  <% break;
  case 2: // 削除後確認
  %>
    <tr>
      <th>ID</th><th>NAME</th><th>AGE</th>
    </tr>
    <tr>
      <td><%=session.getAttribute("id") %></td>
      <td><%=session.getAttribute("name") %></td>
      <td><%=session.getAttribute("age") %></td>
    </tr>
  </table><br><br>
  <div align="center">
    <form method="post" action="/mvctest/DeleteServlet">
      <input type="submit" name="submit" value="一覧へ戻る">
    </form>
  </div>
  <% break;
  }
  %>
  
</body>
</html>