<%@ page import="java.util.List" %>
<%@ page import="model.Blog" %>
<%@ page import="model.Comment" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 20.02.2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jsp" %>

<body>
<%@include file="navbar.jsp" %>
<%
    Blog blog = (Blog) request.getAttribute("blog");
    if (blog != null) {
%>
<div class="col-8 mx-auto">
    <div class="d-flex position-relative">
        <div>
            <h3 class="mt-0"><%=blog.getTitle()%>
            </h3>
            <p><%=blog.getDescription()%>
            </p>
            <p>posted by <%=blog.getAuthor().getFirstName() + " " +
                    blog.getAuthor().getLastName() + " at " +
                    blog.getCreatedAt()%>
            </p>
        </div>
    </div>
    <hr>
    <form action="/comment" method="post">
        <input type="hidden" value="<%=blog.getId()%>" name="blogId">
        <textarea name="value" placeholder="Insert comment..." class="form-control"></textarea>
        <button type="submit" class="btn btn-primary">+ ADD COMMENT</button>
    </form>

    <%
        List<Comment> comments = (List<Comment>) request.getAttribute("commenty");
        for (Comment comment : comments) {
    %>
    <h4><%=comment.getAuthor().getFirstName() + " " + comment.getAuthor().getLastName()%>
    </h4>
    <p><%=comment.getValue()%>
    </p>
    <p><%=comment.getCreatedAt()%>
    </p>
    <%
        }
    %>
</div>
<%
    }
%>
</body>
</html>
