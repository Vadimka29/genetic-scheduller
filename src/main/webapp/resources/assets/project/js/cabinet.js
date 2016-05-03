(function ($) {
    $(document).ready(function () {
        $('.selectpicker').selectpicker();
        var btnAdd = $(".jsAddTask");
        var btnGen = $(".jsGenerateSchedule");
        var table = $(".jsTable");

        btnAdd.on('click', function() {
            $('.cabinet__new-task').bPopup({
                //easing: 'easeOutBack',
                speed: 450,
                transition: 'slideDown'
            });

        });

        function addNewTask() {

        }

    });
})(jQuery);
