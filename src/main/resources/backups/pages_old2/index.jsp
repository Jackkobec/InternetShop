<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 02.01.2017
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="WEB-INF/pages/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${selectedLocale}" scope="session"/>
<fmt:setBundle basename="language" var="rb"/>
<%-- or
"ru_RU" means: language_ru_RU.properties
"en_EN" means: language_en_EN.properties
In the Java: request.setAttribute("selectedLocale", "en_EN");
--%>
<!DOCTYPE html>
<head>
    <jsp:include page="WEB-INF/pages/bootstrap-meta.jsp"/>
    <title>Login Form</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="view.components/css/styles.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form role="form" action="Controller?command=userlogin" method="post">
                <fieldset>
                    <h2><fmt:message key="login.form.sign_in.message" bundle="${rb}"/></h2>
                    <hr class="colorgraph">
                    <div class="form-group has-feedback ${emailValidationFeedback}">
                        <!-- default <div class="form-group"> -->
                        <input type="email" name="email" id="email" class="form-control input-lg"
                               placeholder="<fmt:message key="login.form.email.placeholder" bundle="${rb}"/>"
                               value="<%=(request.getParameter("email")!=null)?request.getParameter("email"):""%>"><%--заполнение формы из request--%>
                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span><!-- test icon  -->--%>
                        <span class="glyphicon ${emailGlyphiconType} form-control-feedback"></span><!-- test icon  -->
                    </div>
                    <div class="form-group has-feedback ${passwordValidationFeedback}">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                               value="<%=(request.getParameter("password")!=null)?request.getParameter("password"):""%>"><%--заполнение формы из request--%>
                        <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                        <!-- test icon  -->
                    </div>

                    <div class="form-group"> <!-- тест выпадающего списка -->
                        <label for="filter"><fmt:message key="login.form.language_selector" bundle="${rb}"/></label>
                        <select class="form-control" id="filter" name="language_selection">
                            <option value="0" selected><fmt:message key="login.form.language.english"
                                                                    bundle="${rb}"/></option>
                            <option value="1"><fmt:message key="login.form.language.russian" bundle="${rb}"/></option>
                            <%--<option value="2">Most popular</option>--%>
                            <%--<option value="3">Top rated</option>--%>
                            <%--<option value="4">Most commented</option>--%>
                        </select>
                    </div> <!-- /тест выпадающего списка -->


                    <span class="button-checkbox">
					<button type="button" class="btn" data-color="info"><fmt:message key="login.form.remember_me"
                                                                                     bundle="${rb}"/></button>
                    <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<a href="" class="btn btn-link pull-right"><fmt:message key="login.form.forgot_password"
                                                                            bundle="${rb}"/></a>
				</span>
                    <hr class="colorgraph">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <%--<input type="submit" class="btn btn-lg btn-success btn-block" value="Sign In"><!-- default  -->--%>
                            <input type="submit" class="btn btn-lg btn-success btn-block ${notification}"
                                   value="<fmt:message key="login.form.sign_in.button" bundle="${rb}"/>">
                        </div>

                        <%--<div class="notification notification-error logged-out"><!-- notification test-->--%>
                        <div class="notification notification-error notification-from-command"><!-- notification test-->
                            <fmt:message key="${notificationMessage}" bundle="${rb}"/>
                        </div>
                        <div class="notification notification-error logged-oute">
                            You logged out successfully!
                        </div><!-- /notification test-->


                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <a href="Controller?command=register" class="btn btn-lg btn-primary btn-block">
                                <fmt:message key="login.form.register.button" bundle="${rb}"/></a>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="view.components/js/bootstrap.min.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/forlogin.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/fornotifications.js"></script>
</body>
</html>
