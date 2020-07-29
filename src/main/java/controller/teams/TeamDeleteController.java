package controller.teams;

import DAO.TeamDAO;
import DAO.TeamJDBCDAO;
import exception.ReadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeamDeleteController", urlPatterns = "/teams/delete")
public class TeamDeleteController extends HttpServlet {
    private TeamDAO teamDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        teamDAO = new TeamJDBCDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            teamDAO.deleteTeam(id);
            request.setAttribute("message", "Team successfully deleted!");
        } catch (ReadException e) {
            request.setAttribute("message", e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/teams");
        dispatcher.forward(request, response);
    }
}
