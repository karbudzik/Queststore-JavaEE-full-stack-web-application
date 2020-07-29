package controller.levels;


import DAO.LevelDAO;
import DAO.LevelJDBCDAO;
import exception.ReadException;
import model.Level;
import service.LevelService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Levels", urlPatterns = "/levels")
public class LevelListController extends HttpServlet {
    private LevelService service;

    @Override
    public void init() throws ServletException {
        super.init();
        LevelDAO levelDAO = new LevelJDBCDAO();
        this.service = new LevelService(levelDAO);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        Boolean order = getOrder(parameters);
        String sortBy = getSortBy(parameters);
        try {
            List<Level> levelsList = service.getAllLevels(sortBy, order);
            req.setAttribute("levelsList", levelsList);
            RequestDispatcher dispatcher
                    = req.getRequestDispatcher("/html-cms/levels_list.jsp");
            dispatcher.forward(req, resp);
        } catch (ReadException e) {
            req.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/error_page.jsp");
            dispatcher.forward(req, resp);

        }
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

