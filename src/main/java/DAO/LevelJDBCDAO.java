package DAO;

import exception.ConnectionException;
import exception.ReadException;
import model.Level;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LevelJDBCDAO implements LevelDAO {
    public Level level;

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
    public void insertNewLevel(Level level) throws ReadException {

        try(Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO level (name, description, price, picture_url)" +
                    "VALUES (?, ?, ?, ?);");
            statement.setString(1, level.getName());
            statement.setString(2, level.getDescription());
            statement.setInt(3, level.getPrice());
            statement.setString(4, level.getPictureUrl());
            statement.executeUpdate();
        }catch(SQLException e){
            throw new ReadException("Adding new level is currently impossible.");
        }
    }

    @Override
    public void updateLevel(Level level, int levelId) throws ReadException {

        try(Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE level SET name = ?," +
                    " description  = ?, price = ?, picture_url = ? WHERE level_id =" + levelId + ";");
            statement.setString(1, level.getName());
            statement.setString(2, level.getDescription());
            statement.setInt(3, level.getPrice());
            statement.setString(4, level.getPictureUrl());
            statement.executeUpdate();
        }catch(SQLException e){
            throw new ReadException("We have a problem to update. Please try again.");

        }
    }

    @Override
    public void deleteLevel(int levelId) throws  ReadException {

        try(Connection connection = connectToDB()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM level WHERE level_id=" + " ? ;");
            statement.setInt(1, levelId);
            statement.executeUpdate();
        }catch(SQLException e){
            throw new ReadException("We couldn't delete this level. please try again!");
        }
    }

    @Override
    public List<Level> getLevelsList() throws ReadException {
        List<Level> levelsList = new ArrayList<>();
        ResultSet rs;

        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM level;");
            rs = statement.executeQuery();

            while (rs.next()) {
                Level level = new Level(rs.getInt("level_id"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("price"), rs.getDate("date_of_adding"),
                        rs.getString("picture_url"));
                levelsList.add(level);
            }
        } catch (SQLException e) {
            throw new ReadException("Something went wrong!");
        }

        return levelsList;
    }

    @Override
    public int getLevelsCount() throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM level");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access the database.");
        }
        throw new ReadException("Problem with data in database");
    }

    public Level getLevelToUpdate(int levelId) throws  ReadException {

        try(Connection connection = connectToDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM level WHERE level_id = ? ;");
            preparedStatement.setInt(1, levelId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                level = new Level(rs.getInt("level_id"), rs.getString("name"), rs.getString("description"),
                        rs.getInt("price"), rs.getDate("date_of_adding"), rs.getString("picture_url"));
            }
        }catch(SQLException e){
            throw new ReadException("We are unable to update this level. Please try again!");
        }
        return level;

    }
}


