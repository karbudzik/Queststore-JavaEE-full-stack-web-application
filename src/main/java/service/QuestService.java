package service;

import DAO.QuestDAO;
import exception.*;
import model.CMSUser;
import model.Quest;
import sort.*;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuestService {
    private final QuestDAO questDAO;

    public QuestService(QuestDAO questDAO) {
        this.questDAO = questDAO;
    }

    public List<Quest> getAllQuests(String sortBy, Boolean order) throws ReadException {
        List<Quest> allQuests = questDAO.getAllQuests();
        if (order != null && sortBy != null) {
            try {
                allQuests = sortList(allQuests, order, sortBy);
            } catch (NoComparatorException e) {
                throw new ReadException(e.getMessage());
            }
        }
        return allQuests;
    }

    public Quest extractQuestFromHTTPRequest(HttpServletRequest request) {
        String name = request.getParameter("quest-name");
        String description = request.getParameter("quest-description");
        int value = Integer.parseInt(request.getParameter("quest-value"));
        String type = request.getParameter("quest-type");
        String url = getRandomUrlPath();

        return new Quest(name, description, value, type, url);
    }

    public Quest changeQuestDetails(HttpServletRequest request, Quest quest) {
        String name = request.getParameter("quest-name");
        String description = request.getParameter("quest-description");
        int value = Integer.parseInt(request.getParameter("quest-value"));
        String type = (request.getParameter("quest-type") != null) ? request.getParameter("quest-type") : quest.getType().name();
        quest.setName(name);
        quest.setDescription(description);
        quest.setValue(value);
        quest.setType(type);

        return quest;
    }

    private List<Quest> sortList(List<Quest> allQuests, boolean order, String sortBy) throws NoComparatorException {
        Comparing<Quest> comparing = new ComparatorQuest();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<Quest> comparator = comparing.getComparator(typeColumn);
        SortItems<Quest> sortItems = new SortItems<>(allQuests, comparator);

        return sortItems.sort(order);
    }

    private String getRandomUrlPath() {
        List<String> urlPaths = Arrays.asList("quest_logo_01.svg", "quest_logo_02.svg", "quest_logo_03.svg",
                "quest_logo_04.svg", "quest_logo_05.svg", "quest_logo_06.svg");
        Random rand = new Random();

        return urlPaths.get(rand.nextInt(urlPaths.size()));
    }
}
