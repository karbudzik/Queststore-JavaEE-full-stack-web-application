package controller.teams;

import DAO.CodecoolerDAO;
import DAO.CodecoolerJDBCDAO;
import exception.ReadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeamDeleteCodecoolerController", urlPatterns = "/teams/edit/delete_codecooler")
public class TeamDeleteCodecoolerController extends HttpServlet {

    CodecoolerDAO codecoolerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        codecoolerDAO = new CodecoolerJDBCDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int team_id = Integer.parseInt(request.getParameter("team_id"));
        int codecooler_id = Integer.parseInt(request.getParameter("codecooler_id"));
        try {
            codecoolerDAO.clearCodecoolerTeamId(codecooler_id);
        } catch (ReadException e) {
            request.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
            dispatcher.forward(request, response);
        }
        response.sendRedirect("/teams/edit?id=" + team_id);
    }
}
