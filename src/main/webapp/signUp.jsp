<%--
  Created by IntelliJ IDEA.
  User: zfq11
  Date: 11/14/2022
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>new registration</title>
</head>
<body>
<h1> Don't have account or account doesn't exits? please signup before logIn </h1>
<form action="AuthControllerServlet" method="POST">
    name :<input type="text" name="Name"/>
    password :<input type="text" name="Password"/>
    role :<input type="text" name="Role"/>
    id :<input type="int" name="ID"/>
    <input type="submit"/>

</form>
</body>
</html>
