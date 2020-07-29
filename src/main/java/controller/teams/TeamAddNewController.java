package controller.teams;

import DAO.TeamDAO;
import DAO.TeamJDBCDAO;
import exception.ReadException;
import model.Team;
import service.TeamService;
import validation.ValidationHelper;
import validation.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeamAddNewController", urlPatterns = "teams/add")
public class TeamAddNewController extends HttpServlet {
    private TeamDAO teamDAO;
    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.teamDAO = new TeamJDBCDAO();
        this.teamService = new TeamService(teamDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/teams_add_new.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ValidationHelper validationHelper = new ValidationHelper(request, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("team-name", "name_validation_message", 3, 25)
                .helpValidateStringLength("team-city", "city_validation_message", 3, 25)
                .helpValidateDate("team-start-date", "start_date_validation_message", "yyyy-mm-dd")
                .complete();

        if (isInputValid) {
            try {
                Team team = teamService.extractTeamFromHTTPRequest(request);
                teamDAO.addTeam(team);
                request.setAttribute("message", "Team successfully added!");
            } catch (ReadException e) {
                request.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
                dispatcher.forward(request, response);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/teams_add_new.jsp");
        dispatcher.forward(request, response);
    }
}
