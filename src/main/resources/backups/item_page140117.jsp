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
                <%--<li>--%>
                <%--<div class="row"><span class="glyphicon glyphicon-dashboard"></div>--%>
                <%--<div class="row"><a href="#"></span>Dashboard</a></div>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<div class="row"><span class="glyphicon glyphicon-user"></div>--%>
                <%--<div class="row"><a href="#"></span>User</a></div>--%>
                <%--</li>--%>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-shopping-cart mycolorspan"></div>
                    <div class="row"><a data-toggle="modal" href="#myModal"></span>Cart</a></div>
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
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
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
                                <tr>
                                    <td class="col-sm-8 col-md-6">
                                        <div class="media">
                                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                            <div class="media-body">
                                                <h4 class="media-heading"><a href="#">Product name</a></h4>
                                                <h5 class="media-heading"> by <a href="#">Brand name</a></h5>
                                                <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                                            </div>
                                        </div></td>
                                    <td class="col-sm-1 col-md-1" style="text-align: center">
                                        <input class="form-control" id="exampleInputEmail1" value="3" type="email">
                                    </td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong>$4.87</strong></td>
                                    <td class="col-sm-1 col-md-1 text-center"><strong>$14.61</strong></td>
                                    <td class="col-sm-1 col-md-1">
                                        <button type="button" class="btn btn-danger">
                                            <span class="glyphicon glyphicon-remove"></span> Remove
                                        </button></td>
                                </tr>

                                <tr>
                                    <td class="col-md-6">
                                        <div class="media">
                                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                            <div class="media-body">
                                                <h4 class="media-heading"><a href="#">Product name</a></h4>
                                                <h5 class="media-heading"> by <a href="#">Brand name</a></h5>
                                                <span>Status: </span><span class="text-warning"><strong>Leaves warehouse in 2 - 3 weeks</strong></span>
                                            </div>
                                        </div></td>
                                    <td class="col-md-1" style="text-align: center">
                                        <input class="form-control" id="exampleInputEmail1" value="2" type="email">
                                    </td>
                                    <td class="col-md-1 text-center"><strong>$4.99</strong></td>
                                    <td class="col-md-1 text-center"><strong>$9.98</strong></td>
                                    <td class="col-md-1">
                                        <button type="button" class="btn btn-danger">
                                            <span class="glyphicon glyphicon-remove"></span> Remove
                                        </button></td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><h5>Subtotal</h5></td>
                                    <td class="text-right"><h5><strong>$106.94</strong></h5></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><h5>Discount</h5></td>
                                    <td class="text-right"><h5><strong>$6.94</strong></h5></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><h3>Total</h3></td>
                                    <td class="text-right"><h3><strong>$100.0</strong></h3></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <button type="button" class="btn btn-default">
                                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                        </button></td>
                                    <td>
                                        <button type="button" class="btn btn-success">
                                            Checkout <span class="glyphicon glyphicon-play"></span>
                                        </button></td>
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
                <button type="button" class="btn btn-primary">Save changes</button>
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
                    <div class="btn-group cart">
                        <a href="#" data-toggle="tooltip" title="Hooray!">
                        <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#myModalNotification">
                            Add to cart
                        </button></a>
                    </div>

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

                    <div class="item active">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/655583bx9/item_1_180x200.png"
                                             class="img-responsive center-block"></a>
                            <h4 class="text-center">MAYORAL SUKNJA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/41uq0fc4d/item_2_180x200.png"
                                             class="img-responsive center-block"></a>
                            <h4 class="text-center">MAYORAL KOŠULJA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="../../view.components/images/uV4ABsIur2s.png"
                                             class="img-responsive center-block"></a>
                            <span class="badge">10%</span>
                            <h4 class="text-center">PANTALONE TERI 2</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                            <h6 class="text-center">5000,00 RSD</h6>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/5w7ki5z4t/item_4_180x200.png"
                                             class="img-responsive center-block"></a>
                            <h4 class="text-center">CVETNA HALJINA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/e2zk9qp7h/item_5_180x200.png"
                                             class="img-responsive center-block"></a>
                            <h4 class="text-center">MAJICA FOTO</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/46yha3jfh/item_6_180x200.png"
                                             class="img-responsive center-block"></a>
                            <h4 class="text-center">MAJICA MAYORAL</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

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
