package service;

import DAO.TeamDAO;
import exception.NoComparatorException;
import exception.ReadException;
import model.Quest;
import model.Team;
import sort.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class TeamService {

    private final TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public List<Team> getAllTeams(String sortBy, Boolean order) throws ReadException {
        List<Team> allTeams = teamDAO.getAllTeams();
        if (order != null && sortBy != null) {
            try {
                allTeams = sortList(allTeams, order, sortBy);
            } catch (NoComparatorException e) {
                throw new ReadException(e.getMessage());
            }
        }
        return allTeams;
    }

    private List<Team> sortList(List<Team> allTeams, boolean order, String sortBy) throws NoComparatorException {
        Comparing<Team> comparing = new ComparatorTeam();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<Team> comparator = comparing.getComparator(typeColumn);
        SortItems<Team> sortItems = new SortItems<>(allTeams, comparator);

        return sortItems.sort(order);
    }

    public Team extractTeamFromHTTPRequest(HttpServletRequest request) {
        String name = request.getParameter("team-name");
        String city = request.getParameter("team-city");
        Date date = Date.valueOf(request.getParameter("team-start-date"));

        return new Team.Builder()
                .withName(name)
                .withCity(city)
                .withStartDate(date)
                .build();
    }

    public Team changeTeamDetails(HttpServletRequest request, Team team) {
        String name = request.getParameter("team-name");
        String city = request.getParameter("team-city");
        Date date = Date.valueOf(request.getParameter("team-start-date"));
        team.setName(name);
        team.setCity(city);
        team.setStartDate(date);

        return team;
    }
}
