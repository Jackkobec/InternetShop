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
                                            <input class="form-control" id="exampleInputEmail1" value="1" type="email">
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>$${item.itemPrice}</strong></td>

                                        <form action="Controller?command=removeitemfromcart" method="POST">
                                            <input type="hidden" name="item_id" value="${item.id}"> </input>
                                            <input type="hidden" name="from_page" value="ITEM_PAGE"> </input>
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
                                        <form action="Controller?command=makeorder" method="POST">
                                            <td>
                                                <button type="submit" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                                </button></td>
                                        </form>
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


<%--Item--%>
<div class="container-fluid">
    <div class="content-wrapper">
        <div class="item-container">
            <div class="container">
                <div class="col-md-12">
                    <div class="product col-md-3 service-image-left">

                        <center>
                            <img id="item-display" src="${itemForEdit.itemBigPicturePath800x600}" alt=""></img>
                        </center>
                    </div>

                    <div class="container service1-items col-sm-2 col-md-2 pull-left">
                        <center>
                            <a id="item-1" class="service1-item">
                                <img src="${itemForEdit.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                            <a id="item-2" class="service1-item">
                                <img src="${itemForEdit.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                            <a id="item-3" class="service1-item">
                                <img src="${itemForEdit.itemSmallPicturePath350x260}" alt=""></img>
                            </a>
                        </center>
                    </div>
                </div>

                <div class="col-md-7">

                    <div class="modal-body">
                        <div class="form-group">
                            <label >Item Name</label>
                            <input class="form-control " type="text" placeholder="Item Name" value="${itemForEdit.itemName}">
                        </div>
                        <div class="form-group">
                            <label >Small Description</label>
                            <input class="form-control " type="text" placeholder="Small Description" value="${itemForEdit.itemSmallDescription}">
                        </div>
                        <div class="form-group">
                            <label >Full Description</label>
                            <textarea rows="7" class="form-control" placeholder="Product Info" >${itemForEdit.itemFullDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <label >Product Info</label>
                            <textarea rows="7" class="form-control" placeholder="Product Info" value="${itemForEdit.itemProductInfo}"></textarea>
                        </div>
                        <div class="form-group">
                            <label >Price</label>
                            <input class="form-control " type="text" placeholder="Price" value="${itemForEdit.itemPrice}">
                        </div>
                        <div class="form-group">
                            <label >Big Picture Path 800x600</label>
                            <input class="form-control " type="text" placeholder="Big Picture Path 800x600" value="${itemForEdit.itemBigPicturePath800x600}">
                        </div>
                        <div class="form-group">
                            <label >Small Picture Path 350x260</label>
                            <input class="form-control " type="text" placeholder="Small Picture Path 350x260" value="${itemForEdit.itemSmallPicturePath350x260}">
                        </div>
                        <c:choose>
                            <c:when test="${itemForEdit.itemRating == 1}">
                                <c:set var="raitingOne" value="selected"/>
                            </c:when>
                            <c:when test="${itemForEdit.itemRating == 2}">
                                <c:set var="raitingTwo" value="selected"/>
                            </c:when>
                            <c:when test="${itemForEdit.itemRating == 3}">
                                <c:set var="raitingThree" value="selected"/>
                            </c:when>
                            <c:when test="${itemForEdit.itemRating == 4}">
                                <c:set var="raitingFour" value="selected"/>
                            </c:when>
                            <c:when test="${itemForEdit.itemRating == 5}">
                                <c:set var="raitingFive" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="raitingFive" value="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group">
                            <label >Item Rating</label>
                            <select class="form-control" id="itemRating" name="itemRating">
                                <option value="1" ${raitingOne}>1</option>
                                <option value="2" ${raitingTwo}>2</option>
                                <option value="3" ${raitingThree}>3</option>
                                <option value="4" ${raitingFour}>4</option>
                                <option value="5" ${raitingFive}>5</option>
                            </select>
                        </div>
                        <c:choose>
                        <c:when test="${itemForEdit.itemCategory == 'FOR_SELF_DEFENSE'}">
                            <c:set var="isOneSelected" value="selected"/>
                        </c:when>
                        <c:when test="${itemForEdit.itemCategory == 'FOR_RESPECT'}">
                            <c:set var="isTwoSelected" value="selected"/>
                        </c:when>
                            <c:when test="${itemForEdit.itemCategory == 'ULTIMATE_SOLUTIONS'}">
                                <c:set var="isThreeSelected" value="selected"/>
                            </c:when>
                        <c:otherwise>
                            <c:set var="isOneSelected" value="selected"/>
                        </c:otherwise>
                        </c:choose>
                        <div class="form-group">
                            <label >Item Category</label>
                            <select class="form-control" id="itemCategory" name="itemCategory">
                                <option value="1" ${isOneSelected}>FOR_SELF_DEFENSE</option>
                                <option value="2" ${isTwoSelected}>FOR_RESPECT</option>
                                <option value="3" ${isThreeSelected}>ULTIMATE_SOLUTIONS</option>
                            </select>
                        </div>
                        <c:choose>
                            <c:when test="${itemForEdit.itemStatus == 'PRESENT'}">
                                <c:set var="isFirstStatusSelected" value="selected"/>
                            </c:when>
                            <c:when test="${itemForEdit.itemStatus == 'NOT_PRESENT'}">
                                <c:set var="isSecondStatusSelected" value="selected"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="isFirstStatusSelected" value="selected"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group">
                            <label >Item Status</label>
                            <select class="form-control" id="itemStatus" name="itemStatus">
                                <option value="1" ${isFirstStatusSelected}>PRESENT</option>
                                <option value="2" ${isSecondStatusSelected}>NOT_PRESENT</option>
                            </select>
                        </div>
                        <div class="modal-footer ">
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <form action="Controller?command=updateitem" method="POST">
                                <input type="hidden" name="item_id" value="${itemForEdit.id}"> </input>
                            <button type="submit" class="btn btn-warning btn-lg"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
                                </form>
                            <form action="Controller?command=addnewitem" method="POST">
                                <input type="hidden" name="item_id" value="${itemForEdit.id}"> </input>
                            <button type="submit" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-ok-sign"></span>Add New</button>
                                </form>
                        </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
<%--/Item--%>


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
<script src="../../view.components/js/for_slider_products.js"></script>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>
