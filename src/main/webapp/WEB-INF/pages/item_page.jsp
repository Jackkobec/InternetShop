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
