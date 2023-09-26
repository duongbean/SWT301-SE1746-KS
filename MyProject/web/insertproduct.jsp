<%-- 
    Document   : login
    Created on : Jun 27, 2023, 3:49:25 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet,entity.Products,entity.Categories" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>  
<html lang="en" >  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Insert a Product | Samsung Store</title>  
        <link rel="icon" type="image/x-icon" href="icon/favicon.png">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">  
    </head>  
    <style>
        body {
            background: #1428A0 !important;
            font-family: 'Muli', sans-serif;
        }
        h1 {
            color: #fff;
            padding-bottom: 2rem;
            font-weight: bold;
        }
        a {
            color: #333;
        }
        a:hover {
            color: #1428A0;
            text-decoration: none;
        }
        .form-control:focus {
            color: #000;
            background-color: #fff;
            border: 2px solid #1428A0;
            outline: 0;
            box-shadow: none;
        }
        .btn {
            background: #1428A0;
            border: #1428A0;
        }
        .btn:hover {
            background: #1428A0;
            border: #1428A0;
        }
    </style>  
    <body>  
        <div class="pt-5">  
            <h1 class="text-center">Insert a Product</h1>  
            <div class="container">  
                <div class="row">  
                    <div class="col-md-5 mx-auto">  
                        <div class="card card-body">  
                            <form id="submitForm" action="product?service=insert" method="post" data-parsley-validate="" data-parsley-errors-messages-disabled="true" novalidate="" _lpchecked="1">  
                                <input type="hidden" name="_csrf" value="7635eb83-1f95-4b32-8788-abec2724a9a4">  

                                <div class="form-group required">  
                                    <lSabel for="name"> Enter the product's name</lSabel>  
                                    <input type="text" class="form-control" id="name" required="" name="name" value="">  
                                </div> 
                                <div class="form-group required">  
                                    <lSabel for="name"> Enter the product's unit price</lSabel>  
                                    <input type="number" class="form-control" id="price" required="" name="price" value="">  
                                </div> 
                                <div class="form-group required">  
                                    <lSabel for="name"> Enter the product's quantity</lSabel>  
                                    <input type="number" class="form-control" id="quantity" required="" name="quantity" value="">  
                                </div> 
                                <div class="form-group required">  
                                    <lSabel for="name"> Enter the product's description</lSabel>  
                                    <input type="text" class="form-control" id="description" required="" name="description" value="">  
                                </div> 
                                <div class="form-group required">  
                                    <lSabel for="name"> Enter the URL of product's image</lSabel>  
                                    <input type="text" class="form-control" id="imageurl" required="" name="imageurl" value="">  
                                </div> 
                                <div class="form-group required">  
                                    <lSabel for="username"> Select the category of the product </lSabel> 
                                    <br/>
                                    <select name="cateid" class="form-control">
                                        <% 
                                            List<Categories> list=(List<Categories>) request.getAttribute("listcat");    
                                            for (Categories i: list) { %>
                                            <option value="<%=i.getCategoryID()%>"><%=i.getCategoryName()%></option>
                                        <% } %>
                                    </select>
                                </div>                      
                                <div class="form-group pt-1">  
                                    <input class="btn btn-primary btn-block" type ="submit" name ="submitinsert" value = "Submit">
                                </div>  
                            </form>  
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div>  
    </body>  
</html>  
