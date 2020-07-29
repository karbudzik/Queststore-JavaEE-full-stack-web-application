package controller.users;

import DAO.UserDAO;
import DAO.UserJDBCDAO;
import exception.ReadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserDeleteController", urlPatterns = "/user/delete")
public class UserDeleteController extends HttpServlet {
    private UserDAO dao;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        String id = parameters.get("id")[0];
        String type = parameters.get("type")[0];
        int idUser = 0;
        try {
            idUser = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            //throw new ServletException("You put incorrect path to page!");
            response.sendRedirect("/dashboard");
        }
        try {
            dao.deleteUser(idUser);
        } catch (ReadException ex) {
            throw new ServletException("You cannot delete this user");
        }
        response.sendRedirect("/user-list?type=" + type);
    }
}
