<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.ArrayList, bean.HumanBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body> 
  <%-- 4種類の処理画面をフラグによって切り替え --%>
  <%
  // もしnullであった場合、(int)でのキャストには例外(ぬるぽ)が投げられるが、(Integer)なら単にnullが返る
  switch ((Integer)session.getAttribute("sw")) {
  case 0: // 初期画面(全件検索)
  %>
  <p align="center">変更するユーザ情報を選んでください</p><br>
  <table align="center" border="1">
    <tr>
      <th>ID</th><th>NAME</th><th>AGE</th><td></td>
    </tr>
    <%
    ArrayList<HumanBean> list = (ArrayList<HumanBean>)request.getAttribute("list");
    for (int i = 0; i < list.size(); i++) {
    	HumanBean bean = list.get(i);
    %>
    <tr>
      <td><%=bean.getId()%></td>
      <td><%=bean.getName()%></td>
      <td><%=bean.getAge()%></td>
      <%-- 削除ボタンを押された行のIDをvalueではなく、JavaScriptによってURLの一部として渡す(nameは不使用) --%>
      <td><input type="submit" name="upd<%=i%>" value="変更" onclick="location.href='/mvctest/UpdateServlet?id=<%=bean.getId()%>'"></td>
    <tr>
      <%
      }
      %>
  </table>
  <% break;
  case 1: // 編集画面
  %>
  <p align="center">項目を変更してください</p><br>
  <form method="post" action="/mvctest/UpdateServlet">
    <table align="center" border="1">
      <tr>
        <th>ID</th><th>NAME</th><th>AGE</th>
      </tr>
      <tr>
        <%-- IDの値は変更させない仕様 --%>
        <td><input type="text" name="id" value="<%=session.getAttribute("id")%>" readonly></td>
        <td><input type="text" name="name" value="<%=session.getAttribute("name")%>"></td>
        <td><input type="text" name="age" value="<%=session.getAttribute("age")%>"></td>       
      </tr>
    </table><br><br>
    <div align="center">
      <input type="submit" name="submit" value="確認">
    </div>
  </form>
  <% break;
  case 2: // 修正内容の事前確認
  %>
  <p align="center">以下の内容でよろしいですか？</p><br>
  <table align="center" border="1">
    <tr>
      <th>ID</th><th>NAME</th><th>AGE</th>
    </tr>
    <tr>
      <td><%=session.getAttribute("id") %></td>
      <td><%=session.getAttribute("name") %></td>
      <td><%=session.getAttribute("age") %></td>
    </tr>
  </table><br><br>
  <form method="post" action="/mvctest/UpdateServlet">
    <div align="center">
      <input type="submit" name="submit" value="確定">
    </div>
  </form>
  <% break;
  case 3: // 更新後確認(対象レコード変更後のDB1件検索は行っていない)
  %>
  <p align="center">以下の情報へ変更しました</p><br>
  <table align="center" border="1">
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
    <form method="post" action="/mvctest/UpdateServlet">
      <div align="center">
        <input type="submit" name="submit" value="変更トップへ戻る">
      </div>
    </form>
  </div>
  <% break;
  }
  %>
  
</body>
</html>