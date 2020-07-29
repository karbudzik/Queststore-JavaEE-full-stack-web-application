package controller.teams;

import DAO.*;
import exception.ReadException;
import model.Artifact;
import model.Codecooler;
import model.Team;
import service.ArtifactService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeamEditController", urlPatterns = "/teams/edit")
public class TeamEditController extends HttpServlet {
    private TeamDAO teamDAO;
    private TeamService teamService;
    private CodecoolerDAO codecoolerDAO;
    private ArtifactDAO artifactDAO;
    private ArtifactService artifactService;
    private Team team;
    private Integer id;
    private List<Artifact> artifactsList;
    private List<Codecooler> teamCodecoolersList;
    private List<Codecooler> allRemainingCodecoolersList;

    @Override
    public void init() throws ServletException {
        super.init();
        this.teamDAO = new TeamJDBCDAO();
        this.teamService = new TeamService(teamDAO);
        this.codecoolerDAO = new CodecoolerJDBCDAO();
        this.artifactDAO = new ArtifactJDBCDAO();
        this.artifactService = new ArtifactService(artifactDAO);
        this.artifactsList = new ArrayList<>();
        this.teamCodecoolersList = new ArrayList<>();
        this.allRemainingCodecoolersList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateTeamIdFromRequestIfExists(request);
            loadRequiredData();
            setAttributesToRequest(request);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("/html-cms/teams_update.jsp");
            dispatcher.forward(request, response);
        } catch (ReadException e) {
            request.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "basic-information":
                triggerInformationUpdate(request, response);
                break;

            case "team-members":
                triggerMembersUpdate(request, response);
                break;

            case "team-artifacts":
                triggerArtifactsUpdate(request, response);
                break;
        }

        doGet(request, response);
    }

    private void loadRequiredData() throws ReadException {
        team = teamDAO.getTeamById(id);
        teamCodecoolersList = codecoolerDAO.getCodecoolersByTeamId(id);
        allRemainingCodecoolersList = codecoolerDAO.getAllCodecoolers();
        allRemainingCodecoolersList.removeAll(teamCodecoolersList);
        artifactsList = artifactDAO.getArtifactsByTeamId(id);
    }

    private void setAttributesToRequest(HttpServletRequest request) {
        request.setAttribute("team", team);
        request.setAttribute("teamCodecoolersList", teamCodecoolersList);
        request.setAttribute("allRemainingCodecoolersList", allRemainingCodecoolersList);
        request.setAttribute("teamArtifactsList", artifactsList);
    }

    private void triggerInformationUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidationHelper validationHelper = new ValidationHelper(request, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("team-name", "name_validation_message", 3, 25)
                .helpValidateStringLength("team-city", "city_validation_message", 3, 25)
                .helpValidateDate("team-start-date", "start_date_validation_message", "yyyy-mm-dd")
                .complete();

        if (isInputValid) {
            try {
                team = teamService.changeTeamDetails(request, team);
                teamDAO.editTeam(id, team);
                request.setAttribute("message", "Team successfully modified!");
            } catch (ReadException e) {
                request.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
                dispatcher.forward(request, response);
            }
        }
    }

    private void triggerMembersUpdate(HttpServletRequest request, HttpServletResponse response) {
        try {
            int studentId = Integer.parseInt(request.getParameter("student"));
            Codecooler codecooler = codecoolerDAO.getCodecoolerById(studentId);
            codecooler.setTeamId(id);
            codecoolerDAO.editCodecooler(studentId, codecooler);
            request.setAttribute("message", "Codecooler succesfully added to this team!");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "You have to choose a codecooler to add!");
        } catch (ReadException e) {
            request.setAttribute("message", e.getMessage());
        }
    }

    private void triggerArtifactsUpdate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] strings = request.getParameterMap().get("is-used");
            artifactsList = artifactService.callArtifactUsageChange(strings, artifactsList);
            request.setAttribute("message", "You've successfully changed this team's quest!");
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "You can't edit quests if the team doesn't have any!");
        } catch (ReadException e) {
            request.setAttribute("message", e.getMessage());
        }
    }

    private void updateTeamIdFromRequestIfExists(HttpServletRequest request) {
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            // clause left empty on purpose - if we don't jave "id" parameter, the id field already has a value
        }
    }
}
