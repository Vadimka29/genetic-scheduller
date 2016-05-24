<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <a class="menu__item menu__sign-in-link" href="#">Увійти</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="menu__item" href="<c:url value="/calendar"/>">Календарний план</a>
                    <a class="menu__item" href="<c:url value="/cabinet"/>">Кабінет</a>
                    <a class="menu__item" href="<c:url value="/logout"/>">Вийти</a>
                </sec:authorize>

            </ul>

        </div>

    </div>
</nav>