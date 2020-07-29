package controller.levels;

import DAO.LevelDAO;
import DAO.LevelJDBCDAO;
import exception.ReadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(urlPatterns = "/levels/delete/")
public class LevelDeleteController extends HttpServlet {
    private LevelDAO levelDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            levelDAO = new LevelJDBCDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        int id = Integer.parseInt(parameters.get("id")[0]);

        try {
            levelDAO.deleteLevel(id);
            resp.sendRedirect("/levels");
        } catch (ReadException e) {
            req.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error-page");
            dispatcher.forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }
}


