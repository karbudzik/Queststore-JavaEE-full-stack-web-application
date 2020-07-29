package controller.users;

import DAO.UserDAO;
import DAO.UserJDBCDAO;
import model.CMSUser;
import exception.ReadException;
import validation.ValidationHelper;
import validation.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserUpdateController", urlPatterns = "/user/edit")
public class UserUpdateController extends HttpServlet {
    private UserDAO dao;
    private CMSUser userToEdit;
    private Integer id;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dao = new UserJDBCDAO("src/main/resources/database.properties");
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidationHelper validationHelper = new ValidationHelper(request, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("person-name", "name_validation_message", 3, 25)
                .helpValidateEmail("person-mail", "email_validation_message", 3, 25)
                .helpValidateStringLength("person-city", "city_validation_message", 3, 25)
                .complete();

        if (isInputValid) {
            String name = request.getParameter("person-name");
            String email = request.getParameter("person-mail");
            String city = request.getParameter("person-city");

            userToEdit.setName(name);
            userToEdit.setEmail(email);
            userToEdit.setCity(city);

            try {
                dao.editUser(userToEdit.getID(), userToEdit);
            } catch (ReadException ex) {
                if (ex.getMessage().equals("You cannot edit this user, because a provide e-mail is exist in a database")) {
                    request.setAttribute("message", "This e-mail is exist in database. Please use different e-mail.");
                    doGet(request, response);
                } else {
                    throw new ServletException(ex);
                }
            }
            request.setAttribute("message", "The user edited successfully.");
        }
        doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateUserIdFromRequestIfExists(request, response);
        userToEdit = getUser(this.id);

        String type = userToEdit.getRole().toLowerCase();

        request.setAttribute("editAdmin", userToEdit);
        request.setAttribute("type", type);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/html-cms/users_edit.jsp");
        dispatcher.forward(request, response);
    }

    private CMSUser getUser(int idUser) throws ServletException {
        try {
            return dao.getCMSUser(idUser);
        } catch (ReadException ex) {
            throw new ServletException("You cannot edit user!" + ex);
        }
    }

    private void updateUserIdFromRequestIfExists(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        if (parameters.containsKey("id")) {
            this.id = 0;
            try {
                id = Integer.parseInt(parameters.get("id")[0]);
            } catch (NumberFormatException ex) {
                response.sendRedirect("/dashboard");
            }
        }
    }
}
