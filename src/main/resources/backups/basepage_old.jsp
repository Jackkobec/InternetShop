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
</head>

<body>

<%--Navibar--%>
<%--<div class="menu">--%>
<%--<div class="container-fluid">--%>
<%--<div class="navbar-header">--%>
<%--<a href="#">Internet Shop</a>--%>
<%--</div>--%>
<%--<div>--%>
<%--<ul class="nav navbar-nav navbar-right">--%>
<%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
<%--<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--/Navibar--%>


<%--Test Navibar2--%>
<%--<div id="navbar-main">--%>
    <%--<!-- Fixed navbar -->--%>
    <%--<div class="navbar navbar-inverse navbar-top">--%>
        <%--<div class="container">--%>
            <%--<div class="navbar-header">--%>
                <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">--%>
                    <%--<span class="icon icon-shield" style="font-size:30px; color:#3498db;"></span>--%>
                <%--</button>--%>
                <%--<a class="navbar-brand hidden-xs hidden-sm" href="#home"><span class="icon icon-shield" style="font-size:18px; color:#3498db;"></span></a>--%>
            <%--</div>--%>
            <%--<div class="navbar-collapse collapse">--%>
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li><a href="#home" class="smoothScroll">Home</a></li>--%>
                    <%--<li class="active"> <a href="#about" class="smoothScroll"> About</a></li>--%>
                    <%--<li class=""> <a href="#services" class="smoothScroll"> Services</a></li>--%>
                    <%--<li class=""> <a href="#team" class="smoothScroll"> Team</a></li>--%>
                    <%--<li class=""> <a href="#portfolio" class="smoothScroll"> Portfolio</a></li>--%>
                    <%--<li class=""> <a href="#blog" class="smoothScroll"> Blog</a></li>--%>
                    <%--<li class=""> <a href="#contact" class="smoothScroll"> Contact</a></li>--%>
                <%--</ul></div><!--/.nav-collapse -->--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--/Test Navibar2--%>

<%--Fixed header elements--%>
<div class="container">
    <label class="control-label">Welcome Mr. Nuon Veyo</label>
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
                    <div class="row"><span class="glyphicon glyphicon-cog"></div>
                    <div class="row"><a href="#"></span>Setting</a></div>
                </li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12" id="right-content">
            Subtitle: Content value<br/>


        </div>
    </div>
</div>
<%--/Fixed header elements--%>

<%--Menu--%>
<div class="container">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span
                                    class="glyphicon glyphicon-folder-close">
                </span>Content</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <ul class="list-group">
                            <li class="list-group-item"><span
                                    class="glyphicon glyphicon-pencil text-primary"></span><a
                                    href="http://fb.com/moinakbarali">Articles</a></li>

                            <li class="list-group-item"><span
                                    class="glyphicon glyphicon-flash text-success"></span><a
                                    href="http://fb.com/moinakbarali">News</a></li>

                            <li class="list-group-item"><span
                                    class="glyphicon glyphicon-file text-info"></span><a
                                    href="http://fb.com/moinakbarali">Newsletters</a></li>

                            <li class="list-group-item"><span
                                    class="glyphicon glyphicon-comment text-success"></span><a
                                    href="http://fb.com/moinakbarali">Comments</a><span
                                    class="badge">42</span></li>

                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><span
                                    class="glyphicon glyphicon-file">
                </span>Reports</a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="list-group">
                            <a href="#" class="list-group-item active">
                                Cras justo odio
                            </a>
                            <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                            <a href="#" class="list-group-item">Morbi leo risus</a>
                            <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                            <a href="#" class="list-group-item">Vestibulum at eros</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive"><span
                                    class="glyphicon glyphicon-heart">
                </span>Reports</a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <p class="list-group-item-text">Donec id elit non mi porta gravida at eget
                                    metus. Maecenas sed diam eget risus varius blandit.</p>
                            </a>
                        </div>
                        <div class="list-group">
                            <a href="#" class="list-group-item active">
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <p class="list-group-item-text">Donec id elit non mi porta gravida at eget
                                    metus. Maecenas sed diam eget risus varius blandit.</p>
                            </a>
                        </div>
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <h4 class="list-group-item-heading">List group item heading</h4>
                                <p class="list-group-item-text">Donec id elit non mi porta gravida at eget
                                    metus. Maecenas sed diam eget risus varius blandit.</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Dashboard</h3>
                </div>
                <div class="panel-body">
                    Admin Dashboard Accordion List Group Menu
                </div>
            </div>
        </div>
    </div>
</div>
<%--/Menu--%>

<%--Categoties--%>
<div class="container">
    <div class="row category-child" style="margin-top:20px">
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://kudo.co.id/wp-pictures/product/example/woven_che_white.jpg" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Kebutuhan Ibu dan Anak</span>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://image.kudo.co.id/wp-content/uploads/2015/10/fashion.png" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Produk Pilihan</span>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://image.kudo.co.id/wp-content/uploads/2015/10/hijab.png" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Produk Pilihan</span>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://image.kudo.co.id/wp-content/uploads/2015/10/makanandaerah.png" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Produk Pilihan</span>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://image.kudo.co.id/wp-content/uploads/2015/10/tiket.png" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Produk Pilihan</span>
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-2 col-md-4 col-xs-6 thumb ">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://image.kudo.co.id/wp-content/uploads/2015/10/holiday.png" alt="">
                <div class="wrapper">
                    <div class="caption post-content">
                        <span>Produk Pilihan</span>
                    </div>
                </div>
            </a>
        </div>
    </div>

</div>
<%--/Categories--%>

<%--Slider Products--%>

<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,400,700&subset=latin-ext" rel="stylesheet">

<!--Item slider text-->
<div class="container">
    <div class="row" id="slider-text">
        <div class="col-md-6" >
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
                            <a href="#"><img src="https://s12.postimg.org/655583bx9/item_1_180x200.png" class="img-responsive center-block"></a>
                            <h4 class="text-center">MAYORAL SUKNJA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/41uq0fc4d/item_2_180x200.png" class="img-responsive center-block"></a>
                            <h4 class="text-center">MAYORAL KOŠULJA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/dawwajl0d/item_3_180x200.png" class="img-responsive center-block"></a>
                            <span class="badge">10%</span>
                            <h4 class="text-center">PANTALONE TERI 2</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                            <h6 class="text-center">5000,00 RSD</h6>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/5w7ki5z4t/item_4_180x200.png" class="img-responsive center-block"></a>
                            <h4 class="text-center">CVETNA HALJINA</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/e2zk9qp7h/item_5_180x200.png" class="img-responsive center-block"></a>
                            <h4 class="text-center">MAJICA FOTO</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                    <div class="item">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <a href="#"><img src="https://s12.postimg.org/46yha3jfh/item_6_180x200.png" class="img-responsive center-block"></a>
                            <h4 class="text-center">MAJICA MAYORAL</h4>
                            <h5 class="text-center">4000,00 RSD</h5>
                        </div>
                    </div>

                </div>

                <div id="slider-control">
                    <a class="left carousel-control" href="#itemslider" data-slide="prev"><img src="https://s12.postimg.org/uj3ffq90d/arrow_left.png" alt="Left" class="img-responsive"></a>
                    <a class="right carousel-control" href="#itemslider" data-slide="next"><img src="https://s12.postimg.org/djuh0gxst/arrow_right.png" alt="Right" class="img-responsive"></a>
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
</body>
</html>
