<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
    <title>User CRUD app</title>

    <!-- -->
    <spring:url value="/resources/core/css/bootstrap.min.css"
                var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <!-- -->
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/new" var="urlAddUser" />

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${urlHome}">Home page</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="${urlAddUser}">Add User</a></li>
            </ul>
        </div>

        <div class="col-sm-3 col-md-3 pull-right">
            <form class="navbar-form" role="search" action="${urlHome}">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search by name" name="srch-name" id="srch-name">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>
