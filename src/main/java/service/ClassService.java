package service;

import DAO.CodecoolerClassDAO;
import exception.NoComparatorException;
import exception.ReadException;
import model.CodecoolerClass;
import sort.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class ClassService {
    private final CodecoolerClassDAO classDAO;

    public ClassService(CodecoolerClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    public List<CodecoolerClass> getAllCodecoolerClasses(String sortBy, Boolean order) throws ReadException {
        List<CodecoolerClass> allClasses = classDAO.getAllCodecoolerClasss();
        if (order != null && sortBy != null) {
            try {
                allClasses = sortList(allClasses, order, sortBy);
            } catch (NoComparatorException e) {
                throw new ReadException(e.getMessage());
            }
        }
        return allClasses;
    }

    public CodecoolerClass extractClassFromHTTPRequest(HttpServletRequest request) {
        String name = request.getParameter("class-name");
        String city = request.getParameter("class-city");
        Date startDate = Date.valueOf(request.getParameter("class-start-date"));
        Date endDate = Date.valueOf(request.getParameter("class-end-date"));

        return new CodecoolerClass.Builder()
                .withName(name)
                .withCity(city)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .build();
    }

    public CodecoolerClass changeClassDetails(HttpServletRequest request, CodecoolerClass codecoolerclass) {
        String name = request.getParameter("class-name");
        String city = request.getParameter("class-city");
        Date startDate = Date.valueOf(request.getParameter("class-start-date"));
        Date endDate = Date.valueOf(request.getParameter("class-end-date"));

        codecoolerclass.setName(name);
        codecoolerclass.setCity(city);
        codecoolerclass.setStartDate(startDate);
        codecoolerclass.setEndDate(endDate);

        return codecoolerclass;
    }

    private List<CodecoolerClass> sortList(List<CodecoolerClass> allClasses, boolean order, String sortBy) throws NoComparatorException {
        Comparing<CodecoolerClass> comparing = new ComparatorClass();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<CodecoolerClass> comparator = comparing.getComparator(typeColumn);
        SortItems<CodecoolerClass> sortItems = new SortItems<>(allClasses, comparator);

        return sortItems.sort(order);
    }
}
