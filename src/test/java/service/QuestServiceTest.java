package service;

import DAO.QuestDAO;
import exception.ReadException;
import model.Quest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuestServiceTest {

    @InjectMocks
    QuestService questService;

    @Mock
    QuestDAO questDAO;

    @BeforeEach
    public void init() throws ReadException {
        MockitoAnnotations.initMocks(this);
        given(questDAO.getAllQuests()).willReturn(prepareMockData());
    }

    @Test
    public void should_get_unsorted_quests_if_sorting_parameters_dont_exist() throws ReadException {
        // given:
        String sortBy = null;
        Boolean order = null;
        List<Quest> questListBeforeSort = prepareMockData();

        // when:
        List<Quest> questListFromService = questService.getAllQuests(sortBy, order);

        // then:
        Assertions.assertEquals(questListBeforeSort.get(0).getID(), questListFromService.get(0).getID());
        Assertions.assertEquals(questListBeforeSort.get(1).getID(), questListFromService.get(1).getID());
        Assertions.assertEquals(questListBeforeSort.get(2).getID(), questListFromService.get(2).getID());
        verify(questDAO, times(1)).getAllQuests();
    }

    @Test
    public void should_sort_quests_by_name_ascending() throws ReadException {
        // given:
        String sortBy = "name";
        Boolean order = true;

        // when:
        List<Quest> sortedQuestsList = questService.getAllQuests(sortBy, order);

        // then:
        Assertions.assertEquals(sortedQuestsList.get(0).getName(), "A");
        Assertions.assertEquals(sortedQuestsList.get(1).getName(), "B");
        Assertions.assertEquals(sortedQuestsList.get(2).getName(), "G");
        verify(questDAO, times(1)).getAllQuests();
    }

    @Test
    public void should_sort_quests_by_value_descending() throws ReadException {
        // given:
        String sortBy = "value";
        Boolean order = false;

        // when:
        List<Quest> sortedQuestsList = questService.getAllQuests(sortBy, order);

        // then:
        Assertions.assertEquals(sortedQuestsList.get(0).getValue(), 100);
        Assertions.assertEquals(sortedQuestsList.get(1).getValue(), 10);
        Assertions.assertEquals(sortedQuestsList.get(2).getValue(), 1);
        verify(questDAO, times(1)).getAllQuests();
    }

    private List<Quest> prepareMockData() {
        Quest quest1 = new Quest();
        quest1.setID(11);
        quest1.setName("A");
        quest1.setValue(1);

        Quest quest2 = new Quest();
        quest2.setID(8);
        quest2.setName("G");
        quest2.setValue(10);

        Quest quest3 = new Quest();
        quest3.setID(22);
        quest3.setName("B");
        quest3.setValue(100);

        List<Quest> questList = new ArrayList<>();

        questList.add(quest1);
        questList.add(quest2);
        questList.add(quest3);

        return questList;
    }
}