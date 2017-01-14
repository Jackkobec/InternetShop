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
    <title>Internet Shop main page.</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="../../view.components/css/styles.css" rel="stylesheet">


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

<%--Test image--%>
<%--<div align="center">3<img src="http://www.sunhome.ru/i/wallpapers/67/terminator-2-oboi.1920x1080.jpg" width="100%" height="100%"></div>--%>
<%--/Test image--%>
<%--Image--%>
<div class="jumbotron">
    <a href="Controller?command=gotomainpage">
    <div class="container text-left my-div">
        <h1><fmt:message key="head.big_text" bundle="${rb}"/></h1>
        <h2><fmt:message key="head.small_text" bundle="${rb}"/></h2>
    </div></a>
</div>
<%--/Image--%>

<%--<%@ include file="3-slide_header_carousel.jsp" %>--%>

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
                    <div class="row"><a href="#"></span>Cart</a></div>
                </li>
            </ul>
        </div>
    </div>
</div>
<%--/Fixed header elements--%>

<%--Categories--%>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <a href="Controller?command=gotocategory&category_id=1">
            <div class="panel panel-primary">
                <div class="panel-heading"><fmt:message key="category.for_self_defense" bundle="${rb}"/></div>
                <div class="panel-body"><img src="view.components/images/main_categories/For self-defense.png?text=IMAGE" class="img-responsive"
                                             style="width:100%" alt="Image"></div>
                <span class="badge"></span><%--Скидочный кружек--%>
                <div class="panel-footer"><fmt:message key="category.for_self_defense.description" bundle="${rb}"/></div>
            </div></a>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-danger">
                <div class="panel-heading"><fmt:message key="category.for_respect" bundle="${rb}"/></div>
                <a href="Controller?command=gotocategory&category_id=2">
                    <div class="panel-body"><img src="../../view.components/images/main_categories/For respect.png?text=IMAGE"
                                                 class="img-responsive" style="width:100%" alt="Image"></div>
                </a>
                <span class="badge"></span><%--Скидочный кружек--%>
                <div class="panel-footer"><fmt:message key="category.for_respect.description" bundle="${rb}"/></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-success">
                <div class="panel-heading"><fmt:message key="category.ultimate_solutions" bundle="${rb}"/></div>
                <div class="panel-body"><img src="view.components/images/main_categories/Ultimate Solutions.png?text=IMAGE" class="img-responsive"
                                             style="width:100%" alt="Image"></div>
                <span class="badge">-17%</span><%--Скидочный кружек--%>
                <div class="panel-footer"><fmt:message key="category.ultimate_solutions.description" bundle="${rb}"/></div>
            </div>
        </div>
    </div>
</div>
<%--<br>--%>

<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<div class="col-sm-4">--%>
<%--<div class="panel panel-primary">--%>
<%--<div class="panel-heading">BLACK FRIDAY DEAL</div>--%>
<%--<div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"--%>
<%--style="width:100%" alt="Image"></div>--%>
<%--<div class="panel-footer">Buy 50 mobiles and get a gift card</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-sm-4">--%>
<%--<div class="panel panel-primary">--%>
<%--<div class="panel-heading">BLACK FRIDAY DEAL</div>--%>
<%--<div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"--%>
<%--style="width:100%" alt="Image"></div>--%>
<%--<div class="panel-footer">Buy 50 mobiles and get a gift card</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-sm-4">--%>
<%--<div class="panel panel-primary">--%>
<%--<div class="panel-heading">BLACK FRIDAY DEAL</div>--%>
<%--<div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"--%>
<%--style="width:100%" alt="Image"></div>--%>
<%--<div class="panel-footer">Buy 50 mobiles and get a gift card</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<br><br>--%>
<%--/Categories--%>


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


                    <%--<div class="item">--%>
                        <%--<div class="col-xs-12 col-sm-6 col-md-2">--%>
                            <%--<a href="#"><img src="https://s12.postimg.org/41uq0fc4d/item_2_180x200.png"--%>
                                             <%--class="img-responsive center-block"></a>--%>
                            <%--<h4 class="text-center">MAYORAL KOŠULJA</h4>--%>
                            <%--<h5 class="text-center">4000,00 RSD</h5>--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <%--<div class="item">--%>
                        <%--<div class="col-xs-12 col-sm-6 col-md-2">--%>
                            <%--<a href="#"><img src="../../view.components/images/uV4ABsIur2s.png"--%>
                                             <%--class="img-responsive center-block"></a>--%>
                            <%--<span class="badge">10%</span>--%>
                            <%--<h4 class="text-center">PANTALONE TERI 2</h4>--%>
                            <%--<h5 class="text-center">4000,00 RSD</h5>--%>
                            <%--<h6 class="text-center">5000,00 RSD</h6>--%>
                        <%--</div>--%>
                    <%--</div>--%>







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
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../view.components/js/bootstrap.min.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/formain_fixed_header_elements.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/for_slider_products.js"></script>

<jsp:include page="footer.jsp"/>
</body>
</html>
