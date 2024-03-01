package servlet;

import db.DbBlog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import model.User;

import java.io.IOException;

@WebServlet("/blogs")
public class BlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/auth");
            return;
        }
        var blogs = DbBlog.getBlogs();
        req.setAttribute("blogs", blogs);
        req.getRequestDispatcher("blogs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        User author = (User) req.getSession().getAttribute("currentUser");
        Blog blog = new Blog(title, description, author);
        DbBlog.createBlog(blog);
        resp.sendRedirect("/blogs");
    }
}
