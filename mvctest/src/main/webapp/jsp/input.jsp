<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Input</title>
</head>
<body>
  <%
  // 条件分岐1
  if (request.getAttribute("top1") == null && request.getAttribute("top2") == null) {
  %>
  <form method="post" action="/mvctest/DbAccessServlet3">
    情報を追加します 情報を入力してください
    <table>
      <tr>
        <td>ID：</td>
        <td><input type="text" name="id"></td>
      </tr>
      <tr>
        <td>名前：</td>
        <td><input type="text" name="name"></td>
      </tr>
      <tr>
        <td>年齢：</td>
        <td><input type="text" name="age"></td>
      </tr>
    </table>
    <input type="submit" name="submit" value="確認">
  </form>
  <%
  }
  // 条件分岐2
  if (request.getAttribute("top1") != null) {
  %>
  この情報を登録します。よろしいですか？
  <table>
    <tr>
      <td>ID：</td>
      <td><%=session.getAttribute("id")%></td>
    </tr>
    <tr>
      <td>名前：</td>
      <td><%=session.getAttribute("name")%></td>
    </tr>
    <tr>
      <td>年齢：</td>
      <td><%=session.getAttribute("age")%></td>
    </tr>
    <tr>
      <td>
        <form method="post" action="/mvctest/DbAccessServlet3">
          <input type="submit" name="submit" value="登録">
        </form>
      </td>
      <td>
        <form method="post" action="./jsp/input.jsp">
          <input type="submit" name="submit2" value="戻る">
        </form>
      </td>
    </tr>
  </table>
  <%
  }
  // 条件分岐3
  if (request.getAttribute("top2") != null) {
  %>
  情報を登録しました
  <table>
    <tr>
      <td>ID：</td>
      <td><%=session.getAttribute("id")%></td>
    </tr>
    <tr>
      <td>名前：</td>
      <td><%=session.getAttribute("name")%></td>
    </tr>
    <tr>
      <td>年齢：</td>
      <td><%=session.getAttribute("age")%></td>
    </tr>
  </table>
  <form method="post" action="./jsp/input.jsp">
    <input type="submit" name="submit2" value="登録画面へ">
  </form>
  <%
  }
  %>
</body>
</html>