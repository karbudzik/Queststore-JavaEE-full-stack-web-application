package DAO;

import exception.ReadException;
import model.Team;

import java.util.List;

public interface TeamDAO {
    void addTeam(Team team) throws ReadException;

    void deleteTeam(int id) throws ReadException;

    List<Team> getAllTeams() throws ReadException;

    Team getTeamById(int id) throws ReadException;

    void editTeam(int id, Team team) throws ReadException;

    int getTeamsCount() throws ReadException;
}
