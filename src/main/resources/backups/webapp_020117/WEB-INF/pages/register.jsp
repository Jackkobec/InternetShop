<%@ include file="include.jsp" %>
<html>
<head>
    <title>Register</title>
    <style>
        h2 {
        <%--background: red; /* Цвет фона под заголовком */00--%> color: red; /* Цвет текста */
            padding: 2px; /* Поля вокруг текста */
        }
    </style>
    <style>/*цвет кнопки*/
    .b1 {
        background: navy; /* Синий цвет фона */
        color: white; /* Белые буквы */
        font-size: 9pt; /* Размер шрифта в пунктах */
    }
    </style>
</head>
<body>


<div class="container">
    <h1>Input register info</h1>

    <form method="post" action="Controller?command=userregistration">
        <ul>
            <li>Input name:
                <input name="name" type="text" placeholder="Enter your name"
                       value="<%=(request.getParameter("name")!=null)?request.getParameter("name"):""%>"><%--заполнение формы из request--%>
            </li>
            <li>Input pass:
                <input name="pass" type="password" placeholder="Enter your password"
                       value="<%=(request.getParameter("pass")!=null)?request.getParameter("pass"):""%>"><%--заполнение формы из request--%>
            </li>
            <li>Submit:
                <input class="b1" type="submit">
            </li>
        </ul>
    </form>

    <c:if test="${!validation}">
        <ul>
            <h2>${validationMessage}</h2>
        </ul>
    </c:if>
</div>


</body>
</html>
