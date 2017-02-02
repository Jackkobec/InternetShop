
<%@ include file="WEB-INF/pages/include.jsp"%>
<html>
<head>
    <style>
        body {
            background: #333; /* Цвет фона */
            color: #fc0; /* Цвет текста */
        }
    </style>
    <title>Main</title>

</head>
<body>

<div class="header">

    <ul>
        <li><a href="Controller?command=register">Register</a></li>
    </ul>

    <c:if test="${!inSystem}">
        <form action="login" method="post">
            <ul>
                <li>Input name:
                    <input name="name" type="text"placeholder="Enter your name"
                           value="<%=(request.getParameter("name")!=null)?request.getParameter("name"):""%>"><%--заполнение формы из request--%>
                </li>
                <li>Input pass:
                    <input name="pass" type="password"placeholder="Enter your password"
                           value="<%=(request.getParameter("pass")!=null)?request.getParameter("pass"):""%>"><%--заполнение формы из request--%>
                </li>
                <li>Submit:
                    <input type="submit">
                </li>
            </ul>
        </form>
    </c:if>
    <c:if test="${inSystem}">
        <ul>
            <li>Hello ${currentUaer}</li>
        </ul>
    </c:if>
    <c:if test="${registred}">
        <ul>
            <h2>${info}</h2>
        </ul>
    </c:if>

</div>



</body>
</html>
