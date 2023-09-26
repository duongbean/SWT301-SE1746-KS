<%-- 
    Document   : banner
    Created on : Jul 11, 2023, 11:09:03 AM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jakarta.servlet.http.HttpSession,model.DAOUsers,entity.Users" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 logo_section">
                        <div class="full">
                            <div class="center-desk">
                                <div class="logo">
                                    <a href="main"><img src="images/logo.png" alt="#"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="right_header_info">
                            <ul>
                                <li class="menu_iconb">
                                    <a href="main">HE170158 Tran Huy Tuan</a>
                                </li>
                                <%
                                Users u=(Users) session.getAttribute("account");
                                if (u==null){ %>
                                <li class="menu_iconb">
                                    <a href="account">Log in<img style="margin-right: 15px;" src="icon/55.png" alt="#" /> </a>
                                </li>
                                <li class="menu_iconb">
                                    <a href="account?service=register">Register<img style="margin-left: 15px;" src="icon/66.png" alt="#" /></a>
                                </li>
                                <li class="tytyu">
                                    <a href="account"> <img style="margin-right: 15px;" src="icon/2.png" alt="#" /></a>
                                </li>
                                <%} else {%>
                                <li class="menu_iconb">
                                    Hello <%=u.getContactName()%>
                                </li>
                                    <% if (u.getRole()==1) { %>
                                    <li class="tytyu">
                                        <a href="showcart.jsp"> <img style="margin-right: 15px;" src="icon/2.png" alt="#" /></a>
                                    </li>
                                    <li class="tytyu">
                                        <a href="order"> <img style="margin-right: 15px;" src="icon/7.png" alt="#" /></a>
                                    </li>
                                    <%} else {%>
                                    <li class="tytyu">
                                        <a href="product?service=insert"><img style="margin-right: 15px;" src="icon/8.png" alt="#" /></a>
                                    </li>
                                    <li class="tytyu">
                                        <a href="order"> <img style="margin-right: 15px;" src="icon/7.png" alt="#" /></a>
                                    </li>
                                    <%}%>
                                    <li class="menu_iconb">
                                        <a href="account?service=logout">Log Out</a>
                                    </li>
                                <%}%>
                                
                                <li class="menu_iconb">
                                    <a href="main?service=search"><img style="margin-right: 15px;" src="icon/3.png" alt="#" /></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Advertisement-->
        <section class="slider_section">
            <div class="banner_main">
                <div class="container-fluid padding3">
                    <div class="row">
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-12 mapimg">
                            <div class="text-bg">
                                <h1>Galaxy<br>
                                    Unpacked</h1>
                                <span>Watch the livesteam here.</span>
                                <span>July 26, 2023 at 7AM ET</span>
                                <a href="https://www.samsung.com/us/smartphones/the-next-galaxy/reserve/">Reserve now</a>
                            </div>
                        </div>
                        <div class="col-xl-8 col-lg-8 col-md-8 col-sm-12">
                            <div id="myCarousel" class="carousel slide banner_Client" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <div class="container">
                                            <div class="carousel-caption text">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="img_bg">
                                                            <figure><img src="images/gu23.jpg" /></figure>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
