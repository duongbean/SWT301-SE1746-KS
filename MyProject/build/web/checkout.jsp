<%-- 
    Document   : index
    Created on : Jun 28, 2023, 2:07:20 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="model.DAOProducts,model.DAOOrders,entity.Orders,entity.Products" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Check out | Samsung Store</title>
        <link rel="icon" type="image/x-icon" href="icon/favicon.png">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <style>
            .product-image {
                max-width: 100px; /* Giảm kích thước tối đa của ảnh */
                max-height: 100px;
            }
        </style>

    </head>
    <!-- body -->
    <body class="main-layout">
        <!-- loader  -->
        <div class="loader_bg">
            <div class="loader"><img src="images/loading.gif" alt="#" /></div>
        </div>
        <!-- content-->
        <div class="wrapper">
            <!-- end loader -->
            <div id="content">
                <!-- header -->
                <header>
                    <!-- header inner -->
                    <div class="head_top">
                        <!-- end header inner -->
                        <jsp:include page="banner.jsp"></jsp:include>
                        </div>
                    </header>
                    <br/>
                    <br/>
                <%
                    ResultSet rs=(ResultSet)request.getAttribute("rsod");
                    DAOProducts daopr = new DAOProducts();
                    DAOOrders daoo = new DAOOrders();
                    double finaltotal = 0.0;
                    int oid = 0;
                    Timestamp OrderDate = null;
                    String ReceiverName = "";
                    String ReceiverPhone = "";
                    String ReceiverAddress = "";
                %>
                <div class="container">
                    <div id="showcart"  class="brand-bg">
                        <h3>Order Detail</h3>
                        <h3 style="color: #1428A0;">You have successfully ordered!</h3>
                        <table width="100%">
                            <tr>
                                <td><h2><strong>Image</strong></h2></td>
                                <td><h2><strong>Product Name</strong></h2></td>
                                <td><h2><strong>Unit Price</strong></h2></td>
                                <td><h2><strong>Number of Product</strong></h2></td>
                                <td><h2><strong>Total</strong></h2></td>
                            </tr>
                            <%while (rs.next()) {
                                Orders o = daoo.searchbyid(rs.getInt("OrderID"));
                                Products p = daopr.searchbyid(rs.getInt("ProductID"));
                                finaltotal=o.getTotal();
                                oid=o.getOrderID();
                                OrderDate=o.getOrderDate();
                                ReceiverName=o.getReceiverName();
                                ReceiverPhone=o.getReceiverPhone();
                                ReceiverAddress=o.getReceiverAddress();
                            %>
                            <tr>
                                <td><img src="<%= p.getImageURL() %>"class="product-image"/></td>
                                <td><%= p.getProductName() %></td>
                                <td><%= p.getPrice() %></td>
                                <td><%= rs.getInt("Quantity") %></td>
                                <td>$<%= p.getPrice()*((int)rs.getInt("Quantity"))%></td>
                            </tr>
                            <% } %>
                        </table>
                        <br/>
                        <h2><strong>Grand Total: $<%=finaltotal%></strong></h2>
                        <br/>
                            <h4>---Purchase order information---</h4>
                            <h4>Samsung Electronics Vietnam Thai Nguyen Co., Ltd - Ho Chi Minh City Branch</h4>
                            <h4>Address: Bitexco Building, No. 2 Hai Trieu Street, Ben Nghe Ward, District 1, Ho Chi Minh City</h4>
                            <h4>Invoice number: <%=oid%></h4>
                            <h4>Custormer: ${sessionScope.account.username.toUpperCase()}</h4>
                            <h4>Purchase date: <%=OrderDate%></h4>
                            <h4>Receiver's name: <%=ReceiverName%></h4>
                            <h4>Receiver's phone: <%=ReceiverPhone%></h4>
                            <h4>Receiver's address: <%=ReceiverAddress%></h4>
                            <h4>---------------------------------</h4>
                        <h5>For any questions, please contact 1800-588-886 and press 6 to meet Customer Care.</h5>
                        <a class="buynow" href="main">NEW SHOPPING</a>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
        <div class="overlay"></div>

        <!-- Javascript files-->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.0.0.min.js"></script>
        <!-- sidebar -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/custom.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });
                $('#dismiss, .overlay').on('click', function () {
                    $('#sidebar').removeClass('active');
                    $('.overlay').removeClass('active');
                });
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').addClass('active');
                    $('.overlay').addClass('active');
                    $('.collapse.in').toggleClass('in');
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $(".fancybox").fancybox({
                    openEffect: "none",
                    closeEffect: "none"
                });
                $(".zoom").hover(function () {
                    $(this).addClass('transition');
                }, function () {
                    $(this).removeClass('transition');
                });
            });
        </script>
        <script>
            // This example adds a marker to indicate the position of Bondi Beach in Sydney,
            // Australia.
            function initMap() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 11,
                    center: {
                        lat: 40.645037,
                        lng: -73.880224
                    },
                });
                var image = 'images/maps-and-flags.png';
                var beachMarker = new google.maps.Marker({
                    position: {
                        lat: 40.645037,
                        lng: -73.880224
                    },
                    map: map,
                    icon: image
                });
            }
        </script>
        <!-- google map js -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8eaHt9Dh5H57Zh0xVTqxVdBFCvFMqFjQ&callback=initMap"></script>
        <!-- end google map js -->
    </body>
</html>