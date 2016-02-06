<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User List</title>

    <jsp:include page="../fragments/header.jsp" />

    <jsp:useBean id="pagedListHolder" scope="request"
                 type="org.springframework.beans.support.PagedListHolder"/>

    <spring:url value="/users/list" var="pagedLink">
        <spring:param name="page" value="~"/>
        <c:if test="${!nameFilter.isEmpty()}">
            <spring:param name="srch-name" value="${nameFilter}"/>
        </c:if>
    </spring:url>

</head>
<body>

    <div class="container">
        <c:if test="${not empty msg}">
            <div class="alert alert-${css} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <c:if test="${nameFilter.isEmpty()}">
            <h1>All Users:</h1>
        </c:if>
        <c:if test="${!nameFilter.isEmpty()}">
            <h1>Search Result:</h1>
        </c:if>

        <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
        <c:if test="${pagedListHolder.getNrOfElements() > 0}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>#ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Is Admin</th>
                        <th>Created Date</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <c:forEach items="${pagedListHolder.pageList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                        <td>${user.admin}</td>
                        <td>${user.formattedDate}</td>
                        <td>
                            <spring:url value="/users/${user.id}" var="userUrl" />
                            <spring:url value="/users/${user.id}/delete" var="deleteUrl">
                                <c:if test="${!nameFilter.isEmpty()}">
                                    <spring:param name="srch-name" value="${nameFilter}"/>
                                </c:if>
                            </spring:url>
                            <spring:url value="/users/${user.id}/edit" var="editUrl"/>

                            <button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
                            <button class="btn btn-primary" onclick="location.href='${editUrl}'">Edit</button>
                            <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${pagedListHolder.getNrOfElements() == 0}">
            <div class="alert alert-info">
                <strong>No records</strong>
            </div>
        </c:if>
        <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

</body>
</html>