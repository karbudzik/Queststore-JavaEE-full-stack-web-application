package controller.codecoolers;

import DAO.*;

import exception.ReadException;
import model.Codecooler;
import model.CodecoolerClass;
import model.Team;
import service.ClassService;
import service.CodecoolerService;
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
import java.util.List;

@WebServlet(name = "CodecoolerAddNewController", urlPatterns = "/codecoolers/add")
public class CodecoolerAddNewController extends HttpServlet {
    private CodecoolerDAO codecoolerDAO;
    private CodecoolerService codecoolerService;
    private TeamService teamService;
    private ClassService classService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.codecoolerDAO = new CodecoolerJDBCDAO();
        this.codecoolerService = new CodecoolerService(codecoolerDAO, new CodecoolerClassJDBCDAO(), new TeamJDBCDAO());
        this.teamService = new TeamService(new TeamJDBCDAO());
        this.classService = new ClassService(new CodecoolerClassJDBCDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CodecoolerClass> classList = classService.getAllCodecoolerClasses("name", true);
            List<Team> teamsList = teamService.getAllTeams("name", true);
            request.setAttribute("classList", classList);
            request.setAttribute("teamsList", teamsList);
        } catch (ReadException e) {
            request.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/codecoolers_add_new.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidationHelper validationHelper = new ValidationHelper(request, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("person-name", "name_validation_message", 3, 25)
                .helpValidateEmail("person-email", "email_validation_message", 3, 25)
                .helpValidateStringLength("person-city", "city_validation_message", 3, 25)
                .complete();

        if (isInputValid) {
            try {
                Codecooler codecooler = codecoolerService.extractCodecoolerFromHTTPRequest(request);
                codecoolerDAO.addCodecooler(codecooler);
                request.setAttribute("message", "Codecooler successfully added!");
            } catch (ReadException e) {
                request.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
                dispatcher.forward(request, response);
            }
        }
        doGet(request, response);
    }
}

