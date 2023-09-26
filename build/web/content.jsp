<%-- 
    Document   : content
    Created on : Jul 11, 2023, 12:16:45 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.DAOUsers,entity.Users" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! int count=10, ctr=20;%>
        <% Users u=(Users) session.getAttribute("account"); %>
        <div class="container">
            <div id="mobiletablet"  class="brand-bg">
                <c:set var="count" value="0" />
                <c:forEach items="${requestScope.datapro}" var="p">
                    <c:if test="${p.getQuantity() > 0}">
                        <c:if test="${count % 4 == 0}">
                            <div class="row">
                            </c:if>
                            <div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 margintop">
                                <div class="brand-box">
                                    <i><img src="${p.getImageURL()}"/></i>
                                    <h4>${p.getProductName()}</h4>
                                    <br/>
                                    <h4>$${p.getPrice()}</h4>
                                </div>
                                <% if (session.getAttribute("account") == null) { %>
                                <a class="buynow" href="account">Buy now</a>
                                <% } else {
                                    if (u.getRole()==1) { %>
                                        <a class="buynow" href="cart?service=addCart&id=${p.getProductID()}">Buy now</a>
                                    <% } else { %> 
                                    <% } 
                                } %>
                            </div>
                            <c:if test="${count % 4 == 3}">
                            </div>
                            <br/>
                        </c:if>
                        <c:set var="count" value="${count + 1}" />
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <section>
            <!--  save -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="save">
                            <div class="row">
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                                    <div id="samsunglive" class="save_box">
                                        <h3>save up to 50%</h3>
                                        <% if (session.getAttribute("account") == null) { %>
                                        <a href="account">Buy now</a>
                                        <% } else { %>
                                        <a href="main">Buy now</a>
                                        <% } %> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <br/>
            <!-- end save -->
        </section>
    </body>
</html>
