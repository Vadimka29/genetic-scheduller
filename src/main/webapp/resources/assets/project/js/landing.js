(function ($) {
    function loginFunc() {
        $('#login-form').submit();
    }

    $(document).ready(function () {
        //$('#login-dialog').dialog({
        //    autoOpen: false,
        //    width: 500,
        //    height: 250,
        //    modal: true,
        //    buttons: {
        //        Login: loginFunc,
        //        Cancel: function () {
        //            $(this).dialog("close");
        //        }
        //    },
        //    open: function () {
        //        $('#login-form').show();
        //        $('#motivation-text').hide();
        //        $('.ui-widget-overlay').css('background', '#000000');
        //    },
        //    close: function () {
        //        $('#motivation-text').show();
        //    }
        //});

        $('.menu__sign-in-link').click(function () {
            $('#login-form').bPopup({
                //easing: 'easeOutBack',
                speed: 450,
                transition: 'slideDown'
            });
        });

    });

})(jQuery);