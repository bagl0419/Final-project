<%--
  Created by IntelliJ IDEA.
  User: bagl0
  Date: 01.03.2024
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="#">Bitlab</a>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Блог</a>
                </li>
            </ul>
            <div class="d-flex gap-2">
                <a href="/auth" class="btn btn-success">Войти</a>
                <button class="btn btn-outline-secondary" onclick="logout()">Выйти</button>
            </div>
        </div>
    </div>
</nav>

<script>
    const logout = async () => {
        await fetch('/logout').then(() => {
            window.location.href='/';
        })
    }
</script>
