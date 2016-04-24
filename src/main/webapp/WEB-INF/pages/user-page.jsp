<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel='stylesheet' href='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.css' />
    <link rel="stylesheet" href="/resources/assets/project/css/custom-calendar.css">
    <%--JQuery theme--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/humanity/jquery-ui.css">

    <script src="/resources/assets/vendor/jquery-2.2.1.min.js"></script>
    <script src="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
    <script src='http://momentjs.com/downloads/moment.min.js'></script>
    <script src='//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.7.0/fullcalendar.min.js'></script>
</head>
<body>
<div class="container-fluid">
    <div id='calendar'></div>
</div>

<script>
    $(document).ready(function () {
        $('#calendar').fullCalendar({
            theme: true,
            weekends: false,
            height: 600,
            header: {
                right: 'month, agendaWeek, agendaDay',
                left: 'prev,next,today',
                center: 'title'
            }
        })
    });
</script>
</body>
</html>
