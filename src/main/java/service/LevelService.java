package service;

import DAO.LevelDAO;
import exception.NoComparatorException;
import exception.ReadException;
import exception.StringLengthFormatException;
import exception.ValueFormatException;
import model.Artifact;
import model.Level;
import sort.*;
import validation.ValidationHelper;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;


public class LevelService {
    Validator validator = new Validator();
    private LevelDAO dao;

    public LevelService() {
    }

    public LevelService(LevelDAO dao) {
        this.dao = dao;
    }

    public List<Level> getAllLevels(String sortBy, Boolean order) throws ReadException {
        List<Level> allLevels = dao.getLevelsList();
        if (order != null && sortBy != null) {
            try {
                allLevels = sortList(allLevels, order, sortBy);
            } catch (NoComparatorException e) {
                throw new ReadException(e.getMessage());
            }
        }
        return allLevels;
    }

    public Level getInformationFromServlet(HttpServletRequest req) {
        String name = req.getParameter("level-name");
        String description = req.getParameter("level-description");
        String coins = req.getParameter("level-coins");
        int price = parseInt(coins);

        return new Level(name, description, price, "level6.svg");
    }

    public Level getInformationToUpdate(HttpServletRequest request, Level level) {
        String name = request.getParameter("level-name");
        String description = request.getParameter("level-description");
        String coins = request.getParameter("level-coins");
        int price = parseInt(coins);

        level.setName(name);
        level.setDescription(description);
        level.setPrice(price);

        return level;

    }

    private List<Level> sortList(List<Level> allArtifacts, boolean order, String sortBy) throws NoComparatorException {
        Comparing<Level> comparing = new ComparatorLevel();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<Level> comparator = comparing.getComparator(typeColumn);
        SortItems<Level> sortItems = new SortItems<>(allArtifacts, comparator);

        return sortItems.sort(order);
    }
}
