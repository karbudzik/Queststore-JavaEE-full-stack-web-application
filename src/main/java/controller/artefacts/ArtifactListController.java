package controller.artefacts;

import DAO.ArtifactDAO;
import DAO.ArtifactJDBCDAO;
import exception.ReadException;
import model.Artifact;
import service.ArtifactService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Artifacts", urlPatterns = "/artifacts")

public class ArtifactListController extends HttpServlet {
    private ArtifactService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ArtifactDAO dao = new ArtifactJDBCDAO();
        service = new ArtifactService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        Boolean order = getOrder(parameters);
        String sortBy = getSortBy(parameters);
        try {
            List<Artifact> allArtifacts = service.getAllArtifacts(sortBy, order);
            req.setAttribute("allArtifacts", allArtifacts);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/artifacts_list.jsp");
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
