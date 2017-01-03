<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 03.01.2017
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrap-meta.jsp"/>
    <title>Error Page</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="../../view.components/css/styles.css" rel="stylesheet">
</head>
<body>

<div class="error-404">
    <div class="error-code m-b-10 m-t-20">404 <i class="fa fa-warning"></i></div>
    <h2 class="font-bold">Oops 404! That page not found.</h2>

    <div class="error-desc">
        <%--<%=(request.getAttribute("errorInfo").toString()!=null)?request.getAttribute("errorInfo").toString():""%>--%>
        <h2>${errorInfo}</h2>
        <div><br/>
            <!-- <a class=" login-detail-panel-button btn" href="http://vultus.de/"> -->
            <a href="/" class="btn btn-primary"><span class="glyphicon glyphicon-home"></span> Go back to Homepage</a>
        </div>
    </div>
</div>

</body>
</html>
