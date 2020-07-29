package controller.quests;

import DAO.QuestDAO;
import DAO.QuestJDBCDAO;
import exception.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestDeleteController",  urlPatterns = "/quests/delete")
public class QuestDeleteController extends HttpServlet {
    private QuestDAO questDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        questDAO = new QuestJDBCDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            questDAO.deleteQuest(id);
        } catch (ReadException e) {
            request.setAttribute("message", e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/quests");
        dispatcher.forward(request, response);
    }
}
