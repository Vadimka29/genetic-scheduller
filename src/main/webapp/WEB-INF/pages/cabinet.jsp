<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Task Cabinet</title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/css/bootstrap.css"/>"/>
    <link rel="stylesheet"
          href="<c:url value="/resources/assets/vendor/bootstrap-select-1.10.0/css/bootstrap-select.css"/>"/>
    <%--project--%>
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/assets/project/css/cabinet.css"/>"/>
</head>
<body>


<div class="container-fluid">
    <div class="row">
        <jsp:include page="common/header.jsp"/>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="cabinet-container">
                <div class="cabinet-container__controls">
                    <div class="btn-toolbar" role="toolbar">
                        <button type="button" class="btn btn-success">Додати задачу</button>
                        <button type="button" class="btn btn-primary">Створити розклад</button>
                        <div class="btn-group" role="group">
                            <label for="algorithm"></label>
                            <select id="algorithm" class="selectpicker">
                                <option>Генетичний алгоритм</option>
                                <option>Метод гілок та меж</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="cabinet-container__table">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>№</th>
                            <th>Задача</th>
                            <th>Час на виконання</th>
                            <th>Крайня дата</th>
                            <th>Дата отримання</th>
                            <th>Годин/День</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Психологія реферат</td>
                            <td>4</td>
                            <td>12/03/2016</td>
                            <td>01/03/2016</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>ГКС курсова робота</td>
                            <td>48</td>
                            <td>25/05/2016</td>
                            <td>01/01/2016</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Вища математика РГР</td>
                            <td>10</td>
                            <td>08/04/2016</td>
                            <td>10/03/2016</td>
                            <td>2</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="common/footer.jsp" />
    <script src="<c:url value="/resources/assets/vendor/jquery-2.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/jquery.bpopup.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/bootstrap-select-1.10.0/js/bootstrap-select.js"/>"></script>
    <script src="<c:url value="/resources/assets/project/js/cabinet.js"/>"></script>
</div>
</body>
</html>
