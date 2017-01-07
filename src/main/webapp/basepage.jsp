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
    <title>Main</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="view.components/css/styles.css" rel="stylesheet">

    <style>
        .colorspan {
            background-color: #ffe; /* Цвет фона */
            color: #930; /* Цвет текста */
        }
    </style>
</head>

<body>
<%--Image--%>
<div class="jumbotron">
    <div class="container text-center">
        <h1>Online Store</h1>
        <p>Mission, Vission & Values</p>
    </div>
</div>

<%--/Image--%>

<%--Fixed header elements--%>
<div class="container">
    <div class="row">
        <div class="header">
            <ul>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-dashboard"></div>
                    <div class="row"><a href="#"></span>Dashboard</a></div>
                </li>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-user"></div>
                    <div class="row"><a href="#"></span>User</a></div>
                </li>
                <li>
                    <div class="row"><span class="glyphicon glyphicon-shopping-cart colorspan"></div>
                    <div class="row"><a href="#"></span>Cart</a></div>
                </li>
            </ul>
        </div>
    </div>
</div>

<%--Categories--%>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="panel panel-primary">
                <div class="panel-heading">BLACK FRIDAY DEAL</div>
                <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"
                                             style="width:100%" alt="Image"></div>
                <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-danger">
                <div class="panel-heading">BLACK FRIDAY DEAL</div>
                <a href="Controller?command=register">
                    <div class="panel-body"><img src="view.components/images/uV4ABsIur2s.png?text=IMAGE"
                                                 class="img-responsive" style="width:100%" alt="Image"></div>
                </a>
                <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-success">
                <div class="panel-heading">BLACK FRIDAY DEAL</div>
                <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"
                                             style="width:100%" alt="Image"></div>
                <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
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
            <h2>NEW COLLECTION</h2>
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
                            <a href="#"><img src="view.components/images/uV4ABsIur2s.png"
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

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="view.components/js/bootstrap.min.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/formain_fixed_header_elements.js"></script>
<!-- Добавляем свой скрипт -->
<script src="view.components/js/for_slider_products.js"></script>

<jsp:include page="WEB-INF/pages/footer.jsp"/>
</body>
</html>
