package DAO;

import exception.ConnectionException;
import exception.ReadException;
import model.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TeamJDBCDAO implements TeamDAO {

    private Connection connectToDB() {
        try {
            Properties prop = PropertiesReader.readProperties("src/main/resources/database.properties");
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.passwd");
            return DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            throw new ConnectionException("Sorry, couldn't connect to database");
        }
    }

    @Override
    public void addTeam(Team team) throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO team (name, city, start_date) VALUES (?, ?, ?);");
            statement.setString(1, team.getName());
            statement.setString(2, team.getCity());
            statement.setDate(3, team.getStartDate());
            statement.execute();
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't insert this team to database");
        }
    }

    @Override
    public void deleteTeam(int id) throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM team WHERE team_id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't delete this team");
        }
    }

    @Override
    public List<Team> getAllTeams() throws ReadException {
        List<Team> teamsList = new ArrayList<>();
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM team;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Team team = extractTeamFromResultSet(rs);
                teamsList.add(team);
            }
        } catch (SQLException e) {
            throw new ReadException("Sorry, getting teams from database is currently impossible");
        }
        return teamsList;
    }

    @Override
    public Team getTeamById(int id) throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM team WHERE team_id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return extractTeamFromResultSet(rs);
            }
            throw new ReadException("Sorry, this quest does not exist");
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get this quest");
        }
    }

    @Override
    public void editTeam(int id, Team team) throws ReadException {
        String query = "UPDATE team SET name = ?, city = ?, start_date = ? WHERE team_id = ?";
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, team.getName());
            statement.setString(2, team.getCity());
            statement.setDate(3, team.getStartDate());
            statement.setInt(4, team.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot update this team!");
        }
    }

    @Override
    public int getTeamsCount() throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM team");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access the database.");
        }
        throw new ReadException("Problem with data in database");
    }

    private Team extractTeamFromResultSet(ResultSet rs) throws SQLException {
        return new Team.Builder()
                .withID(rs.getInt("team_id"))
                .withName(rs.getString("name"))
                .withCity(rs.getString("city"))
                .withStartDate(rs.getDate("start_date"))
                .build();
    }
}
