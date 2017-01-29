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
                    <div class="row"><a href="#"></span>Hello!<br>${sessionScope.currentUserInSystem.email}</a></div>
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
                                            <input type="hidden" name="from_page" value="USER_MANAGEMENT_PAGE"> </input>
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

<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });
</script>


<%--Item management form--%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

<div class="container">
    <div class="row">
        <div class="col-md-12">

            <h1>${itemManagementMessage}</h1>
                <br>

            <%--category choose--%>
            <form action="Controller?command=gotoitemmanagementpage" method="POST">
            <c:choose>
                <c:when test="${itemListByCategory.get(0).getItemCategory() == 'FOR_SELF_DEFENSE'}">
                    <c:set var="isOneSelected" value="selected"/>
                </c:when>
                <c:when test="${itemListByCategory.get(0).getItemCategory() == 'FOR_RESPECT'}">
                    <c:set var="isTwoSelected" value="selected"/>
                </c:when>
                <c:when test="${itemListByCategory.get(0).getItemCategory() == 'ULTIMATE_SOLUTIONS'}">
                    <c:set var="isThreeSelected" value="selected"/>
                </c:when>
                <c:otherwise>
                    <c:set var="isOneSelected" value="selected"/>
                </c:otherwise>
            </c:choose>
            <div class="form-group">
               <h2><fmt:message key="select_category" bundle="${rb}"/></h2>
                <select class="form-control" id="itemCategoryId" name="itemCategoryId">
                    <option value="1" ${isOneSelected}><fmt:message key="category.for_self_defense" bundle="${rb}"/></option>
                    <option value="2" ${isTwoSelected}><fmt:message key="category.for_respect" bundle="${rb}"/></option>
                    <option value="3" ${isThreeSelected}><fmt:message key="category.ultimate_solutions" bundle="${rb}"/></option>
                </select>
            </div>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="submit" class="btn btn-success btn-lg"><span class="glyphicon glyphicon-th-list"></span><fmt:message key="show_items_button" bundle="${rb}"/></button>
                </div>
            </form>
            <%--/category choose--%>

            <%--add new item--%>
            <h2><fmt:message key="add_new_item" bundle="${rb}"/></h2>
            <form action="Controller?command=edititem" method="POST">
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="submit" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-plus"></span><fmt:message key="add_new_item" bundle="${rb}"/></button>
            </div>
                </form>
            <%--/add new item--%>

            <br>

            <h4><fmt:message key="category_items" bundle="${rb}"/>.</h4>
            <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">

                    <thead>

                    <th><input type="checkbox" id="checkall" /></th>
                    <th>Id</th>
                    <th><fmt:message key="item_name" bundle="${rb}"/></th>
                    <th><fmt:message key="small_description" bundle="${rb}"/></th>
                    <th><fmt:message key="full_description" bundle="${rb}"/></th>
                    <th><fmt:message key="price" bundle="${rb}"/></th>
                    <th><fmt:message key="rating" bundle="${rb}"/></th>
                    <th><fmt:message key="category" bundle="${rb}"/></th>
                    <th><fmt:message key="status" bundle="${rb}"/></th>

                    <th><fmt:message key="edit" bundle="${rb}"/></th>
                    <th><fmt:message key="delete" bundle="${rb}"/></th>
                    </thead>
                    <tbody>

                <c:forEach var="item" items="${itemListByCategory}">
                    <tr>
                        <td><input type="checkbox" class="checkthis" /></td>
                        <td>${item.id}</td>
                        <td>${item.itemName}</td>
                        <td>${item.itemSmallDescription}</td>
                        <td>${item.itemFullDescription}</td>
                        <td>${item.itemPrice}</td>
                        <td>${item.itemRating}</td>
                        <td>${item.itemCategory}</td>
                        <td>${item.itemStatus}</td>


            <%--<form action="Controller?command=edititem" method="POST">--%>
                <%--<input type="hidden" name="item_id" value="${item.id}"> </input>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Edit">--%>
                            <%--<button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-content="${item.id}">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span></button></p></td>--%>
                            <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Delete">--%>
                            <%--<button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >--%>
                                <%--<span class="glyphicon glyphicon-trash"></span></button></p></td>--%>




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
                                    <h4 class="modal-title custom_align" id="Heading"><fmt:message key="select_edit_mode" bundle="${rb}"/></h4>
                                </div>
                                <div class="modal-body">
                                        <%----%>
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-3 col-md-4">
                                            <form action="Controller?command=edititem" method="POST">
                                                <input type="hidden" name="item_id" id="itemForUpdate" value="">
                                                <input type="hidden" name="itemEditMode" value="updateitem">
                                                <button type="submit" class="btn btn-warning btn-lg">
                                                    <span class="glyphicon glyphicon-ok-sign"></span><fmt:message key="update" bundle="${rb}"/></button>
                                            </form>
                                        </div>
                                        <div class="col-xs-8 col-sm-9 col-md-8">
                                            <br>
                                            <h4 class="modal-title custom_align">
                                                <fmt:message key="by_click" bundle="${rb}"/> <strong class="label label-primary"><fmt:message key="update" bundle="${rb}"/></strong>, <fmt:message key="update_description" bundle="${rb}"/>
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
                                                        <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-ok-sign"></span><fmt:message key="new_like_this" bundle="${rb}"/></button>
                                                    </form>
                                                </div>
                                                <div class="col-xs-8 col-sm-9 col-md-8">
                                                    <br>
                                                    <h4 class="modal-title custom_align">
                                                        <fmt:message key="by_click" bundle="${rb}"/> <strong class="label label-primary"><fmt:message key="new_like_this" bundle="${rb}"/></strong>, <fmt:message key="new_like_this_description" bundle="${rb}"/>
                                                    </h4>
                                                </div>
                                            </div>
                                            <%----%>
                                </div>
                                <div class="modal-footer ">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close" bundle="${rb}"/></button>
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
                                            <h4 class="modal-title custom_align"><fmt:message key="delete_item" bundle="${rb}"/>?</h4>
                                        </div>
                                        <div class="modal-body">
                                <div class="row">
                                    <div class="col-xs-4 col-sm-3 col-md-4">
                                        <form action="Controller?command=deleteitem" method="POST">
                                            <input type="text" name="for_delete_item_id" id="itemForDeleteId" value="">
                                            <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-trash"></span><fmt:message key="delete_item" bundle="${rb}"/></button>
                                        </form>
                                    </div>
                                    <div class="col-xs-8 col-sm-9 col-md-8">
                                        <br>
                                        <h4 class="modal-title custom_align">
                                            <fmt:message key="by_click" bundle="${rb}"/> <strong class="label label-primary"><fmt:message key="delete_item" bundle="${rb}"/></strong>, <fmt:message key="delete_item_description" bundle="${rb}"/>
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
                    <%--<tr>--%>
                        <%--<td><input type="checkbox" class="checkthis" /></td>--%>
                        <%--<td>Mohsin</td>--%>
                        <%--<td>Irshad</td>--%>
                        <%--<td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>--%>
                        <%--<td>isometric.mohsin@gmail.com</td>--%>
                        <%--<td>+923335586757</td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>--%>
                    <%--</tr>--%>


                    <%--<tr>--%>
                        <%--<td><input type="checkbox" class="checkthis" /></td>--%>
                        <%--<td>Mohsin</td>--%>
                        <%--<td>Irshad</td>--%>
                        <%--<td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>--%>
                        <%--<td>isometric.mohsin@gmail.com</td>--%>
                        <%--<td>+923335586757</td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>--%>
                    <%--</tr>--%>



                    <%--<tr>--%>
                        <%--<td><input type="checkbox" class="checkthis" /></td>--%>
                        <%--<td>Mohsin</td>--%>
                        <%--<td>Irshad</td>--%>
                        <%--<td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>--%>
                        <%--<td>isometric.mohsin@gmail.com</td>--%>
                        <%--<td>+923335586757</td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>--%>
                    <%--</tr>--%>


                    <%--<tr>--%>
                        <%--<td><input type="checkbox" class="checkthis" /></td>--%>
                        <%--<td>Mohsin</td>--%>
                        <%--<td>Irshad</td>--%>
                        <%--<td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>--%>
                        <%--<td>isometric.mohsin@gmail.com</td>--%>
                        <%--<td>+923335586757</td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-content="Содержимое 1..."><span class="glyphicon glyphicon-pencil"></span></button></p></td>--%>
                        <%--<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>--%>
                    <%--</tr>--%>





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


<%--<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>--%>
                <%--<h4 class="modal-title custom_align" id="Heading">Select Edit Model</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<h4 class="modal-title custom_align">Edit - edit current item mode.</h4>--%>
                <%--<br>--%>
                <%--<h4 class="modal-title custom_align">Add - copy current item mode.</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-footer ">--%>
                <%----%>
                <%--<button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>--%>
                <%--<button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!-- /.modal-content -->--%>
    <%--</div>--%>
    <%--<!-- /.modal-dialog -->--%>
<%--</div>--%>



<%--<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>--%>
                <%--<h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>

                <%--<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>--%>

            <%--</div>--%>
            <%--<div class="modal-footer ">--%>
                <%--<button type="button" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!-- /.modal-content -->--%>
    <%--</div>--%>
    <%--<!-- /.modal-dialog -->--%>
<%--</div>--%>



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
<script src="../../view.components/js/for_slider_products.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/for_item_management_page.js"></script>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>
