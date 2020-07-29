package DAO;

import exception.ConnectionException;
import exception.ReadException;
import model.Artifact;
import model.Codecooler;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtifactJDBCDAO implements ArtifactDAO {

    private ResultSet resultSet;

    private Connection connectToDB() throws ConnectionException {
        try {
            Properties prop = PropertiesReader.readProperties("src/main/resources/database.properties");
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.passwd");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            throw new ConnectionException("Sorry, data base is currently not available.");
        }
    }

    private ResultSet askForAllArtifacts() throws SQLException {
        String query = "SELECT * FROM artifact";
        Statement statement;
            statement = connectToDB().createStatement();
            resultSet = statement.executeQuery(query);

        return resultSet;
    }

    @Override
    public List<Artifact> getAllArtifacts() throws ReadException {
        List<Artifact> allArtifacts = new ArrayList<Artifact>();
        try {
            resultSet = askForAllArtifacts();

            while (resultSet.next()) {
                Artifact artifact = new Artifact.Builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .value(resultSet.getInt(4))
                        .type(resultSet.getString(5))
                        .dateOfAdding(resultSet.getDate(6))
                        .pictureUrl(resultSet.getString(7))
                        .build();
                allArtifacts.add(artifact);
            }
        } catch (SQLException e) {
            throw new ReadException("Sorry, getting artifacts from database is currently impossible");
        }
        return allArtifacts;
    }

    @Override
    public void addArtifact(Artifact artifact) throws ReadException {
        String query = "INSERT INTO artifact values(default,?,?,?,?,default,?);";
        try {
            PreparedStatement preparedStatement = connectToDB().prepareStatement(query);
            preparedStatement.setString(1, artifact.getName());
            preparedStatement.setString(2, artifact.getDescription());
            preparedStatement.setInt(3, artifact.getValue());
            preparedStatement.setString(4, artifact.getType());
            preparedStatement.setString(5, artifact.getPictureUrl());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new ReadException("Adding of artifacts is currently not available.");
        }
    }

    @Override
    public void deleteArtifact(int id) throws ReadException {
        String query = "delete from artifact where artifact_id = ?;";
        try {
            PreparedStatement preparedStatement = connectToDB().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ReadException("Sorry, this artifacts could not be deleted.");
        }
    }

    public int getNextAvailableID() {
        int nextAvailableId = 0;
        try {
            String query = "SELECT artifact_id FROM artifact order by artifact_id desc limit 1";
            Statement statement = connectToDB().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                nextAvailableId = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nextAvailableId;
    }

    @Override
    public Artifact getArtifactById(int id) throws ReadException {
        String query = "SELECT * FROM artifact WHERE artifact_id = ?";
        Artifact artifact = new Artifact();
        try {
            PreparedStatement preparedStatement = connectToDB().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Artifact(resultSet);
            }
        } catch (SQLException e) {
            throw new ReadException("This artifact is currently not available");
        }
        return artifact;
    }

    @Override
    public void updateArtifact(int artifactToUpdateId, Artifact artifactUpdated) throws ReadException {
        String query = "UPDATE artifact set name = ?, description = ?, value = ?, type = ? WHERE artifact_id = ?;";
        try {
            PreparedStatement preparedStatement = connectToDB().prepareStatement(query);
            preparedStatement.setString(1, artifactUpdated.getName());
            preparedStatement.setString(2, artifactUpdated.getDescription());
            preparedStatement.setInt(3, artifactUpdated.getValue());
            preparedStatement.setString(4, artifactUpdated.getType());
            preparedStatement.setInt(5, artifactToUpdateId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ReadException("You cannot update this artifact");
        }
    }

    @Override
    public int getArtifactsCount() throws ReadException,ConnectionException {
        try {
            String query = "SELECT COUNT(*) FROM artifact";
            PreparedStatement preparedStatement = connectToDB().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new ReadException("You cannot access the database.");
        }
        throw new ReadException("Problem with data in database");
    }

    @Override
    public List<Artifact> getArtifactsByTeamId(int id) throws ReadException {
        List<Artifact> artifactList = new ArrayList<>();
        try {
            String query = "SELECT artifact_id, is_used FROM team_artifacts WHERE team_id = ?";
            PreparedStatement pst = connectToDB().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Artifact artifact = getArtifactById(rs.getInt("artifact_id"));
                artifact.setUsed(rs.getBoolean("is_used"));
                artifactList.add(artifact);
            }
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get the artifacts!");
        }
        return artifactList;
    }

    @Override
    public void markIfArtifactUsed(int id, boolean isUsed) throws ReadException {
        try {
            String query = "UPDATE team_artifacts SET is_used = ? WHERE artifact_id = ?";
            PreparedStatement pst = connectToDB().prepareStatement(query);
            pst.setBoolean(1, isUsed);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't edit this artifact!");
        }
    }
}
