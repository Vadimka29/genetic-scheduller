<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default menu">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="<c:url value="/"/>">
                <img id="menu__logo" src="<c:url value="/resources/assets/project/img/logo.png"/>">
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="isAnonymous()">
                    <a class="menu__item menu__sign-in-link" href="#">Sign in</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="menu__item" href="<c:url value="/calendar"/>">Calendar plan</a>
                    <a class="menu__item" href="<c:url value="/cabinet"/>">Cabinet</a>
                    <a class="menu__item" href="<c:url value="/logout"/>">Log out</a>
                </sec:authorize>

            </ul>

        </div>

    </div>
</nav>