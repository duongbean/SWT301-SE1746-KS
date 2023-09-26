<%-- 
    Document   : login
    Created on : Jun 27, 2023, 3:49:25 PM
    Author     : huytu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet,entity.Users" %>
<!DOCTYPE html>  
<html lang="en" >  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Register to Order | Samsung Store</title>  
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
        <%
                                    Users c = (Users) session.getAttribute("account");
                                    String contactname=null;
                                    if (c!=null) contactname=c.getContactName();
                                    ResultSet lista=(ResultSet)request.getAttribute("dataa");
        %>
        <div class="pt-5">  
            <h1 class="text-center">Register to Order of <%=contactname%></h1>  
            <div class="container">  
                <div class="row">  
                    <div class="col-md-5 mx-auto">  
                        <div class="card card-body">  
                            <form id="submitForm" action="order?service=insert" method="post" data-parsley-validate="" data-parsley-errors-messages-disabled="true" novalidate="" _lpchecked="1">  
                                <input type="hidden" name="_csrf" value="7635eb83-1f95-4b32-8788-abec2724a9a4">  
                                <div class="form-group required">  
                                    <lSabel for="username"> Select who served you </lSabel> 
                                    <br/>
                                    <select name="adminid" class="form-control">
                                        <%
                                            while(lista.next()){
                                        %>
                                        <option value="<%=lista.getInt(1)%>" ><%=lista.getString(2)%></option>
                                        <%
                                            }
                                        %>
                                    </select>  
                                </div>                      
                                <div class="form-group required">  
                                    <lSabel for="receivername"> Enter the receiver's name</lSabel>  
                                    <input type="text" class="form-control" id="receivername" required="" name="receivername" value="">  
                                </div>  
                                <div class="form-group required">  
                                    <lSabel for="receiverphone"> Enter the receiver's phone number </lSabel>  
                                    <input type="text" class="form-control" id="receiverphone" required="" name="receiverphone" value="">  
                                </div>
                                <div class="form-group required">  
                                    <lSabel for="receiveraddress"> Enter the receiver's address </lSabel>  
                                    <input type="text" class="form-control" id="receiveraddress" required="" name="receiveraddress" value="">  
                                </div>
                                <div class="form-group pt-1">  
                                    <input class="btn btn-primary btn-block" type ="submit" name ="submitorder" value = "Submit">
                                </div>  
                            </form>  
                            <lSabel style="color: red">${requestScope.error}</lSabel>
                             
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div>  
    </body>  
</html>  
