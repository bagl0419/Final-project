<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 17.02.2024
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="navbar.jsp" %>
<div class="col-6 mx-auto">
    <%
        String status = request.getParameter("status");
        if (status != null) {
            Boolean authenticated = Boolean.valueOf(status);
            if (Boolean.FALSE.equals(authenticated)) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Incorrect username or password! Try again.</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            }
        }
    %>
    <form action="/auth" method="post">
        <div class="mb-3">
            <label for="usernameField" class="form-label">Username</label>
            <input name="username" type="text" class="form-control" id="usernameField">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <button type="submit" class="btn btn-primary">SIGN IN</button>
    </form>
</div>
</body>
</html>
