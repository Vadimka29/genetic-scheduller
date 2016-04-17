<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!DOCTYPE html>
<html>
<head>
    <title>Scheduler Service</title>
    <link rel="stylesheet" href="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/assets/project/css/landing-page.css">
    <link rel="stylesheet" href="/resources/assets/vendor/bxslider/jquery.bxslider.css"/>
    <link rel="stylesheet" <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

    <script src="/resources/assets/vendor/jquery-2.2.1.min.js"></script>
    <script src="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
    <script src="/resources/assets/vendor/bxslider/jquery.bxslider.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-default menu">
            <div class="container-fluid">
              <div class="navbar-header">
                  <img id="menu__logo" src="/resources/assets/project/img/logo.png">
              </div>
              <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <%--<button type="button" class="btn btn-default navbar-btn">Sign in</button>--%>
                            <a class="menu__sign-in-link" href="#">Sign in</a>
                    </ul>
              </div>

            </div>
        </nav>
    </div>
    <div class="row" title="Login Dialog" id = "login-dialog">
        <form action="#" class="login-form">
            <fieldset>
                <label for="login-form__email">Email</label>
                <input type="email" id="login-form__email" class="ui-widget-content ui-corner-all">
                <label for="login-form__password">Password</label>
                <input type="password" id="login-form__password" class="ui-widget-content ui-corner-all">
            </fieldset>
        </form>
    </div>
    <div class="row">
        <p id="motivation-text">Довір нам контроль над своїм часом</p>
    </div>
    <div class="navbar-fixed-bottom footer">
        <p>&copy;Vadym Akymov</p>
    </div>
</div>


<script>
(function () {
    var loginFunc = function () {
        a = 5;
    };
    $('#login-dialog').dialog({
        autoOpen: false,
        width: 500,
        height: 250,
        modal: true,
        buttons: {
            "Login": loginFunc,
            Cancel: function() {$(this).dialog("close");},
        },
        'open': function () {
            $('#motivation-text').hide();
            $('.ui-widget-overlay').css('background', '#000000');
        },
        'close': function () {
            $('#motivation-text').show();
        }
    });
    $('.menu__sign-in-link').click(function () {
        $('#login-dialog').dialog('open');
    });
})();
</script>
</body>
</html>
