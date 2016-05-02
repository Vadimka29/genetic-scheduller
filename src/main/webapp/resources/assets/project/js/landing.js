(function ($) {
    function loginFunc() {
        $('#login-form').submit();
    }

    $('#login-dialog').dialog({
        autoOpen: false,
        width: 500,
        height: 250,
        modal: true,
        buttons: {
            Login: loginFunc,
            Cancel: function () {
                $(this).dialog("close");
            }
        },
        open: function () {
            $('#motivation-text').hide();
            $('.ui-widget-overlay').css('background', '#000000');
        },
        close: function () {
            $('#motivation-text').show();
        }
    });

    $('.menu__sign-in-link').click(function () {
        $('#login-dialog').dialog('open');
    });
})(jQuery);