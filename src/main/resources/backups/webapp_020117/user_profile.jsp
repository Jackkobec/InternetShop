<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 09.01.2017
  Time: 3:30
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
<%--nav tabs--%>
<ul class="nav nav-pills nav-justified">
    <li class="active"><a data-toggle="pill" href="#home">Home</a></li>
    <li><a data-toggle="pill" href="#menu1">Menu 1</a></li>
    <li><a data-toggle="pill" href="#menu2">Menu 2</a></li>
</ul>

<div class="tab-content">
    <div id="home" class="tab-pane fade in active">
        <h3>HOME</h3>

        <%----%>
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
                                <span class="glyphicon ${emailGlyphiconType} form-control-feedback"></span>
                                <!-- test icon  -->
                            </div>
                            <div class="form-group has-feedback ${passwordValidationFeedback}">
                                <input type="password" name="password" id="password" class="form-control input-lg"
                                       placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                                       value="<%=(request.getParameter("password")!=null)?request.getParameter("password"):""%>"><%--заполнение формы из request--%>
                                <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                                <!-- test icon  -->
                            </div>

                            <div class="form-group"> <!-- тест выпадающего списка -->
                                <label for="filter"><fmt:message key="login.form.language_selector"
                                                                 bundle="${rb}"/></label>
                                <select class="form-control" id="filter" name="language_selection">
                                    <option value="0" selected><fmt:message key="login.form.language.english"
                                                                            bundle="${rb}"/></option>
                                    <option value="1"><fmt:message key="login.form.language.russian"
                                                                   bundle="${rb}"/></option>
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
                                <div class="notification notification-error notification-from-command">
                                    <!-- notification test-->
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
        <%----%>

    </div>
    <div id="menu1" class="tab-pane fade">
        <h3>Menu 1</h3>

        <%----%>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

        <div class="container">
            <div class="row">


                <div class="col-md-12">
                    <h4>Bootstrap Snipp for Datatable</h4>
                    <div class="table-responsive">


                        <table id="mytable" class="table table-bordred table-striped">

                            <thead>

                            <th><input type="checkbox" id="checkall"/></th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Contact</th>
                            <th>Edit</th>

                            <th>Delete</th>
                            </thead>
                            <tbody>

                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>Mohsin</td>
                                <td>Irshad</td>
                                <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                <td>isometric.mohsin@gmail.com</td>
                                <td>+923335586757</td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Edit">
                                        <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal"
                                                data-target="#edit"><span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                    </p>
                                </td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal"
                                                data-target="#delete"><span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </td>
                            </tr>

                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>Mohsin</td>
                                <td>Irshad</td>
                                <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                <td>isometric.mohsin@gmail.com</td>
                                <td>+923335586757</td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Edit">
                                        <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal"
                                                data-target="#edit"><span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                    </p>
                                </td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal"
                                                data-target="#delete"><span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </td>
                            </tr>


                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>Mohsin</td>
                                <td>Irshad</td>
                                <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                <td>isometric.mohsin@gmail.com</td>
                                <td>+923335586757</td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Edit">
                                        <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal"
                                                data-target="#edit"><span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                    </p>
                                </td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal"
                                                data-target="#delete"><span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </td>
                            </tr>


                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>Mohsin</td>
                                <td>Irshad</td>
                                <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                <td>isometric.mohsin@gmail.com</td>
                                <td>+923335586757</td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Edit">
                                        <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal"
                                                data-target="#edit"><span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                    </p>
                                </td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal"
                                                data-target="#delete"><span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </td>
                            </tr>


                            <tr>
                                <td><input type="checkbox" class="checkthis"/></td>
                                <td>Mohsin</td>
                                <td>Irshad</td>
                                <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                <td>isometric.mohsin@gmail.com</td>
                                <td>+923335586757</td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Edit">
                                        <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal"
                                                data-target="#edit"><span class="glyphicon glyphicon-pencil"></span>
                                        </button>
                                    </p>
                                </td>
                                <td>
                                    <p data-placement="top" data-toggle="tooltip" title="Delete">
                                        <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal"
                                                data-target="#delete"><span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </p>
                                </td>
                            </tr>


                            </tbody>

                        </table>

                        <div class="clearfix"></div>
                        <ul class="pagination pull-right">
                            <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                        </ul>

                    </div>

                </div>
            </div>
        </div>


        <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                                class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input class="form-control " type="text" placeholder="Mohsin">
                        </div>
                        <div class="form-group">

                            <input class="form-control " type="text" placeholder="Irshad">
                        </div>
                        <div class="form-group">
                            <textarea rows="2" class="form-control"
                                      placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span
                                class="glyphicon glyphicon-ok-sign"></span> Update
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>


        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
                                class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
                    </div>
                    <div class="modal-body">

                        <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you
                            sure you want to delete this Record?
                        </div>

                    </div>
                    <form role="form" action="Controller?command=userlogout" method="post">
                        <div class="modal-footer ">
                            <button type="submit" class="btn btn-success"><span
                                    class="glyphicon glyphicon-ok-sign"></span> Yes
                            </button>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><span
                                    class="glyphicon glyphicon-remove"></span> No
                            </button>
                        </div>
                    </form>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <%----%>

    </div>
    <div id="menu2" class="tab-pane fade">
        <h3>Menu 2</h3>
        <p>Some content in menu 2.</p>
    </div>
</div>
<%--/nav tabs--%>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="view.components/js/bootstrap.min.js"></script>
</body>
</html>
