package controller.codecoolers;

import DAO.CodecoolerClassJDBCDAO;
import DAO.CodecoolerJDBCDAO;
import DAO.QuestJDBCDAO;
import DAO.TeamJDBCDAO;
import exception.ReadException;
import model.Codecooler;
import model.Quest;
import service.CodecoolerService;
import service.QuestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CodecoolerListController", urlPatterns = "/codecoolers")
public class CodecoolerListController extends HttpServlet {
    private CodecoolerService codecoolerService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.codecoolerService = new CodecoolerService(new CodecoolerJDBCDAO(), new CodecoolerClassJDBCDAO(), new TeamJDBCDAO());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        Boolean order = getOrder(parameters);
        String sortBy = getSortBy(parameters);
        try {
            Map<Codecooler, List<String>> codecoolersMap = codecoolerService.getAllCodecoolersWithClassNameAndTeamName(sortBy, order);
            request.setAttribute("codecoolersMap", codecoolersMap);
            if (codecoolersMap.size() == 0) {
                request.setAttribute("message", "There are no quests available");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/codecoolers_list.jsp");
            dispatcher.forward(request, response);
        } catch (ReadException e) {
            request.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private Boolean getOrder(Map<String, String[]> parameters){
        if (parameters.containsKey("order")) {
            return parameters.get("order")[0].equals("ASC");
        }
        return null;
    }

    private String getSortBy(Map<String, String[]> parameters){
        if (parameters.containsKey("sortBy")) {
            return parameters.get("sortBy")[0];
        }
        return null;
    }
}
