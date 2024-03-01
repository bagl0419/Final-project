package servlet;

import db.DbComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

@WebServlet("/comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        Long blogId = Long.parseLong(req.getParameter("blogId"));

        User user = (User) req.getSession().getAttribute("currentUser");
        DbComment.addComment(value, user.getId(), blogId);
        resp.sendRedirect("/blog-details?id=" + blogId);
    }
}
