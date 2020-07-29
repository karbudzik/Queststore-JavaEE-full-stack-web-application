package controller.classes;

import DAO.CodecoolerClassDAO;
import DAO.CodecoolerClassJDBCDAO;
import DAO.DataSourceReader;
import exception.ConnectionException;
import exception.ReadException;
import model.CodecoolerClass;
import model.Team;
import org.postgresql.ds.PGSimpleDataSource;
import service.ClassService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ClassesList", urlPatterns = "/classes")
public class ClassesListController extends HttpServlet {
    private PGSimpleDataSource ds;
    private CodecoolerClassDAO dao;
    private ClassService classService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ds = DataSourceReader.getDataSource("src/main/resources/database.properties");
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }
        dao = new CodecoolerClassJDBCDAO(ds);
        classService = new ClassService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        Boolean order = getOrder(parameters);
        String sortBy = getSortBy(parameters);
        try {
            List<CodecoolerClass> codecoolerClassList = classService.getAllCodecoolerClasses(sortBy, order);
            req.setAttribute("allClassList", codecoolerClassList);
            if (codecoolerClassList.size() == 0) {
                req.setAttribute("message", "There are no classes available");
            }
        } catch (ReadException e) {
            req.setAttribute("error_message", e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/error_page.jsp");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/classes_list.jsp");
        dispatcher.forward(req, resp);
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
