<%@ include file="include.jsp"%>
<html>
<head>
  <title>User Info</title>
</head>
<body>

<%--<% Student transfered = (Student) request.getAttribute("student");%>--%>
<c:set var="transfered" value="${user}"/><%-- это берется из реквеста, мы в реквест положили атрибут user:   request.setAttribute("user" , finded); --%>


<div class="container">
  <ul>
    <li>
      <div class="column">
        id : ${transfered.id}<%-- должно совпадать с полем класса и должен быть геттер и сеттер--%>
      </div>
    </li>

    <li>
      <div class="column">
        name : ${transfered.name} <%-- должно совпадать с полем класса и должен быть геттер и сеттер--%>
      </div>
    </li>

    <li>
      <div class="column">
        pass : ${transfered.pass} <%-- должно совпадать с полем класса и должен быть геттер и сеттер--%>
      </div>
    </li>
  </ul>
</div>
</body>
</html>
