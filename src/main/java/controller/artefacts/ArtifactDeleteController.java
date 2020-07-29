package controller.artefacts;

import DAO.ArtifactDAO;
import DAO.ArtifactJDBCDAO;
import exception.ReadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/artifacts/delete/")
public class ArtifactDeleteController extends HttpServlet {
    ArtifactDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new ArtifactJDBCDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int artifactId = Integer.parseInt(req.getParameterMap().get("id")[0]);
            dao.deleteArtifact(artifactId);
            resp.sendRedirect("/artifacts");
        } catch (ReadException e) {
            req.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/error_page.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
