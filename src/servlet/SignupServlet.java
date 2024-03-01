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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthCountry = req.getParameter("birthCountry");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        User user = new User(firstName, lastName, birthCountry, age, username, password);
        String result = DbManager.createUser(user, rePassword);
        HttpSession session = req.getSession();
        session.setAttribute("result", result);
        resp.sendRedirect("/signup");
    }
}
