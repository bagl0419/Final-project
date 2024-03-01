package servlet;

import db.DbBlog;
import db.DbComment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import model.Comment;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/blog-details")
public class BlogDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/auth");
            return;
        }
        Long id = Long.parseLong(req.getParameter("id"));
        Blog blog = DbBlog.getBlogById(id);
        req.setAttribute("blog", blog);

        List<Comment> comments = DbComment.getCommentsByBlogId(id);
        req.setAttribute("commenty", comments);
        req.getRequestDispatcher("blogDetails.jsp").forward(req, resp);
    }
}
