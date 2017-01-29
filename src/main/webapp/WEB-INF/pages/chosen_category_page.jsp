<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 12.01.2017
  Time: 4:13
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
    <title>Category Page</title>

    <!-- Добавляем свой стиль -->
    <link type="text/css" href="view.components/css/styles.css" rel="stylesheet">
    <!-- Добавляем свой стиль -->
    <link type="text/css" href="view.components/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%--Image--%>
<div class="jumbotron">
    <div class="container text-left my-div">
        <h1><fmt:message key="head.big_text" bundle="${rb}"/></h1>
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
                    <div class="row"><a href="#"></span>Cart</a></div>
                </li>
            </ul>
        </div>
    </div>
</div>
<%--/Fixed header elements--%>

<%--Items of this category. 2 carousels.--%>
<div class="container">
    <div class="row">
        <div class="row">
            <div class="col-md-9">
                <h3>
                    <fmt:message key="first_carousel_title" bundle="${rb}"/> <fmt:message key="${currentCategoryName}" bundle="${rb}"/></h3>
            </div>
            <div class="col-md-3">
                <!-- Controls -->
                <div class="controls pull-right hidden-xs">
                    <a class="left fa fa-chevron-left btn btn-success" href="#carousel-example"
                       data-slide="prev"></a><a class="right fa fa-chevron-right btn btn-success" href="#carousel-example"
                                                data-slide="next"></a>
                </div>
            </div>
        </div>
        <div id="carousel-example" class="carousel slide hidden-xs" data-ride="carousel">
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active"><%--item active--%>
                    <div class="row"><%--row of item active--%>

                        <%--foreach for 4 first active items--%>
                        <c:forEach var="item" begin="0" end="3" items="${currentCategoryItemList}">
                        <div class="col-sm-3">
                            <div class="col-item">
                                <div class="photo">
                                    <img src="${item.itemSmallPicturePath350x260}" class="img-responsive" alt="a" />
                                </div>
                                <div class="info">
                                    <div class="row">
                                        <div class="price col-md-6">
                                            <h5>
                                                    ${item.itemName}</h5>
                                            <h5 class="price-text-color">
                                                    ${item.itemPrice}</h5>
                                        </div>
                                        <div class="rating hidden-sm col-md-6">
                                                <%--<c:forEach var="ratingStar" begin="0" end="${item.itemRating}" items="${currentCategoryItemList}">--%>
                                <c:forEach var="ratingStar" begin="1" end="${item.itemRating}">
                                <i class="price-text-color fa fa-star"></i><%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                </c:forEach>
                                <c:forEach var="ratingStar" begin="${item.itemRating}" end="4">
                                    </i><i class="fa fa-star"></i></i><%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                </c:forEach>
                                    <%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                    <%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                        </div>
                                    </div>
                                    <div class="separator clear-left">
                                        <p class="btn-add">
                                            <i class="fa fa-shopping-cart"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="add_to_cart" bundle="${rb}"/></a></p>
                                        <p class="btn-details">
                                            <i class="fa fa-list"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="more_details" bundle="${rb}"/></a></p>
                                    </div>
                                    <div class="clearfix">
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                        <%--/foreach for 4 first active items--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="view.components/images/for_self_defense/Beretta92_350x260.png" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product Example</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$249.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Next Sample Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$149.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Sample Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$199.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    </div><%--/row of item active--%>
                </div><%--/item active--%>

                <div class="item">
                    <div class="row">
                        <%--foreach next 4 simple items--%>
                            <c:forEach var="item" begin="4" end="7" items="${currentCategoryItemList}">
                                <div class="col-sm-3">
                                    <div class="col-item">
                                        <div class="photo">
                                            <img src="${item.itemSmallPicturePath350x260}" class="img-responsive" alt="a" />
                                        </div>
                                        <div class="info">
                                            <div class="row">
                                                <div class="price col-md-6">
                                                    <h5>
                                                            ${item.itemName}</h5>
                                                    <h5 class="price-text-color">
                                                            ${item.itemPrice}</h5>
                                                </div>
                                                <div class="rating hidden-sm col-md-6">
                                                        <%--<c:forEach var="ratingStar" begin="0" end="${item.itemRating}" items="${currentCategoryItemList}">--%>
                                                    <c:forEach var="ratingStar" begin="1" end="${item.itemRating}">
                                                        <i class="price-text-color fa fa-star"></i><%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                    </c:forEach>
                                                    <c:forEach var="ratingStar" begin="${item.itemRating}" end="4">
                                                        </i><i class="fa fa-star"></i></i><%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                                    </c:forEach>
                                                        <%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                        <%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                                </div>
                                            </div>
                                            <div class="separator clear-left">
                                                <p class="btn-add">
                                                    <i class="fa fa-shopping-cart"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="add_to_cart" bundle="${rb}"/></a></p>
                                                <p class="btn-details">
                                                    <i class="fa fa-list"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="more_details" bundle="${rb}"/></a></p>
                                            </div>
                                            <div class="clearfix">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--/foreach next 4 simple items--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product with Variants</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$199.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Grouped Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$249.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product with Variants</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$149.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="col-sm-3">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product with Variants</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$199.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div><%--/row item item div--%>
                </div><%--/item item div--%>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="row">
            <div class="col-md-9">
                <h3>
                    <fmt:message key="second_carousel_title" bundle="${rb}"/> <fmt:message key="${currentCategoryName}" bundle="${rb}"/></h3>
            </div>
            <div class="col-md-3">
                <!-- Controls -->
                <div class="controls pull-right hidden-xs">
                    <a class="left fa fa-chevron-left btn btn-primary" href="#carousel-example-generic"
                       data-slide="prev"></a><a class="right fa fa-chevron-right btn btn-primary" href="#carousel-example-generic"
                                                data-slide="next"></a>
                </div>
            </div>
        </div>
        <div id="carousel-example-generic" class="carousel slide hidden-xs" data-ride="carousel">
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active"><%--active item 2 carousel active item start--%>
                    <div class="row"><%--active item 2 carousel row start--%>

                        <%--foreach 2 carousel first 3--%>
                        <c:forEach var="item" begin="0" end="2" items="${currentCategoryItemList}">
                            <div class="col-sm-4">
                                <div class="col-item">
                                    <div class="photo">
                                        <img src="${item.itemSmallPicturePath350x260}" class="img-responsive" alt="a" />
                                    </div>
                                    <div class="info">
                                        <div class="row">
                                            <div class="price col-md-6">
                                                <h5>
                                                        ${item.itemName}</h5>
                                                <h5 class="price-text-color">
                                                        ${item.itemPrice}</h5>
                                            </div>
                                            <div class="rating hidden-sm col-md-6">
                                                    <%--<c:forEach var="ratingStar" begin="0" end="${item.itemRating}" items="${currentCategoryItemList}">--%>
                                                <c:forEach var="ratingStar" begin="1" end="${item.itemRating}">
                                                    <i class="price-text-color fa fa-star"></i><%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                </c:forEach>
                                                <c:forEach var="ratingStar" begin="${item.itemRating}" end="4">
                                                    </i><i class="fa fa-star"></i></i><%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                                </c:forEach>
                                                    <%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                    <%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                            </div>
                                        </div>
                                        <div class="separator clear-left">
                                            <p class="btn-add">
                                                <i class="fa fa-shopping-cart"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="add_to_cart" bundle="${rb}"/></a></p>
                                            <p class="btn-details">
                                                <i class="fa fa-list"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="more_details" bundle="${rb}"/></a></p>
                                        </div>
                                        <div class="clearfix">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <%--foreach 2 carousel first 3--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Sample Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$199.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product Example</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$249.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Next Sample Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$149.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    </div> <%--/active item 2 carousel row end--%>
                </div><%--/active item 2 carousel active item end--%>

                <div class="item"><%--item 2 carousel item start--%>
                    <div class="row"><%--item 2 carousel row start--%>

                        <%--foreach 2 carousel next 3 simple item--%>
                        <c:forEach var="item" begin="3" end="5" items="${currentCategoryItemList}">
                            <div class="col-sm-4">
                                <div class="col-item">
                                    <div class="photo">
                                        <img src="${item.itemSmallPicturePath350x260}" class="img-responsive" alt="a" />
                                    </div>
                                    <div class="info">
                                        <div class="row">
                                            <div class="price col-md-6">
                                                <h5>
                                                        ${item.itemName}</h5>
                                                <h5 class="price-text-color">
                                                        ${item.itemPrice}</h5>
                                            </div>
                                            <div class="rating hidden-sm col-md-6">
                                                    <%--<c:forEach var="ratingStar" begin="0" end="${item.itemRating}" items="${currentCategoryItemList}">--%>
                                                <c:forEach var="ratingStar" begin="1" end="${item.itemRating}">
                                                    <i class="price-text-color fa fa-star"></i><%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                </c:forEach>
                                                <c:forEach var="ratingStar" begin="${item.itemRating}" end="4">
                                                    </i><i class="fa fa-star"></i></i><%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                                </c:forEach>
                                                    <%--</i><i class="price-text-color fa fa-star">  - одна звезда, цветная--%>
                                                    <%--</i><i class="fa fa-star"></i></i>   - одна звезда, не цветная--%>
                                            </div>
                                        </div>
                                        <div class="separator clear-left">
                                            <p class="btn-add">
                                                <i class="fa fa-shopping-cart"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="add_to_cart" bundle="${rb}"/></a></p>
                                            <p class="btn-details">
                                                <i class="fa fa-list"></i><a href="Controller?command=showitem&item_id=${item.id}" class="hidden-sm"><fmt:message key="more_details" bundle="${rb}"/></a></p>
                                        </div>
                                        <div class="clearfix">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <%--/foreach 2 carousel next 3 simple item--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product with Variants</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$199.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Grouped Product</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$249.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div class="col-item">--%>
                                <%--<div class="photo">--%>
                                    <%--<img src="http://placehold.it/350x260" class="img-responsive" alt="a" />--%>
                                <%--</div>--%>
                                <%--<div class="info">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="price col-md-6">--%>
                                            <%--<h5>--%>
                                                <%--Product with Variants</h5>--%>
                                            <%--<h5 class="price-text-color">--%>
                                                <%--$149.99</h5>--%>
                                        <%--</div>--%>
                                        <%--<div class="rating hidden-sm col-md-6">--%>
                                            <%--<i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="price-text-color fa fa-star"></i><i class="price-text-color fa fa-star">--%>
                                        <%--</i><i class="fa fa-star"></i>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="separator clear-left">--%>
                                        <%--<p class="btn-add">--%>
                                            <%--<i class="fa fa-shopping-cart"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">Add to cart</a></p>--%>
                                        <%--<p class="btn-details">--%>
                                            <%--<i class="fa fa-list"></i><a href="http://www.jquery2dotnet.com" class="hidden-sm">More details</a></p>--%>
                                    <%--</div>--%>
                                    <%--<div class="clearfix">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    </div><%--/item 2 carousel item row end--%>
                </div><%--/item 2 carousel item item end--%>

            </div>
        </div>
    </div>
</div>
<%--/Items of this category. 2 carousels.--%>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../../view.components/js/bootstrap.min.js"></script>
<!-- Добавляем свой скрипт -->
<script src="../../view.components/js/formain_fixed_header_elements.js"></script>

<jsp:include page="footer.jsp"/>
</body>
</html>
