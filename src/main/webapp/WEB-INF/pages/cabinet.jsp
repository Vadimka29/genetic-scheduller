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
                        <button type="button" class="btn btn-success jsAddTask">Додати задачу</button>
                        <button type="button" class="btn btn-primary jsGenerateSchedule">Створити розклад</button>
                        <div class="btn-group" role="group">
                            <label for="algorithm"></label>
                            <select id="algorithm" class="selectpicker">
                                <option>Генетичний алгоритм</option>
                                <option>Алгоритм імітації відпалу</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="cabinet-container__table">
                    <table class="table table-hover jsTable">
                        <thead>
                        <tr>
                            <th>№</th>
                            <th>Задача</th>
                            <th>Пріоритет</th>
                            <th>Час на виконання</th>
                            <th>Крайня дата</th>
                            <th>Дата отримання</th>
                            <th>Годин/День</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="common/footer.jsp"/>
    <div class="cabinet__new-task" style="display: none">
        <form class="new-task jsForm">
            <div class="form-group">
                <label for="taskName">Задача</label>
                <input type="text" name="taskName" class="form-control" id="taskName" placeholder="Physics">
            </div>
            <div class="form-group">
                <label for="deadline">Крайня дата</label>
                <input type="date" name="deadline" class="form-control" id="deadline" placeholder="10/10/2016">
            </div>
            <div class="form-group">
                <label for="duration">Час на виконання</label>
                <input type="text" name="duration" class="form-control" id="duration">
            </div>
            <div class="form-group">
                <label for="hoursPerDay">Годин/День</label>
                <input type="text" name="hoursPerDay" class="form-control" id="hoursPerDay">
            </div>
            <div class="form-group float-fix last-group">
                <div class="left-input">
                    <label for="difficulty">Складність завднаня</label>
                    <select id="difficulty" name="difficulty" class="selectpicker">
                        <option value="1">Легка робота</option>
                        <option value="3">Середня робота</option>
                        <option value="5">Складна робота</option>
                    </select>
                </div>
                <div class="right-input">
                    <label for="priority">Важливість завднаня</label>
                    <select id="priority" name="priority" class="selectpicker">
                        <option value="1">Низька</option>
                        <option value="3">Нормальна</option>
                        <option value="5">Висока</option>
                    </select>
                </div>
            </div>
            <div>
                <input class="btn btn-success" type="submit" value="Додати задачу">
                <input class="btn btn-danger b-close" type="button" value="Відміна">
            </div>
        </form>
    </div>
    <script src="<c:url value="/resources/assets/vendor/jquery-2.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/bootstrap-3.3.6-dist/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/jquery.bpopup.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/bootstrap-select-1.10.0/js/bootstrap-select.js"/>"></script>
    <script src="<c:url value="/resources/assets/vendor/moment.min.js"/>"></script>
    <script src="<c:url value="/resources/assets/project/js/cabinet.js"/>"></script>
</div>
</body>
</html>
