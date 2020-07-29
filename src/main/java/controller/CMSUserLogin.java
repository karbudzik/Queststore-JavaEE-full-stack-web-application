package controller;

import DAO.UserDAO;
import exception.ReadException;
import DAO.UserJDBCDAO;
import model.CMSUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CMSUserLogin", urlPatterns = "/CMSUserLogin")
public class CMSUserLogin extends HttpServlet {
    private UserDAO dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/html-login-and-account/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao = new UserJDBCDAO("src/main/resources/database.properties");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        invalidateOldSession(request);
        boolean isCorrectLogIn = false;
        try {
            isCorrectLogIn = dao.checkUser(email, password);
        } catch (ReadException e) {
            throw new ServletException(e);
        }
        if(isCorrectLogIn){
            try {
                CMSUser user = dao.getCMSUser(email, password);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                response.sendRedirect("/dashboard");
            } catch (ReadException e) {
                throw new ServletException(e);
            }
        }
        else{
            String message = "<p class=\"warning-incorrect-login\">You put incorrect data!<p>";
            request.setAttribute("wrongLogIn", message);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("/html-login-and-account/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void invalidateOldSession(HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
    }
}
