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
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

@WebServlet(name = "UserNewController", urlPatterns = "/user/new")
public class UserNewController extends HttpServlet {
    private UserDAO dao;
    private boolean isAdmin;
    private String type;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


            String defaultPassword = "admin";
            String defaultPicture = "../assets/img/admins-images/penelope-cruz.svg";
            Date actualDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            CMSUser newAdmin = new CMSUser.
                    Builder().
                    userName(name).
                    userEmail(email).
                    userCity(city).
                    userPassword(defaultPassword).
                    userDate(actualDate).
                    userPicture(defaultPicture).
                    userRole(isAdmin).
                    build();
            try {
                dao.addUser(newAdmin);
            } catch (ReadException ex) {
                if (ex.getMessage().equals("You cannot add a new user, because this e-mail is exist in a database")) {
                    request.setAttribute("message", "This e-mail is exist in database. Please use different e-mail.");
                    doGet(request, response);
                } else {
                    throw new ServletException(ex);
                }
            }
            request.setAttribute("message", "New user has been add.");
        }
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateUserRoleFromRequestIfExists(request);
        request.setAttribute("type", type);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/html-cms/users_add_new.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUserRoleFromRequestIfExists(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        if (parameters.containsKey("type")) {
            this.type = parameters.get("type")[0];
            this.isAdmin = type.equals("admin");
        }
    }
}
