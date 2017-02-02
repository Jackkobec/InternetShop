<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 12.01.2017
  Time: 8:33
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
    <link type="text/css" href="view.components/css/item_page.css" rel="stylesheet">

    <!-- Добавляем свой стиль user profile -->
    <link type="text/css" href="view.components/css/user_profile.css" rel="stylesheet">

    <!-- Добавляем свой стиль header picture style -->
    <link type="text/css" href="view.components/css/header_style.css" rel="stylesheet">
</head>


<body>

<%--Header Image--%>
<div class="jumbotron">
    <a href="Controller?command=gotomainpage">
        <div class="container text-left my-div">
            <h1><fmt:message key="head.big_text" bundle="${rb}"/></h1>
            <h2><fmt:message key="head.small_text" bundle="${rb}"/></h2>
        </div></a>
</div>
<%--/Header Image--%>

<%--USER MENU--%>
<%--GLOBAL BLOCK. USER MENU: Admin, Cart, User Profile, Log Out.--%>
<%--Fixed header elements--%>
<div class="container">
    <div class="row">
        <div class="header">
            <ul>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-off mycolorspan-red"></div>
                    <div class="row"><a data-toggle="modal" href="#myModalLogOut"></span><fmt:message key="logout" bundle="${rb}"/></a></div>
                </li>
                <li2>
                    <div class="row"><span class="glyphicon glyphicon-user mycolorspan"></div>
                    <div class="row"><a data-toggle="modal" href="#myModalUserProfile"></span><fmt:message key="user" bundle="${rb}"/>
                        <br>${sessionScope.currentUserInSystem.email}</a></div>
                </li2>

                <c:choose>
                    <c:when test="${currentUserCart.size() == 0}">
                        <c:set var="spanClass" value="mycolorspan"/>
                    </c:when>
                    <c:when test="${currentUserCart.size() > 0}">
                        <c:set var="spanClass" value="mycolorspan-gold"/>
                        <c:set var="cartText" value="(${currentUserCart.size()})"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="spanClass" value="mycolorspan"/>
                    </c:otherwise>
                </c:choose>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-shopping-cart ${spanClass}"></div>
                    <div class="row"><a data-toggle="modal" href="#myModal"></span><fmt:message key="cart" bundle="${rb}"/>${cartText}</a></div>
                </li>

                <c:if test="${currentUserInSystem.getUserType() == 'ADMIN'}">
                    <li>
                        <div class="row"><span class="glyphicon glyphicon-flash mycolorspan-red"></div>
                        <div class="row"><a data-toggle="modal" href="#myAdminModal"></span><fmt:message key="admin" bundle="${rb}"/></a></div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<%--/Fixed header elements--%>

<%--Admin Modal--%>
<!-- Modal -->
<div class="modal fade" id="myAdminModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin_panel" bundle="${rb}"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotousermanagementpage">
                    <button type="submit" class="btn btn-warning btn-lg btn-block">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span><fmt:message key="user_management" bundle="${rb}"/></button>
                </form>
                <br><br>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="gotoitemmanagementpage">
                    <button type="submit" class="btn btn-success btn-lg btn-block">
                        <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span><fmt:message key="item_management" bundle="${rb}"/></button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close" bundle="${rb}"/></button>
            </div>
        </div>
    </div>
