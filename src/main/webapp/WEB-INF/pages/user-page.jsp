<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>"/>
    <link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.css' />
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/custom-calendar.css"/>">
    <%--JQuery theme--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/humanity/jquery-ui.css">


</head>
<body>
<div class="container-fluid">
    <div id='calendar'></div>
    <jsp:include page="common/footer.jsp" />
</div>

<script src="<c:url value="/resources/assets/vendor/jquery-2.2.1.min.js"/>"></script>
<script src="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"/>"></script>
<script src='http://momentjs.com/downloads/moment.min.js'></script>
<script src='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.js'></script>
<script src="<c:url value="/resources/assets/project/js/user-calendar.js"/>"></script>
</body>
</html>
