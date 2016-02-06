<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>

    <jsp:include page="../fragments/header.jsp" />

</head>
<body>

    <div class="container">

        <c:choose>
            <c:when test="${edit}">
                <h1>Update User</h1>
            </c:when>
            <c:otherwise>
                <h1>Add User</h1>
            </c:otherwise>
        </c:choose>
        <br />

        <spring:url value="/users/save" var="userActionUrl">
            <spring:param name="edit" value="${edit}"/>
        </spring:url>

        <form:form class="form-horizontal" method="post" modelAttribute="user" action="${userActionUrl}">

            <form:hidden path="id" />

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" type="text" class="form-control" id="name" placeholder="Name" />
                        <form:errors path="name" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="age">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Age</label>
                    <div class="col-sm-10">
                        <form:input path="age" type="text" class="form-control" id="age" placeholder="Age" />
                        <form:errors path="age" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="admin">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Is Admin</label>
                    <div class="col-sm-10">
                        <label><form:radiobutton path="admin" value="true"/> True </label>
                        <label><form:radiobutton path="admin" value="false"/> False </label>

                        <form:errors path="admin" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="createdDate">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Created Date</label>
                    <div class="col-sm-10">
                        <form:input path="createdDate" type="text" class="form-control" id="createdDate" placeholder="dd/MM/yyyy" />
                        <form:errors path="createdDate" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" class="btn-lg btn-primary pull-right" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="btn-lg btn-primary pull-right" value="Add"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </form:form>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

</body>
</html>
