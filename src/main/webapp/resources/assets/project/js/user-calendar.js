$(document).ready(function () {

    getOptimizedScheduleFromServer(initCalendar);

    function initCalendar(calendarEvents) {
        $('#calendar').fullCalendar({
            theme: true,
            weekends: true,
            editable: true,
            height: window.innerHeight - 150,
            header: {
                right: 'month, agendaWeek, agendaDay',
                left: 'prev,next,today',
                center: 'title'
            },
            events: calendarEvents
        });
    }

    function getOptimizedScheduleFromServer(callback) {
        $.get("/api/test-schedule", function (data, status) {
            var events = new Array();
            for(var i = 0; i < data.length; i ++){
                var eventName = data[i].parentName + "[" + data[i].id + "]";
                var eventObject = {title: eventName, start: new Date(data[i].executionDate)};
                events.push(eventObject);
                console.log(eventObject);
            }
            callback(events);
        });
    }
});