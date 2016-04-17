<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="login-form" action="j_spring_security_check" method="POST">
    <input type="text" id="login" name="j_username" placeholder="login">
    <input type="password" id="password" name="j_password" placeholder="password">
    <button type="submit">Submit</button>
</form>
</body>
</html>