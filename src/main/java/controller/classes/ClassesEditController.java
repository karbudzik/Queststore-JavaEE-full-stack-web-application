package controller.classes;

import DAO.*;
import exception.ConnectionException;
import exception.ReadException;
import model.Codecooler;
import model.CodecoolerClass;
import org.postgresql.ds.PGSimpleDataSource;
import service.ClassService;
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
import java.util.stream.Collectors;

@WebServlet(name = "ClassesEditController", urlPatterns = "/classes/edit")
public class ClassesEditController extends HttpServlet {
    private PGSimpleDataSource ds;
    private CodecoolerClassDAO classDAO;
    private CodecoolerDAO codecoolerDAO;
    private ClassService classService;
    private Integer id;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ds = DataSourceReader.getDataSource("src/main/resources/database.properties");
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }
        classDAO = new CodecoolerClassJDBCDAO(ds);
        codecoolerDAO = new CodecoolerJDBCDAO(ds);
        classService = new ClassService(classDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fillDetailsAboutClass(request, response);
        fillDetailsAboutAvailableCodecoolers(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html-cms/classes_update.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOfAction = req.getParameter("action");
        switch (typeOfAction) {
            case "update-details":
                triggerDetailsUpdate(req, resp);
                break;
            case "add-new-codecooler":
                triggerAddingCodecooler(req, resp);
                break;
        }
        doGet(req, resp);
    }

    private void fillDetailsAboutClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        updateClassIdFromRequestIfExists(request);
        try {
            CodecoolerClass codecoolerClass = classDAO.getCodecoolerClassById(id);
            request.setAttribute("classToEdit", codecoolerClass);
        } catch (ReadException e) {
            response.sendRedirect("/classes");
        }
    }

    private void fillDetailsAboutAvailableCodecoolers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Codecooler> codecoolerList = codecoolerDAO.getAllCodecoolers();
            codecoolerList = codecoolerList.stream()
                    .filter(x -> x.getClassId() != id)
                    .collect(Collectors.toList());

            request.setAttribute("codecoolerList", codecoolerList);
        } catch (ReadException ex) {
            response.sendRedirect("/classes");
        }
    }

    private void triggerDetailsUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ValidationHelper validationHelper = new ValidationHelper(req, new Validator());

        boolean isInputValid = validationHelper
                .helpValidateStringLength("class-name", "name_validation_message", 3, 45)
                .helpValidateStringLength("class-city", "city_validation_message", 3, 25)
                .helpValidateDate("class-start-date", "start_date_validation_message", "yyyy-mm-dd")
                .helpValidateDate("class-end-date", "end_date_validation_message", "yyyy-mm-dd")
                .complete();

        if (isInputValid) {
            try {
                CodecoolerClass codecoolerClass = classDAO.getCodecoolerClassById(this.id);
                codecoolerClass = classService.changeClassDetails(req, codecoolerClass);
                classDAO.editCodecoolerClass(this.id, codecoolerClass);
                req.setAttribute("message", "Class successfully modified!");
            } catch (ReadException ex) {
                req.setAttribute("error_message", ex.getMessage());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/error_page.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    private void triggerAddingCodecooler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newCodecoolerId = Integer.parseInt(req.getParameter("select-new-student"));
        try {
            Codecooler newCodecooler = codecoolerDAO.getCodecoolerById(newCodecoolerId);
            newCodecooler.setClassId(this.id);
            codecoolerDAO.editCodecooler(newCodecooler.getId(), newCodecooler);
        } catch (ReadException e) {
            req.setAttribute("error-message", "You cannot add new codecooler. " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/html-cms/error_page.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void updateClassIdFromRequestIfExists(HttpServletRequest request) {
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            // clause left empty on purpose - if we don't jave "id" parameter, the id field already has a value
        }
    }
}
