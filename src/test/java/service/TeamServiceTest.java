package service;

import DAO.TeamDAO;
import exception.ReadException;
import model.Quest;
import model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TeamServiceTest {
    @InjectMocks
    TeamService teamService;

    @Mock
    TeamDAO teamDAO;

    @BeforeEach
    public void init() throws ReadException {
        MockitoAnnotations.initMocks(this);
        given(teamDAO.getAllTeams()).willReturn(prepareMockData());
    }

    @Test
    public void should_get_unsorted_teams_if_sorting_parameters_dont_exist() throws ReadException {
        // given:
        String sortBy = null;
        Boolean order = null;
        List<Team> teamsListBeforeSort = prepareMockData();

        // when:
        List<Team> teamsListFromService = teamService.getAllTeams(sortBy, order);

        // then:
        Assertions.assertEquals(teamsListBeforeSort.get(0).getId(), teamsListFromService.get(0).getId());
        Assertions.assertEquals(teamsListBeforeSort.get(1).getId(), teamsListFromService.get(1).getId());
        Assertions.assertEquals(teamsListBeforeSort.get(2).getId(), teamsListFromService.get(2).getId());
        verify(teamDAO, times(1)).getAllTeams();
    }

    @Test
    public void should_sort_teams_by_city_ascending() throws ReadException {
        // given:
        String sortBy = "city";
        Boolean order = true;

        // when:
        List<Team> sortedQuestsList = teamService.getAllTeams(sortBy, order);

        // then:
        Assertions.assertEquals(sortedQuestsList.get(0).getCity(), "Bucharest");
        Assertions.assertEquals(sortedQuestsList.get(1).getCity(), "Cracow");
        Assertions.assertEquals(sortedQuestsList.get(2).getCity(), "Warsaw");
        verify(teamDAO, times(1)).getAllTeams();
    }

    @Test
    public void should_sort_teams_by_date_descending() throws ReadException {
        // given:
        String sortBy = "date";
        Boolean order = false;

        // when:
        List<Team> sortedQuestsList = teamService.getAllTeams(sortBy, order);

        // then:
        Assertions.assertEquals(sortedQuestsList.get(0).getStartDate().toString(), "2020-09-15");
        Assertions.assertEquals(sortedQuestsList.get(1).getStartDate().toString(), "2020-05-15");
        Assertions.assertEquals(sortedQuestsList.get(2).getStartDate().toString(), "2019-05-10");
        verify(teamDAO, times(1)).getAllTeams();
    }

    private List<Team> prepareMockData() {
        Team team1 = new Team.Builder()
                .withID(97)
                .withCity("Warsaw")
                .withStartDate(Date.valueOf("2020-05-15"))
                .build();
        Team team2 = new Team.Builder()
                .withID(99)
                .withCity("Bucharest")
                .withStartDate(Date.valueOf("2020-09-15"))
                .build();
        Team team3 = new Team.Builder()
                .withID(94)
                .withCity("Cracow")
                .withStartDate(Date.valueOf("2019-05-10"))
                .build();
        List<Team> teamsList = new ArrayList<>();
        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);

        return teamsList;
    }
}