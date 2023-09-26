<%-- 
    Document   : index
    Created on : Jun 28, 2023, 2:07:20 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="jakarta.servlet.http.HttpSession,model.DAOProducts,entity.Products" %>
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
        <title>Show cart | Samsung Store</title>
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
                    <div class="container">
                        <div id="showcart"  class="brand-bg">
                            <h3>Shopping Cart Detail</h3>
                        <%
                            int count = 0;
                            double grandtotal = 0;
                            java.util.Enumeration em = session.getAttributeNames();
                            DAOProducts daopro=new DAOProducts();
                            ArrayList<Products> listpro = new ArrayList<>();
                            while (em.hasMoreElements()) {
                                String raw_id = em.nextElement().toString();
                                if (!raw_id.equals("account")) {
                                    int id = Integer.parseInt(raw_id);
                                    Products pro = daopro.searchbyid(id);
                                    listpro.add(pro);
                                    count += 1;
                                    grandtotal = grandtotal + pro.getPrice()*((int)session.getAttribute(raw_id));
                                }
                            }
                            String finaltotal = String.format("%.2f", grandtotal);
                        %>
                        <% if (count > 0) { %>
                        <table width="100%">
                            <tr>
                                <td><h2><strong>Image</strong></h2></td>
                                <td><h2><strong>Product Name</strong></h2></td>
                                <td><h2><strong>Unit Price</strong></h2></td>
                                <td><h2><strong>Number of Product</strong></h2></td>
                                <td><h2><strong>Total</strong></h2></td>
                                <td></td>
                            </tr>
                            <% for (Products i : listpro) { %>
                            <tr>
                                <td><img src="<%= i.getImageURL() %>"class="product-image"/></td>
                                <td><%= i.getProductName() %></td>
                                <td><%= i.getPrice() %></td>
                                <td><input form="quanForm" type="number" min="1" value="<%=session.getAttribute(String.valueOf(i.getProductID()))%>" name="pro<%=i.getProductID()%>"/></td>
                                <td>$<%= i.getPrice()*((int)session.getAttribute(String.valueOf(i.getProductID())))%></td>
                                <td><strong><a href="cart?service=remove&id=<%=i.getProductID()%>">Remove</a></strong></td>
                            </tr>
                            <% } %>
                            <tr>
                                <td><a href="javascript:void(0);" onclick="document.getElementById('quanForm').submit();">Update Cart</a></td>
                                <td></td>
                                <td></td>
                                <td><strong><a href="cart?service=remove&id=0&quantity=1">Remove products whose quantity is one</a></strong></td>
                                <td><strong><a href="cart?service=remove">Remove All</a></strong></td>
                                <td></td>
                            </tr>
                        </table>
                        <br/>
                        <h2><strong>Grand Total: $<%=finaltotal%></strong></h2>
                        <br/>
                        <h1><strong><a href="order?service=insert">Check-out</a></strong></h1>
                        <% } else { %>
                        <h5>You haven't bought anything yet.</h5>
                        <% } %>
                        <a class="buynow" href="main">NEW SHOPPING</a>
                    </div>
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