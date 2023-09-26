<%-- 
    Document   : categories
    Created on : Jul 11, 2023, 11:12:08 AM
    Author     : huytu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="Categories">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title">
                            <h2> Shop all latest offers and innovations</h2>
                            <ul class="categiri">
                                <li><a href="main?id=${0}">All</a></li>
                                <c:forEach items="${requestScope.datacat}" var="c">
                                    <li><a href="main?id=${c.getCategoryID()}">${c.getCategoryName()}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
