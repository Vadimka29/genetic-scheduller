$(document).ready(function () {
    showLoadingInsteadOfCalendar();

    getOptimizedScheduleFromServer(initCalendar);

    function initCalendar(calendarEvents) {
        hideLoading();

        $('#calendar').fullCalendar({
            lang: 'uk',
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
        $('#calendar').fullCalendar('gotoDate', calendarEvents[0].start);
    }

    function getOptimizedScheduleFromServer(callback) {
        $.get("/api/test-schedule", function (data, status) {
            var events = [];
            for (var i = 0; i < data.length; i++) {
                var eventName = data[i].parentName + " [duration: " + data[i].duration + "h]";
                var eventObject = {id: i, title: eventName, start: new Date(data[i].executionDate)};
                events.push(eventObject);
            }
            var map = new Map();
            for (var i = 0; i < data.length; i++) {
                var value;
                if (map.get((data[i].executionDate)) == undefined) {
                    value = 0;
                    map.set(data[i].executionDate, value);
                } else {
                    value = map.get((data[i].executionDate));
                    value = value + data[i].duration;
                    map.set(data[i].executionDate, value);
                }
            }
            callback(events);
        });
    }

    function showLoadingInsteadOfCalendar() {
        $("#calendar").fadeOut();
        $(".sk-circle").css('display', 'block');
    }

    function hideLoading() {
        $("#calendar").fadeIn();
        $(".sk-circle").css('display', 'none');
    }
});