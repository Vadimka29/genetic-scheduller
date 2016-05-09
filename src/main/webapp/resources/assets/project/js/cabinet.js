(function ($) {
    $.fn.serializeFormJSON = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    $(document).ready(function () {
        $('.selectpicker').selectpicker();
        var counter = 0;
        var btnAdd = $(".jsAddTask");
        var btnGen = $(".jsGenerateSchedule");
        var table = $(".jsTable");
        var tasks = [];
        var popUp = $('.cabinet__new-task');

        btnAdd.on('click', function () {
            popUp.bPopup({
                //easing: 'easeOutBack',
                speed: 450,
                transition: 'slideDown'
            });

        });

        btnGen.on("click", function () {
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'type': 'POST',
                'url': "/cabinet/generate",
                'data': JSON.stringify(tasks),
                'dataType': 'json',
                'success': function (data) {
                    alert("Success!!");
                }
            });

        });

        $(".jsForm").submit(function (event) {
            event.preventDefault();
            var task = $(this).serializeFormJSON();
            tasks.push(task);
            task.createdDate = moment(new Date()).format("YYYY-MM-DD");
            console.log(task);
            table.find("tbody").append(
                "<tr>" +
                "<td>" + (++counter) + "</td>" +
                "<td>" + task.taskName + "</td>" +
                "<td>" + task.duration + "</td>" +
                "<td>" + moment(new Date()).format("DD/MM/YYYY") + "</td>" +
                "<td>" + moment(new Date(task.deadline)).format("DD/MM/YYYY") + "</td>" +
                "<td>" + task.hoursPerDay + "</td>" +
                "</tr>");

            popUp.close();
        });

        function addNewTask() {

        }

    });
})(jQuery);
