$(document).ready(function () {
    $('#calendar').fullCalendar({
        theme: true,
        weekends: false,
        editable: true,
        height: window.innerHeight - 150,
        header: {
            right: 'month, agendaWeek, agendaDay',
            left: 'prev,next,today',
            center: 'title'
        },
        events: [
            {id: '1', start:'2016-04-25', end: '2016-04-25', title:'Зафигачить диплом'}
        ]
    })
});