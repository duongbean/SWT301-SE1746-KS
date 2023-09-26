<%-- 
    Document   : login
    Created on : Jun 27, 2023, 3:49:25 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>  
<html lang="en" >  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Sign in | Samsung Account</title>  
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
            <h1 class="text-center">Sign in to your Samsung account</h1>  
            <div class="container">  
                <div class="row">  
                    <div class="col-md-5 mx-auto">  
                        <div class="card card-body">  
                            <form id="submitForm" action="account" method="post" data-parsley-validate="" data-parsley-errors-messages-disabled="true" novalidate="" _lpchecked="1">  
                                <input type="hidden" name="_csrf" value="7635eb83-1f95-4b32-8788-abec2724a9a4">  
                                <div class="form-group required">  
                                    <lSabel for="username"> Enter your Username </lSabel>  
                                    <input type="text" class="form-control text-lowercase" id="username" required="" name="username" value="">  
                                </div>
                                <div class="form-group required">  
                                    <lSabel for="username"> Enter your Password </lSabel>  
                                    <input type="password" class="form-control" id="password" required="" name="password" value="">  
                                </div>
                                <div class="form-group pt-1">  
                                    <input class="btn btn-primary btn-block" type ="submit" name ="submitlogin" value = "Log in">
                                </div>  
                            </form>  
                            <lSabel style="color: red">${requestScope.error}</lSabel>
                            <lSabel style="color: green">${requestScope.msg}</lSabel>
                            <p class="small-xl pt-3 text-center">  
                                <span class="text-muted"> Not a member? </span>  
                                <a href="register.jsp"> Register </a>  
                            </p>  
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div>  
    </body>  
</html>  
