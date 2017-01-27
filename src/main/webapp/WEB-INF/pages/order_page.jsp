<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 15.01.2017
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include.jsp" %>
<%--
<%@ include file="include.jsp" %>
Директива включения используется в том случае, когда файл редко изменяется.
Это самый оперативный механизм. Если ваш контейнер не умеет автоматически воспроизводить изменения,
вы можете заставить его это делать, удалив файл класса главной страницы.--%>
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
    <jsp:include page="bootstrap-meta.jsp"/>
    <%--
<jsp:include page="bootstrap-meta.jsp"/>
Действие включения используется только в случаях очень частого обновления содержания и тогда,
когда определить страницу для включения можно не раньше, чем выполнится запрос к главной странице.--%>
    <title>Item Page</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="view.components/css/styles.css" rel="stylesheet">
    <!-- Добавляем свой стиль -->
    <%--Для корзины тоже нужен--%>
    <link type="text/css" href="view.components/css/item_page.css" rel="stylesheet">
    <!-- Добавляем свой стиль user profile -->
    <link type="text/css" href="view.components/css/user_profile.css" rel="stylesheet">
    <style>
        html { height: 100%; }
        .my-div {
            margin: 0; /* Убираем отступы */
            /*height: 43%;*/
            height: 57%;
            width: 100%; /* Высота страницы */
            background: url(http://www.sunhome.ru/i/wallpapers/67/terminator-2-oboi.1920x1080.jpg); /* Параметры фона */
            background-size: auto; /* Фон занимает всю доступную площадь */
        }
    </style>
</head>

<body>
<%--Image--%>
<div class="jumbotron">
    <div class="container text-left my-div">
        <a href="Controller?command=gotomainpage">
            <h1><fmt:message key="head.big_text" bundle="${rb}"/></h1>
        </a>
        <h2><fmt:message key="head.small_text" bundle="${rb}"/></h2>
    </div>
</div>
<%--/Image--%>

<%--Fixed header elements--%>
<div class="container">
    <div class="row">
        <div class="header">
            <ul>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-off mycolorspan-red"></div>
                    <div class="row"><a href="Controller?command=userlogout"></span>LogOut</a></div>
                </li>
                <li2>
                    <div class="row"><span class="glyphicon glyphicon-user mycolorspan"></div>
                    <div class="row"><a data-toggle="modal" href="#myModalUserProfile"></span>Hello!<br>${sessionScope.currentUserInSystem.email}</a></div>
                </li2>

                <c:choose>
                    <c:when test="${currentUserCart.size() == 0}">
                        <c:set var="spanClass" value="mycolorspan"/>
                        <c:set var="cartText" value="Cart"/>
                    </c:when>
                    <c:when test="${currentUserCart.size() > 0}">
                        <c:set var="spanClass" value="mycolorspan-gold"/>
                        <c:set var="cartText" value="Cart(${currentUserCart.size()})"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="spanClass" value="mycolorspan"/>
                        <c:set var="cartText" value="Cart"/>
                    </c:otherwise>
                </c:choose>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-shopping-cart ${spanClass}"></div>
                    <div class="row"><a data-toggle="modal" href="#myModal"></span>${cartText}</a></div>
                </li>
            </ul>
        </div>
    </div>
</div>
<%--/Fixed header elements--%>

<%--test model--%>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Cart</h4>
            </div>
            <div class="modal-body"><%--body--%>
                <%--test shoping cart--%>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:if test="${currentUserCart.size() == 0}">
                                <p><h4>Cart is empty</h4><p>
                                    </c:if>
                                    <c:forEach var="item" items="${currentUserCart}">
                                    <tr>
                                        <td class="col-sm-8 col-md-6">
                                            <div class="media">
                                                <a class="thumbnail pull-left" href="Controller?command=showitem&item_id=${item.id}"> <img class="media-object" src="${item.itemSmallPicturePath350x260}" style="width: 72px; height: 72px;"> </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading"><a href="Controller?command=showitem&item_id=${item.id}">${item.itemName}</a></h4>
                                                    <h5 class="media-heading">Category: <a href="#"><br>${item.itemCategory.categoryName}</a></h5>
                                                    <span>Status: </span><span class="text-success"><strong>${item.itemStatus}</strong></span>
                                                </div>
                                            </div></td>

                                        <td class="col-sm-1 col-md-1" style="text-align: center">
                                            <input class="form-control" id="exampleInputEmail12" value="1" type="email">
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>

                                        <form action="Controller?command=removeitemfromcart" method="POST">
                                            <input type="hidden" name="item_id" value="${item.id}"> </input>
                                            <input type="hidden" name="from_page" value="ORDER_PAGE"> </input>
                                            <td class="col-sm-1 col-md-1">
                                                <button type="submit" class="btn btn-danger">
                                                    <span class="glyphicon glyphicon-remove"></span> Remove
                                                </button></td>
                                        </form>
                                    </tr>
                                    </c:forEach>

                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h5>Subtotal</h5></td>
                                        <td class="text-right"><h5><strong>$${summaryCartPrice}</strong></h5></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h5>Discount</h5></td>
                                        <td class="text-right"><h5><strong>$0.00</strong></h5></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h3>Total</h3></td>
                                        <td class="text-right"><h3><strong>$${summaryCartPrice}</strong></h3></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                            </button></td>
                                        <c:choose>
                                            <c:when test="${currentUserCart.size() == 0}">
                                                <c:set var="isDisable" value="disabled"/>
                                            </c:when>
                                            <c:when test="${currentUserCart.size() > 0}">
                                                <c:set var="isDisable" value=""/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="isDisable" value=""/>
                                            </c:otherwise>
                                        </c:choose>
                                        <form action="Controller?command=makeorder" method="POST">
                                        <td>
                                            <button type="submit" class="btn btn-success ${isDisable}">
                                                Make Order <span class="glyphicon glyphicon-ok"></span>
                                            </button></td>
                                            </form>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%--/test shoping cart--%>

            </div><%--/body--%>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--/test modal--%>

<%--TEST MODAL USER PROFILE--%>
<!-- Modal -->
<div class="modal fade" id="myModalUserProfile" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel2">User Profile</h4>
            </div>
            <div class="modal-body"><%--body--%>

                <%--TEST USER PROFILE CONTENT--%>
                <div class="col-lg-12 col-sm-6">
                    <div class="card hovercard">
                        <div class="card-background">
                            <img class="card-bkimg" alt="" src="view.components/images/Author.png">
                            <!-- http://lorempixel.com/850/280/people/9/ -->
                        </div>
                        <div class="useravatar">
                            <img alt="" src="view.components/images/Author.png">
                        </div>
                        <div class="card-info"> <span class="card-title">${sessionScope.currentUserInSystem.email}</span>

                        </div>
                    </div>
                    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                                <div class="hidden-xs">My Orders</div>
                            </button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                <div class="hidden-xs">User Info</div>
                            </button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
                                <div class="hidden-xs">Change Password</div>
                            </button>
                        </div>
                    </div>

                    <div class="well">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="tab1">
                                <h2>Order list</h2>
                                <%--TEST ORDER LIST--%>
                                <%--<c:forEach var="order" items="${currentUserOrders}">--%>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12">

                                            <h4>Category items.</h4>
                                            <div class="table-responsive">
                                                <table id="mytable" class="table table-bordred table-striped">

                                                    <thead>

                                                    <th><input type="checkbox" id="checkall" /></th>
                                                    <th>Order Id</th>
                                                    <th>Order Date and Time</th>
                                                    <th>Items count</th>
                                                    <th>Summary price</th>
                                                    <th>Order status</th>

                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                    </thead>
                                                    <tbody>

                                                    <c:forEach var="order" items="${currentUserOrders}">
                                                        <tr>
                                                            <td><input type="checkbox" class="checkthis" /></td>
                                                            <td>${order.id}</td>
                                                            <td>${order.orderDateAndTime}</td>
                                                            <td>n/a</td>
                                                            <td>${order.summaryPrice}</td>
                                                            <td>${order.orderStatus.getOrderStatusId}</td>

                                                                <%--data-content="${item.id}  - в data-conten сохраняем id текущего item чтобы через java script передать его в hiden parameter в форму
                                                                                        к примеру:
                                                                                        <form action="Controller?command=edititem" method="POST">
                                                                                                                <input type="hidden" name="item_id" id="itemForUpdate" value="">
                                                                                         Чтобы просетить value в input с помоззью java script извлечем значение  data-content="${item.id}" и пропишем его по id элемента страницы:

                                                                                             <script>
                                                                                        // при открытии модального окна
                                                         <script>
                                                            // при открытии модального окна
                                                            $('#edit').on('show.bs.modal', function (event) {
                                                                // получить кнопку, которая его открыло
                                                                var button = $(event.relatedTarget)
                                                                // извлечь информацию из атрибута data-content
                                                                var content = button.data('content')
                                                                // вытягившаем из этой страницы элементы с id: itemForUpdate и itemForCloneId
                                                                var itemForUpdate = document.getElementById('itemForUpdate');
                                                                var itemForCloneId = document.getElementById('itemForCloneId');
                                                                // записываем в их value данные из переменной content
                                                                itemForUpdate.value = content;
                                                                itemForCloneId.value = content;
                                                                // вывести эту информацию в элемент, имеющий id="content"
                                                                $(this).find('#content').text(content);
                                                            })
                                                        </script>

                                                            Это делается дя передачи правильного item id в форму.--%>
                                                            <td><p data-placement="top" data-toggle="tooltip" title="Edit">
                                                                <button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-content="${item.id}">
                                                                    <span class="glyphicon glyphicon-pencil"></span></button></p></td>

                                                                <%--data-content="${item.id}  - в data-conten сохраняем id текущего item чтобы через java script передать его в hiden parameter в форму
                                                                к примеру:
                                                                <form action="Controller?command=edititem" method="POST">
                                                                                        <input type="hidden" name="item_id" id="itemForUpdate" value="">
                                                                 Чтобы просетить value в input с помоззью java script извлечем значение  data-content="${item.id}" и пропишем его по id элемента страницы:

                                                                     <script>
                                                                // при открытии модального окна
                                                                <script>
                                                                // при открытии модального окна
                                                                $('#delete').on('show.bs.modal', function (event) {
                                                                    // получить кнопку, которая его открыло
                                                                    var button = $(event.relatedTarget)
                                                                    // извлечь информацию из атрибута data-content
                                                                    var content = button.data('content')
                                                                    // вытягившаем из этой страницы элементы с id: itemForUpdate и itemForCloneId
                                                                    var itemForDeleteId = document.getElementById('itemForDeleteId');
                                                                    // записываем в их value данные из переменной content
                                                                    itemForDeleteId.value = content;
                                                                })
                                                            </script>

                                                               Это делается дя передачи правильного item id в форму.--%>
                                                            <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                                <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" data-content="${item.id}">
                                                                    <span class="glyphicon glyphicon-trash"></span></button></p></td>
                                                        </tr>


                                                        <%--test edit model--%>
                                                        <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                                                        <h4 class="modal-title custom_align" id="Heading">Select Edit Model</h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                            <%----%>
                                                                        <div class="row">
                                                                            <div class="col-xs-4 col-sm-3 col-md-4">
                                                                                <form action="Controller?command=edititem" method="POST">
                                                                                    <input type="hidden" name="item_id" id="itemForUpdate" value="">
                                                                                    <input type="hidden" name="itemEditMode" value="updateitem">
                                                                                    <button type="submit" class="btn btn-warning btn-lg">
                                                                                        <span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                                                                                </form>
                                                                            </div>
                                                                            <div class="col-xs-8 col-sm-9 col-md-8">
                                                                                <br>
                                                                                <h4 class="modal-title custom_align">
                                                                                    By clicking <strong class="label label-primary">Update</strong>, update current item mode.
                                                                                </h4>
                                                                            </div>
                                                                        </div>
                                                                            <%----%>
                                                                        <br>
                                                                            <%----%>
                                                                        <div class="row">
                                                                            <div class="col-xs-4 col-sm-3 col-md-4">
                                                                                <form action="Controller?command=edititem" method="POST">
                                                                                    <input type="hidden" name="item_id" id="itemForCloneId" value="">
                                                                                    <input type="hidden" name="itemEditMode" value="additem">
                                                                                    <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-ok-sign"></span>New like this</button>
                                                                                </form>
                                                                            </div>
                                                                            <div class="col-xs-8 col-sm-9 col-md-8">
                                                                                <br>
                                                                                <h4 class="modal-title custom_align">
                                                                                    By clicking <strong class="label label-primary">New like this</strong>, copy current item mode.
                                                                                </h4>
                                                                            </div>
                                                                        </div>
                                                                            <%----%>
                                                                    </div>
                                                                    <div class="modal-footer ">
                                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    </div>
                                                                </div>
                                                                <!-- /.modal-content -->
                                                            </div>
                                                            <!-- /.modal-dialog -->
                                                        </div>
                                                        <%--test edit model--%>

                                                        <%--test delete modal--%>
                                                        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">

                                                                        <%----%>
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                                                        <h4 class="modal-title custom_align">Delete item?</h4>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="row">
                                                                            <div class="col-xs-4 col-sm-3 col-md-4">
                                                                                <form action="Controller?command=deleteitem" method="POST">
                                                                                    <input type="text" name="for_delete_item_id" id="itemForDeleteId" value="">
                                                                                    <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-trash"></span>Delete item</button>
                                                                                </form>
                                                                            </div>
                                                                            <div class="col-xs-8 col-sm-9 col-md-8">
                                                                                <br>
                                                                                <h4 class="modal-title custom_align">
                                                                                    By clicking <strong class="label label-primary">Delete item</strong>, delete item from database.
                                                                                </h4>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer ">
                                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    </div>
                                                                        <%----%>
                                                                </div>
                                                                <!-- /.modal-content -->
                                                            </div>
                                                            <!-- /.modal-dialog -->
                                                        </div>
                                                        <%--/test delete modal--%>

                                                        <%--test data read for data-target="#edit" data-content="${item.id}"--%>
                                                        <script>
                                                            // при открытии модального окна
                                                            $('#edit').on('show.bs.modal', function (event) {
                                                                // получить кнопку, которая его открыло
                                                                var button = $(event.relatedTarget)
                                                                // извлечь информацию из атрибута data-content
                                                                var content = button.data('content')
                                                                // вытягившаем из этой страницы элементы с id: itemForUpdate и itemForCloneId
                                                                var itemForUpdate = document.getElementById('itemForUpdate');
                                                                var itemForCloneId = document.getElementById('itemForCloneId');
                                                                // записываем в их value данные из переменной content
                                                                itemForUpdate.value = content;
                                                                itemForCloneId.value = content;
                                                                // вывести эту информацию в элемент, имеющий id="content"
                                                                $(this).find('#content').text(content);
                                                            })
                                                        </script>
                                                        <%--/test data read for data-target="#edit" data-content="${item.id}"--%>

                                                        <%--test data read for data-target="#delete" data-content="${item.id}"--%>
                                                        <script>
                                                            // при открытии модального окна
                                                            $('#delete').on('show.bs.modal', function (event) {
                                                                // получить кнопку, которая его открыло
                                                                var button = $(event.relatedTarget)
                                                                // извлечь информацию из атрибута data-content
                                                                var content = button.data('content')
                                                                // вытягившаем из этой страницы элементы с id: itemForUpdate и itemForCloneId
                                                                var itemForDeleteId = document.getElementById('itemForDeleteId');
                                                                // записываем в их value данные из переменной content
                                                                itemForDeleteId.value = content;
                                                            })
                                                        </script>
                                                        <%--/test data read for data-target="#delete" data-content="${item.id}"--%>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>

                                                <div class="clearfix"></div>
                                                <ul class="pagination pull-right">
                                                    <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
                                                    <li class="active"><a href="#">1</a></li>
                                                    <li><a href="#">2</a></li>
                                                    <li><a href="#">3</a></li>
                                                    <li><a href="#">4</a></li>
                                                    <li><a href="#">5</a></li>
                                                    <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                                                </ul>
                                            </div><%--table end--%>

                                            </div>
                                        </div>
                                    </div>
                                <%--</c:forEach>--%>
                                <%--/TEST ORDER LIST--%>
                            </div>
                            <div class="tab-pane fade in" id="tab2">
                                <%--TEST USER DATA FIELDS--%>
                                <div class="container">

                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                            <form role="form" action="Controller?command=userupdate" method="post">
                                                <fieldset>
                                                    <h2><fmt:message key="login.form.sign_in.message" bundle="${rb}"/></h2>
                                                    <hr class="colorgraph">
                                                    <label for="filter"><fmt:message key="login.form.language_selector" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${emailValidationFeedback}">
                                                        <!-- default <div class="form-group"> -->
                                                        <input type="text" name="name" id="name" class="form-control input-lg"
                                                               placeholder="<fmt:message key="login.form.email.placeholder" bundle="${rb}"/>"
                                                               value="<%=(request.getParameter("email")!=null)?request.getParameter("email"):""%>"><%--заполнение формы из request--%>
                                                        <%--<span class="glyphicon glyphicon-ok form-control-feedback"></span><!-- test icon  -->--%>
                                                        <span class="glyphicon ${emailGlyphiconType} form-control-feedback"></span><!-- test icon  -->
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
                                <%--/TEST USER DATA FIELDS--%>
                            </div>
                            <div class="tab-pane fade in" id="tab3">
                                <%--<h3>This is tab 3</h3>--%>
                                <%--TEST CHANGE PASSWORD FORM--%>
                                <div class="container">

                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                            <form role="form" action="Controller?command=userlogin" method="post">
                                                <fieldset>
                                                    <h2><fmt:message key="login.form.sign_in.message" bundle="${rb}"/></h2>
                                                    <hr class="colorgraph">
                                                    <label for="filter"><fmt:message key="login.form.language_selector" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${passwordValidationFeedback}">
                                                        <input type="password" name="password" id="password" class="form-control input-lg"
                                                               placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                                                               value="<%=(request.getParameter("password")!=null)?request.getParameter("password"):""%>"><%--заполнение формы из request--%>
                                                        <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                                                        <!-- test icon  -->
                                                    </div>

                                                    <label for="filter"><fmt:message key="login.form.language_selector" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${passwordValidationFeedback}">
                                                        <input type="password" name="password" id="password" class="form-control input-lg"
                                                               placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                                                               value="<%=(request.getParameter("password")!=null)?request.getParameter("password"):""%>"><%--заполнение формы из request--%>
                                                        <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                                                        <!-- test icon  -->
                                                    </div>

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
                                <%--/TEST CHANGE PASSWORD FORM--%>
                            </div>
                        </div>
                    </div>
                </div>
                <%--/TEST USER PROFILE CONTENT--%>

            </div><%--/body--%>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%--/TEST MODAL USER PROFILE--%>

<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>

<script>
    $('#myModalUserProfile').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>

<%--test shoping cart--%>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Total</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <div class="alert alert-warning" role="alert">
                    <h1>New Order Id: ${currentUserOrder.id}, from: ${currentOrderFormattedDate}</h1>
                </div>
                <br>
                <h2>Items list</h2>
                <br>
                <c:if test="${currentUserCart.size() == 0}">
                <p><h4>Order is empty</h4><p>
                    </c:if>
                    <c:forEach var="item" items="${currentUserOrder.itemList}">
                    <tr>
                        <td class="col-sm-8 col-md-6">
                            <div class="media">
                                <a class="thumbnail pull-left" href="Controller?command=showitem&item_id=${item.id}"> <img class="media-object" src="${item.itemSmallPicturePath350x260}" style="width: 72px; height: 72px;"> </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="Controller?command=showitem&item_id=${item.id}">${item.itemName}</a></h4>
                                    <h5 class="media-heading">Category: <a href="#"><br>${item.itemCategory.categoryName}</a></h5>
                                    <span>Status: </span><span class="text-success"><strong>${item.itemStatus}</strong></span>
                                </div>
                            </div></td>

                        <td class="col-sm-1 col-md-1" style="text-align: center">
                            <input class="form-control" id="exampleInputEmail1" value="1" type="email">
                        </td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>

                        <form action="Controller?command=removeitemfromorder" method="POST">
                            <input type="hidden" name="currentUserOrderId" value="${currentUserOrder.id}">
                                <input type="hidden" name="itemForRemoveId" value="${item.id}">
                            <td class="col-sm-1 col-md-1">
                                <button type="submit" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove"></span> Remove
                                </button></td>
                        </form>
                    </tr>
                    </c:forEach>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><h5>Subtotal</h5></td>
                        <td class="text-right"><h5><strong>$${currentUserOrder.summaryPrice}</strong></h5></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><h5>Discount</h5></td>
                        <td class="text-right"><h5><strong>$0.00</strong></h5></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><strong>$${currentUserOrder.summaryPrice}</strong></h3></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <form action="Controller?command=cancelorder" method="POST">
                        <td>
                            <button type="submit" class="btn btn-danger">
                                <input type="hidden" name="currentUserOrderId" value="${currentUserOrder.id}">
                                <span class="glyphicon glyphicon-remove-circle"></span> Cancel order
                            </button></td>
                            </form>
                        <form action="Controller?command=payorder" method="POST">
                            <input type="hidden" name="currentUserOrderId" value="${currentUserOrder.id}">
                        <td>
                            <button type="submit" class="btn btn-success">
                                Pay order <span class="glyphicon glyphicon-usd"></span>
                            </button></td>
                            </form>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%--/test shoping cart--%>


<%--Slider Products--%>

<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,400,700&subset=latin-ext" rel="stylesheet">

<!--Item slider text-->
<div class="container">
    <div class="row" id="slider-text">
        <div class="col-md-6">
            <h2><fmt:message key="item_carousel.new_collection" bundle="${rb}"/></h2>
        </div>
    </div>
</div>

<!-- Item slider-->
<div class="container-fluid">

    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="carousel carousel-showmanymoveone slide" id="itemslider">
                <div class="carousel-inner">

                    <%--<c:set var="sixItemCarousel" value="${sessionScope.sixItemCarousel}"/> &lt;%&ndash;не обязательно,
                     сам подтянет с сессии по имени sixItemCarousel&ndash;%&gt;--%>

                    <div class="item active"><%--Обязательно должен быть - это первый элемент карусели--%>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="Controller?command=showitem&item_id=${sixItemCarousel.get(0).getId()}"><img src="${sixItemCarousel.get(0).getItemSmallPicturePath350x260()}"
                                                                                                                 class="img-responsive center-block"></a>
                            <h4 class="text-center">${sixItemCarousel.get(0).getItemName()}</h4> <%--test name from list--%>
                            <h5 class="text-center">${sixItemCarousel.get(0).getItemPrice()}</h5>
                        </div>
                    </div>

                    <c:forEach var="item" begin="1" items="${sixItemCarousel}">
                        <div class="item">
                            <div class="col-xs-12 col-sm-6 col-md-2">
                                <a href="Controller?command=showitem&item_id=${item.id}"><img src="${item.itemSmallPicturePath350x260}"
                                                                                              class="img-responsive center-block"></a>
                                <h4 class="text-center">${item.itemName}</h4>
                                <h5 class="text-center">${item.itemPrice}</h5>
                            </div>
                        </div>
                    </c:forEach>

                </div>

                <div id="slider-control">
                    <a class="left carousel-control" href="#itemslider" data-slide="prev"><img
                            src="https://s12.postimg.org/uj3ffq90d/arrow_left.png" alt="Left"
                            class="img-responsive"></a>
                    <a class="right carousel-control" href="#itemslider" data-slide="next"><img
                            src="https://s12.postimg.org/djuh0gxst/arrow_right.png" alt="Right" class="img-responsive"></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Item slider end-->

<%--/Slider Products--%>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/tether.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../view.components/js/bootstrap.min.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/formain_fixed_header_elements.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/for_user_profile.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/forlogin.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/for_slider_products.js"></script>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>
