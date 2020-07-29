package controller.quests;

import DAO.QuestDAO;
import DAO.QuestJDBCDAO;
import model.Quest;
import exception.*;
import service.QuestService;
import validation.ValidationHelper;
import validation.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "QuestAddNewController", urlPatterns = "quests/add")
public class QuestAddNewController extends HttpServlet {
    private QuestDAO questDAO;
    private QuestService questService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.questDAO = new QuestJDBCDAO();
        this.questService = new QuestService(questDAO);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/quests_add_new.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ValidationHelper validationHelper = new ValidationHelper(request, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("quest-name", "name_validation_message", 3, 50)
                .helpValidateStringLength("quest-description", "description_validation_message", 3, 100)
                .helpValidateValue("quest-value", "value_validation_message", 0, 1000000)
                .helpValidateType("quest-type", "type_validation_message", "BASIC", "EXTRA")
                .complete();

        if (isInputValid) {
            try {
                Quest quest = questService.extractQuestFromHTTPRequest(request);
                questDAO.insertQuest(quest);
                request.setAttribute("message", "Quest successfully added!");
            } catch (ReadException e) {
                request.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
                dispatcher.forward(request, response);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/quests_add_new.jsp");
        dispatcher.forward(request, response);
    }
}
