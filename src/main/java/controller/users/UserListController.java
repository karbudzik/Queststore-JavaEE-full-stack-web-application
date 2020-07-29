package controller.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import exception.ReadException;
import model.CMSUser;
import service.UserService;


@WebServlet(name = "UsersList", urlPatterns = "/user-list")
public class UserListController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        String type = parameters.get("type")[0];
        Boolean order = getOrder(parameters);
        String sortBy = getSortBy(parameters);

        List<CMSUser> allUsers;

        try {
            allUsers = userService.getAllUsers(type, sortBy, order);
        } catch (ReadException e) {
            throw new ServletException(e);
        }

        req.setAttribute("allUsers", allUsers);
        req.setAttribute("type", type);

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/html-cms/users_list.jsp");
        dispatcher.forward(req, resp);

    }

    private Boolean getOrder(Map<String, String[]> parameters) {
        if (parameters.containsKey("order")) {
            return parameters.get("order")[0].equals("ASC");
        }
        return null;
    }

    private String getSortBy(Map<String, String[]> parameters) {
        if (parameters.containsKey("sortBy")) {
            return parameters.get("sortBy")[0];
        }
        return null;
    }

}
