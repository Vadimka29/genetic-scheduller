
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Scheduler Service</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/landing-page.css"/>">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="common/header.jsp" />
    </div>
    <div class="row" title="Login Dialog" id="login-dialog">
        <form action="j_spring_security_check" id="login-form" method="POST">
            <fieldset>
                <label for="login-form__email">Email</label>
                <input type="email" id="login-form__email" name="j_username" class="ui-widget-content ui-corner-all">
                <label for="login-form__password">Password</label>
                <input type="password" id="login-form__password" name="j_password"
                       class="ui-widget-content ui-corner-all">
            </fieldset>
        </form>
    </div>
    <div class="row">
        <p id="motivation-text">Довір нам контроль над своїм часом</p>
    </div>
    <jsp:include page="common/footer.jsp" />
</div>

<script src="<c:url value="/resources/assets/vendor/jquery-2.2.1.min.js"/>"></script>
<script src="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="<c:url value="/resources/assets/project/js/landing.js"/>"></script>
</body>
</html>
