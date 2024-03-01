<%@ page import="java.util.List" %>
<%@ page import="model.Blog" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 20.02.2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="navbar.jsp" %>
    <div class="col-8 mx-auto">
        <form action="/blogs" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input name="title" type="text" class="form-control" id="title">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input name="description" type="text" class="form-control" id="description">
            </div>
            <button class="btn btn-success">+ADD BLOG</button>
        </form>
        <table class="table table-striped">
            <thead>
                <th>ID</th>
                <th>TITLE</th>
                <th>AUTHOR</th>
                <th>CREATED AT</th>
                <th>DETAILS</th>
            </thead>
            <tbody>
                <%
                    List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
                    for (Blog blog : blogs) {
                %>
                    <tr>
                        <td><%=blog.getId()%></td>
                        <td><%=blog.getTitle()%></td>
                        <td><%=blog.getAuthor().getFirstName() + " " + blog.getAuthor().getLastName()%></td>
                        <td><%=blog.getCreatedAt()%></td>
                        <td><a class="btn btn-dark" href="/blog-details?id=<%=blog.getId()%>">DETAILS</a></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
