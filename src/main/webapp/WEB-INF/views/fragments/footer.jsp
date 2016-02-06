<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <hr>
    <footer>
        <p>&copy; JavaRushStudent 2016</p>
    </footer>
</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/deletingScript.js" var="deletingJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
            var="bootstrapJs" />

<script src="${deletingJs}"></script>
<script src="${bootstrapJs}"></script>


