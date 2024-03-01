package servlet;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet("/auth")
public class SigninServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = DbManager.auth(username, password);

        HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);
        boolean authenticated = user != null;
        if (authenticated) {
            resp.sendRedirect("/blogs");
            return;
        }
        resp.sendRedirect("/auth?status="+authenticated);
    }
}
