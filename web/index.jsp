<%-- 
    Document   : index
    Created on : Jun 28, 2023, 2:07:20 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Samsung Store</title>
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
                <jsp:include page="menu.jsp"></jsp:include>
                <jsp:include page="content.jsp"></jsp:include>
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