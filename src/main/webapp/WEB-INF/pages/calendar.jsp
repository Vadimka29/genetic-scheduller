<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User calendar</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>"/>
    <link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.css'/>
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/custom-calendar.css"/>">
    <%--JQuery theme--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/humanity/jquery-ui.css">

    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/header.css"/>">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="common/header.jsp"/>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div id='calendar'></div>
        </div>
    </div>
    <div class="row">
        <jsp:include page="common/footer.jsp"/>
    </div>
</div>

<script src="<c:url value="/resources/assets/vendor/jquery-2.2.1.min.js"/>"></script>
<script src="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"/>"></script>
<script src='http://momentjs.com/downloads/moment.min.js'></script>
<script src='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.js'></script>
<script src="<c:url value="/resources/assets/project/js/user-calendar.js"/>"></script>
</body>
</html>
