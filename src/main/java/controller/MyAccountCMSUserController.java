package controller;

import DAO.UserDAO;
import DAO.UserJDBCDAO;
import model.CMSUser;
import exception.ReadException;
import exception.SessionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyAccountCMSUSer", urlPatterns = "/cms-user/my-account")
public class MyAccountCMSUserController extends HttpServlet {
    private UserDAO dao;
    private CMSUser userToEdit;
    private String oldPassword;
    private boolean firstStart;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dao = new UserJDBCDAO("src/main/resources/database.properties");
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
        this.firstStart = true;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("person-name");
        String email = request.getParameter("person-mail");
        this.oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");

        if ("personal-information".equals(action)) {
            if (name == null || email == null) {
                response.sendRedirect("/dashboard");
            }

            userToEdit.setName(name);
            userToEdit.setEmail(email);
            try {
                dao.editUser(userToEdit.getID(), userToEdit);
            } catch (ReadException ex) {
                throw new ServletException(ex);
            }
            response.sendRedirect("/dashboard");

        } else if ("change-password".equals(action)) {
            if (oldPassword == null || newPassword == null) {
                response.sendRedirect("/dashboard");
            }

            int userId = userToEdit.getID();
            if (checkIfCorrectPassword(request)) {
                try {
                    dao.changeUserPassword(userId, newPassword);
                } catch (ReadException e) {
                    e.printStackTrace();
                }
            }
        }
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserToEditFromRequest(request);
        if(this.firstStart){
            request.setAttribute("invalidOldPassword", " hidden");
        }
        firstStart = false;

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/html-login-and-account/my-account.jsp");
        dispatcher.forward(request, response);
    }

    private boolean checkIfCorrectPassword(HttpServletRequest request) {
        if(userToEdit.getPassword().equals(oldPassword)){
            request.setAttribute("invalidOldPassword", " hidden");
            return true;
        }else{
            request.setAttribute("invalidOldPassword", "");
            return false;
        }
    }

    private void setUserToEditFromRequest(HttpServletRequest request){
        if(userToEdit == null){
            HttpSession session = request.getSession();
            userToEdit = (CMSUser) session.getAttribute("user");
            request.setAttribute("userToEdit", userToEdit);
        }
    }

}