</div>
<%--/Admin Modal--%>
<%--script for myAdminModal--%>
<script>
    $('#myAdminModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>
<%--/script for myAdminModal--%>


<%--test modal cart--%>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><fmt:message key="cart" bundle="${rb}"/></h4>
            </div>
            <div class="modal-body"><%--modal body--%>
                <%--test shoping cart--%>
                <div class="container" style="max-width: 100%; padding-left: 0px; padding-right: 0px;"> <%--test style чтобы не вылазило за экран--%>
                    <div class="row">
                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th><fmt:message key="product" bundle="${rb}"/></th>
                                    <th><fmt:message key="quantity" bundle="${rb}"/></th>
                                    <th class="text-center"><fmt:message key="price" bundle="${rb}"/></th>
                                    <th class="text-center"><fmt:message key="total" bundle="${rb}"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:if test="${currentUserCart.size() == 0}">
                                <p><h4><fmt:message key="cart_is_empty" bundle="${rb}"/></h4><p>
                                    </c:if>
                                    <c:forEach var="item" items="${currentUserCart}">
                                    <tr>
                                        <td class="col-sm-8 col-md-6">
                                            <div class="media">
                                                <a class="thumbnail pull-left" href="Controller?command=showitem&item_id=${item.id}"> <img class="media-object" src="${item.itemSmallPicturePath350x260}" style="width: 72px; height: 72px;"> </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading"><a href="Controller?command=showitem&item_id=${item.id}">${item.itemName}</a></h4>
                                                    <h5 class="media-heading"><fmt:message key="category" bundle="${rb}"/>: <a href="#"><br>${item.itemCategory.categoryName}</a></h5>
                                                    <span><fmt:message key="status" bundle="${rb}"/>: </span><span class="text-success"><strong>${item.itemStatus}</strong></span>
                                                </div>
                                            </div></td>

                                        <td class="col-sm-1 col-md-1" style="text-align: center">
                                            <input class="form-control" id="exampleInputEmail12" value="1" type="email">
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>

                                        <form action="Controller" method="POST">
                                            <input type="hidden" name="command" value="removeitemfromcart">
                                            <input type="hidden" name="item_id" value="${item.id}">
                                            <input type="hidden" name="from_page" value="ITEM_PAGE">
                                            <td class="col-sm-1 col-md-1">
                                                <button type="submit" class="btn btn-danger">
                                                    <span class="glyphicon glyphicon-remove"></span> <fmt:message key="remove" bundle="${rb}"/>
                                                </button></td>
                                        </form>
                                    </tr>
                                    </c:forEach>

                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h5><fmt:message key="subtotal" bundle="${rb}"/></h5></td>
                                        <td class="text-right"><h5><strong>$${summaryCartPrice}</strong></h5></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h5><fmt:message key="discount" bundle="${rb}"/></h5></td>
                                        <td class="text-right"><h5><strong>$0.00</strong></h5></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h3><fmt:message key="total" bundle="${rb}"/></h3></td>
                                        <td class="text-right"><h3><strong>$${summaryCartPrice}</strong></h3></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                <span class="glyphicon glyphicon-shopping-cart"></span> <fmt:message key="continue_shopping" bundle="${rb}"/>
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
                                        <form action="Controller" method="POST">
                                            <input type="hidden" name="command" value="makeorder"><%--command as hiden parameter--%>
                                            <td>
                                                <button type="submit" class="btn btn-success ${isDisable}">
                                                    <fmt:message key="make_order" bundle="${rb}"/> <span class="glyphicon glyphicon-ok"></span>
                                                </button></td>
                                        </form>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%--/test shoping cart--%>

            </div><%--/modal body--%>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close" bundle="${rb}"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--/test modal cart--%>

<%--TEST MODAL USER PROFILE--%>
<!-- Modal -->
<div class="modal fade" id="myModalUserProfile" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel2"><fmt:message key="user_profile" bundle="${rb}"/></h4>
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
                                <div class="hidden-xs"><fmt:message key="my_orders" bundle="${rb}"/></div>
                            </button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                <div class="hidden-xs"><fmt:message key="user_info" bundle="${rb}"/></div>
                            </button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><span class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
                                <div class="hidden-xs"><fmt:message key="change_password" bundle="${rb}"/></div>
                            </button>
                        </div>
                    </div>

                    <div class="well">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="tab1">
                                <h2><fmt:message key="order_list" bundle="${rb}"/></h2>
                                <form action="Controller" method="POST">
                                    <input type="hidden" name="command" value="removeallnotpaidorders">
                                    <input type="hidden" name="from_page" value="ORDER_PAGE">
                                    <td class="col-sm-1 col-md-1">
                                        <button type="submit" class="btn btn-danger">
                                            <span class="glyphicon glyphicon-remove"></span> <fmt:message key="remove_all_not_paid_orders" bundle="${rb}"/>
                                        </button></td>
                                </form>
                                <%--TEST ORDER LIST--%>
                                <%--<c:forEach var="order" items="${currentUserOrders}">--%>
                                <div class="container" style="max-width: 100%; padding-left: 0px; padding-right: 0px;"> <%--test style чтобы не вылазило за экран--%>
                                    <%--<div class="container">--%>
                                    <div class="row">
                                        <div class="col-md-12">

                                            <div class="table-responsive">
                                                <table id="mytable" class="table table-bordred table-striped">

                                                    <thead>

                                                    <th><input type="checkbox" id="checkall" /></th>
                                                    <th><fmt:message key="order_id" bundle="${rb}"/></th>
                                                    <th><fmt:message key="order_date_and_time" bundle="${rb}"/></th>
                                                    <th><fmt:message key="items_count" bundle="${rb}"/></th>
                                                    <th><fmt:message key="summary_price" bundle="${rb}"/></th>
                                                    <th><fmt:message key="order_status" bundle="${rb}"/></th>

                                                    <th><fmt:message key="actions" bundle="${rb}"/></th>
                                                    <%--<th>Delete</th>--%>
                                                    </thead>
                                                    <tbody>

                                                    <c:forEach var="order" items="${currentUserOrders}">
                                                        <tr>
                                                            <td><input type="checkbox" class="checkthis" /></td>
                                                            <td>${order.id}</td>
                                                            <td>${order.orderDateAndTime}</td>
                                                            <td>n/a</td>
                                                            <td><span class="text-info"><strong>${order.summaryPrice}</strong></span></td>
                                                            <c:choose>
                                                                <c:when test="${order.orderStatus == 1}">
                                                                    <c:set var="orderStatusName" value="NOT PAID"/>
                                                                    <c:set var="spanClass" value="text-danger"/>
                                                                </c:when>
                                                                <c:when test="${order.orderStatus == 2}">
                                                                    <c:set var="orderStatusName" value="DONE"/>
                                                                    <c:set var="spanClass" value="text-success"/>
                                                                </c:when>
                                                            </c:choose>
                                                            <td><span class="${spanClass}"><strong>${orderStatusName}</strong></span></td>

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
                                                            <c:if test="${order.orderStatus == 1}">
                                                                <form action="Controller" method="POST">
                                                                    <input type="hidden" name="command" value="payorder">
                                                                    <input type="hidden" name="currentUserOrderId" value="${order.id}">
                                                                    <td><p data-placement="top" data-toggle="tooltip" title="Pay">
                                                                        <button class="btn btn-success btn-sm" data-title="Edit" data-toggle="modal" data-target="#edit" data-content="${item.id}">
                                                                            <span class="glyphicon glyphicon-play"></span></button></p></td>
                                                                </form>
                                                            </c:if>

                                                            <c:if test="${order.orderStatus == 1}">
                                                                <form action="Controller" method="POST">
                                                                    <input type="hidden" name="command" value="editorder">
                                                                    <input type="hidden" name="currentUserOrderForPaymentId" value="${order.id}">
                                                                    <td><p data-placement="top" data-toggle="tooltip" title="Edit">
                                                                        <button class="btn btn-primary btn-sm" data-title="Edit" data-toggle="modal" data-target="#edit" data-content="${item.id}">
                                                                            <span class="glyphicon glyphicon-pencil"></span></button></p></td>
                                                                </form>
                                                            </c:if>
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
                                                            <form action="Controller" method="POST">
                                                                <input type="hidden" name="command" value="cancelorder">
                                                                <input type="hidden" name="currentUserOrderId" value="${order.id}">
                                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete">
                                                                    <button class="btn btn-danger btn-sm" data-title="Delete" data-toggle="modal" data-target="#delete" data-content="${item.id}">
                                                                        <span class="glyphicon glyphicon-trash"></span></button></p></td>
                                                            </form>
                                                        </tr>

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
                                <div class="container" style="max-width: 100%; padding-left: 0px; padding-right: 0px;"> <%--test style чтобы не вылазило за экран--%>
                                    <%--<div class="container">--%>

                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                            <form role="form" action="Controller" method="post">
                                                <input type="hidden" name="command" value="userupdate">
                                                <fieldset>
                                                    <h2><fmt:message key="user_info_title" bundle="${rb}"/></h2>
                                                    <hr class="colorgraph">
                                                    <label for="filter"><fmt:message key="name_label" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${emailValidationFeedback}">
                                                        <!-- default <div class="form-group"> -->
                                                        <input type="text" name="name" id="name" class="form-control input-lg"
                                                               placeholder="<fmt:message key="name_placeholder" bundle="${rb}"/>"
                                                               value="${currentUserInSystem.name}"><%--заполнение формы из request--%>
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
                                                                   value="<fmt:message key="update_user_info" bundle="${rb}"/>">
                                                        </div>

                                                        <%--<div class="notification notification-error logged-out"><!-- notification test-->--%>
                                                        <div class="notification notification-error notification-from-command"><!-- notification test-->
                                                            <fmt:message key="${notificationMessage}" bundle="${rb}"/>
                                                        </div>
                                                        <div class="notification notification-error logged-oute">
                                                            You logged out successfully!
                                                        </div><!-- /notification test-->


                                                        <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                                        <%--<a href="Controller?command=register" class="btn btn-lg btn-primary btn-block">--%>
                                                        <%--<fmt:message key="login.form.register.button" bundle="${rb}"/></a>--%>
                                                        <%--</div>--%>
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
                                <div class="container" style="max-width: 100%; padding-left: 0px; padding-right: 0px;"> <%--test style чтобы не вылазило за экран--%>
                                    <%--<div class="container">--%>

                                    <div class="row">
                                        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                                            <form role="form" action="Controller" method="post">
                                                <input type="hidden" name="command" value="changeuserpassword">
                                                <fieldset>
                                                    <h2><fmt:message key="change_password_title" bundle="${rb}"/></h2>
                                                    <hr class="colorgraph">
                                                    <label for="filter"><fmt:message key="current_password_label" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${passwordValidationFeedback}">
                                                        <input type="password" name="password" id="password" class="form-control input-lg"
                                                               placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                                                               value="">
                                                        <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                                                        <!-- test icon  -->
                                                    </div>

                                                    <label for="filter"><fmt:message key="new_password_label" bundle="${rb}"/></label>
                                                    <div class="form-group has-feedback ${passwordValidationFeedback}">
                                                        <input type="password" name="newPassword" id="newPassword" class="form-control input-lg"
                                                               placeholder="<fmt:message key="login.form.password.placeholder" bundle="${rb}"/>"
                                                               value="">
                                                        <span class="glyphicon ${passwordGlyphiconType} form-control-feedback"></span>
                                                        <!-- test icon  -->
                                                    </div>

                                                    <hr class="colorgraph">
                                                    <div class="row">
                                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                                            <%--<input type="submit" class="btn btn-lg btn-success btn-block" value="Sign In"><!-- default  -->--%>
                                                            <input type="submit" class="btn btn-lg btn-success btn-block ${notification}"
                                                                   value="<fmt:message key="change_password_button" bundle="${rb}"/>">
                                                        </div>

                                                        <%--<div class="notification notification-error logged-out"><!-- notification test-->--%>
                                                        <div class="notification notification-error notification-from-command"><!-- notification test-->
                                                            <fmt:message key="${notificationMessage}" bundle="${rb}"/>
                                                        </div>
                                                        <div class="notification notification-error logged-oute">
                                                            You logged out successfully!
                                                        </div><!-- /notification test-->

                                                        <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                                        <%--<a href="Controller?command=register" class="btn btn-lg btn-primary btn-block">--%>
                                                        <%--<fmt:message key="login.form.register.button" bundle="${rb}"/></a>--%>
                                                        <%--</div>--%>
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
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close" bundle="${rb}"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%--/TEST MODAL USER PROFILE--%>

<%--TEST MODAL LOGOUT--%>
<div class="modal fade" id="myModalLogOut" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <%----%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align"><fmt:message key="logout" bundle="${rb}"/>?</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-4 col-sm-3 col-md-4">
                        <form action="Controller" method="POST">
                            <input type="hidden" name="command" id="command" value="userlogout">
                            <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-off"></span><fmt:message key="logout" bundle="${rb}"/></button>
                        </form>
                    </div>
                    <div class="col-xs-8 col-sm-9 col-md-8">
                        <br>
                        <h4 class="modal-title custom_align">
                            <fmt:message key="by_click" bundle="${rb}"/> <strong class="label label-primary"><fmt:message key="logout" bundle="${rb}"/></strong>, <fmt:message key="logout_description" bundle="${rb}"/>
                        </h4>
                    </div>
                </div>
            </div>
            <div class="modal-footer ">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close" bundle="${rb}"/></button>
            </div>
            <%----%>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--/TEST MODAL LOGOUT--%>

<%--script for myModal--%>
<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>
<%--/script for myModalUserProfile--%>

<%--script for myModalUserProfile--%>
<script>
    $('#myModalUserProfile').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>
<%--/script for myModalUserProfile--%>

<%--script for myModalLogOut--%>
<script>
    $('#myModalLogOut').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>
<%--/script for myModalLogOut--%>
<%--/GLOBAL BLOCK. USER MENU: Admin, Cart, User Profile, Log Out.--%>
<%--/USER MENU--%>

<%--GLOBAL BLOCK. ITEM.--%>
<%--Item--%>
<div class="container-fluid">
    <div class="content-wrapper">
        <div class="item-container">
            <div class="container">
                <div class="col-md-12">
                    <div class="product col-md-3 service-image-left">

                        <center>
                            <img id="item-display" src="${currentItemForShow.itemBigPicturePath800x600}" alt=""></img>
                        </center>
                    </div>

                    <div class="container service1-items col-sm-2 col-md-2 pull-left">
                        <center>
                            <a id="item-1" class="service1-item">
                                <img src="${currentItemForShow.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                            <a id="item-2" class="service1-item">
                                <img src="${currentItemForShow.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                            <a id="item-3" class="service1-item">
                                <img src="${currentItemForShow.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                        </center>
                    </div>
                </div>

                <div class="col-md-7">
                    <div class="product-title">${currentItemForShow.itemName}</div>
                    <div class="product-desc">${currentItemForShow.itemSmallDescription}</div>

                    <div class="product-rating">
                        <c:forEach var="ratingStar" begin="1" end="${currentItemForShow.itemRating}">
                            <i class="fa fa-star gold"></i><%--<i class="fa fa-star gold"></i>  - одна звезда, золотая--%>
                        </c:forEach>
                        <c:forEach var="ratingStar" begin="${currentItemForShow.itemRating}" end="4">
                            <i class="fa fa-star-o"></i><%--<i class="fa fa-star-o"></i>   - одна звезда, не цветная--%>
                        </c:forEach>
                        <%--<i class="fa fa-star gold"></i>&lt;%&ndash;<i class="fa fa-star gold"></i>  - одна звезда, золотая&ndash;%&gt;--%>
                        <%--<i class="fa fa-star gold"></i>--%>
                        <%--<i class="fa fa-star gold"></i>--%>
                        <%--<i class="fa fa-star gold"></i>--%>
                        <%--<i class="fa fa-star-o"></i>&lt;%&ndash;<i class="fa fa-star-o"></i>   - одна звезда, не цветная&ndash;%&gt; --%>
                    </div>

                    <hr>
                    <div class="product-price">$ ${currentItemForShow.itemPrice}</div>
                    <div class="product-stock">${currentItemForShow.itemStatus}</div>
                    <hr>
                    <form action="Controller?command=addtocart" method="POST">
                        <input type="hidden" name="item_id" value="${currentItemForShow.id}"> </input>

                    <div class="btn-group cart">
                        <a href="#" data-toggle="tooltip" title="Hooray!">
                        <button type="submit" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalNotification">
                            Add to cart
                        </button></a>
                    </div>
                    </form>
                    <%--Modal -   #myModalNotification--%>
                    <!-- Modal -->
                    <div class="modal fade" id="myModalNotification" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Modal Header</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Item added to the cart.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <%--/Modal -   #myModalNotification--%>

                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="col-md-12 product-info">
                <ul id="myTab" class="nav nav-tabs nav_tabs">

                    <li class="active"><a href="#service-one" data-toggle="tab">DESCRIPTION</a></li>
                    <li><a href="#service-two" data-toggle="tab">PRODUCT INFO</a></li>
                    <li><a href="#service-three" data-toggle="tab">REVIEWS</a></li>

                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="service-one">

                        <section class="container product-info">
                            ${currentItemForShow.itemSmallDescription}

                            <h3>${currentItemForShow.itemName} Features:</h3>
                                ${currentItemForShow.itemFullDescription}
                        </section>

                    </div>
                    <div class="tab-pane fade" id="service-two">

                        <section class="container">

                        </section>

                    </div>
                    <div class="tab-pane fade" id="service-three">

                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</div>
<%--/Item--%>
<%--/GLOBAL BLOCK ITEM--%>

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

<script>
$(function () {
$('[data-toggle="tooltip"]').tooltip()
})
</script>
<script>
    $('.popover-dismiss').popover({
        trigger: 'focus'
    })
</script>


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
<!-- Добавляем свой скрипт for tooltips -->
<script src="../../view.components/js/for_item_management_page.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/forlogin.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/for_slider_products.js"></script>

<%--FOOTER--%>
<footer>
    <div class="row">
        <div class="col-lg-12">
            <div class="col-lg-3 col-md-6">
                <div class="container" style="max-width: 250%; padding-left: 0px; padding-right: 0px;"> <%--test style чтобы не вылазило за экран--%> <%--test style чтобы не вылазило за экран--%>
                <%--<div class="container">--%>

                    <div class="col-lg-3">
                        <div class="cuadro_intro_hover " style="background-color:#cccccc;">
                            <p style="text-align:center; margin-top:20px;">
                                <img src="view.components/images/Author.png" class="img-responsive" alt="">
                            </p>
                            <div class="caption">
                                <div class="blur"></div>
                                <div class="caption-text">
                                    <h3 style="border-top:2px solid white; border-bottom:2px solid white; padding:10px;">
                                        Author</h3>
                                    <p>Email: jackkobec@gmail.com<br>Skype: skypejs77</p>
                                    <a class=" btn btn-default" href="https://github.com/Jackkobec"><span
                                            class="glyphicon glyphicon-plus">GitHub</span></a>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="col-lg-3 col-md-6">
                        <h3>Categories:</h3>
                        <ul>
                            <li><a href=""><i class="fa fa-file"></i> News</a></li>
                            <li><a href=""><i class="fa fa-android"></i> Android</a></li>
                            <li><a href=""><i class="fa fa-code"></i> C#</a></li>
                            <li><a href=""><i class="fa fa-code"></i> Java</a></li>
                            <li><a href=""><i class="fa fa-book"></i> Books</a></li>
                            <li><a href=""><i class="fa fa-globe"></i> Web</a></li>
                            <li><a href=""><i class="fa fa-windows"></i> Windows</a></li>
                        </ul>
                    </div>

                    <div class="col-lg-3 col-md-6">
                        <h3>Contact:</h3>
                        <p>Have a question or feedback? Contact me!</p>
                        <p><a href="" title="Contact me!"><i class="fa fa-envelope"></i> Contact</a></p>
                        <h3>Follow:</h3>


                        <a href="" id="gh" target="_blank" title="Twitter"><span class="fa-stack fa-lg">
  <i class="fa fa-square-o fa-stack-2x"></i>
  <i class="fa fa-twitter fa-stack-1x"></i>
</span>
                            Twitter</a>
                        <a href="" target="_blank" title="GitHub"><span class="fa-stack fa-lg">
  <i class="fa fa-square-o fa-stack-2x"></i>
  <i class="fa fa-github fa-stack-1x"></i>
</span>
                            GitHub</a>


                    </div>
                    <br/>
                    <div id="fb-root"></div>


                    <div class="g-plusone" data-annotation="inline" data-width="300" data-href=""></div>

                    <!-- Helyezd el ezt a címkét az utolsó +1 gomb címke mögé. -->
                    <script type="text/javascript">
                        (function () {
                            var po = document.createElement('script');
                            po.type = 'text/javascript';
                            po.async = true;
                            po.src = 'https://apis.google.com/js/platform.js';
                            var s = document.getElementsByTagName('script')[0];
                            s.parentNode.insertBefore(po, s);
                        })();
                    </script>
                    <br/>

                    <hr>
                    <p>Copyright © Your Website | <a href="">Privacy Policy</a> | <a href="">Terms of Use</a></p>
</footer>
<%--/FOOTER--%>
</body>
</html>
