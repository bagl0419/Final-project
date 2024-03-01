<%--
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
        String result = (String) session.getAttribute("result");
        if (result != null) {
            if (result.equals("errorUsername")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Username is busy!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else if (result.equals("errorPasswords")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Passwords are not same!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Account created is successfully!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            }
        }
    %>
    <form action="/signup" method="post">
        <div class="mb-3">
            <label for="usernameField" class="form-label">Username</label>
            <input name="username" type="text" class="form-control" id="usernameField">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword2" class="form-label">Repeat Password</label>
            <input name="rePassword" type="password" class="form-control" id="exampleInputPassword2">
        </div>
        <div class="mb-3">
            <label for="firstNameField" class="form-label">First name</label>
            <input name="firstName" type="text" class="form-control" id="firstNameField">
        </div>
        <div class="mb-3">
            <label for="lastNameField" class="form-label">Last name</label>
            <input name="lastName" type="text" class="form-control" id="lastNameField">
        </div>
        <div class="mb-3">
            <label for="birthCountryField" class="form-label">Birth country</label>
            <input name="birthCountry" type="text" class="form-control" id="birthCountryField">
        </div>
        <div class="mb-3">
            <label for="ageField" class="form-label">Age</label>
            <input name="age" type="number" class="form-control" id="ageField">
        </div>
        <button type="submit" class="btn btn-primary">CREATE ACCOUNT</button>
    </form>
</div>
</body>
</html>
