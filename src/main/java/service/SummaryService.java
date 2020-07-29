package service;

import DAO.*;
import model.SummaryAdmin;
import model.SummaryMentor;
import exception.*;
import model.Team;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

public class SummaryService {
    private UserDAO userDAO;
    private QuestDAO questDAO;
    private ArtifactDAO artifactDAO;
    private CodecoolerDAO codecoolerDAO;
    private CodecoolerClassDAO classDAO;
    private TeamDAO teamDAO;
    private LevelDAO levelDAO;

    public SummaryService(UserDAO userDAO, QuestDAO questDAO, ArtifactDAO artifactDAO, CodecoolerDAO codecoolerDAO,
                          CodecoolerClassDAO classDAO, TeamDAO teamDAO, LevelDAO levelDAO) {
        this.userDAO = userDAO;
        this.questDAO = questDAO;
        this.artifactDAO = artifactDAO;
        this.codecoolerDAO = codecoolerDAO;
        this.classDAO = classDAO;
        this.teamDAO = teamDAO;
        this.levelDAO = levelDAO;
    }

    private Connection connectToDB() throws IOException, SQLException {
        Properties prop = PropertiesReader.readProperties("src/main/resources/database.properties");
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.passwd");

        return DriverManager.getConnection(url, user, password);
    }

    public SummaryMentor getSummaryMentor() throws SQLException, IOException, ConnectionException, ReadException {
        Connection connectionToDB = connectToDB();
        Statement statement = connectionToDB.createStatement();

        return new SummaryMentor(
                getCodecoolersCount(),
                getClassesCount(),
                getTeamsCount(),
                getQuestsCount(),
                getArtifactsCount()
        );
    }

    public SummaryAdmin getSummaryAdmin() throws SQLException, IOException, ReadException, ConnectionException {
        Connection connectionToDB = connectToDB();
        Statement statement = connectionToDB.createStatement();

        return new SummaryAdmin(
                getCodecoolersCount(),
                getClassesCount(),
                getTeamsCount(),
                getQuestsCount(),
                getArtifactsCount(),
                getAdminsCount(),
                getMentorsCount(),
                getLevelsCount()
        );
    }

    private int getMentorsCount() throws ReadException {
        return userDAO.getMentorsCount();
    }

    private int getAdminsCount() throws ReadException {
        return userDAO.getAdminsCount();
    }

    private int getArtifactsCount() throws ConnectionException, ReadException {
        return artifactDAO.getArtifactsCount();
    }

    private int getCodecoolersCount() throws ReadException {
        return codecoolerDAO.getCodecoolersCount();
    }

    private int getClassesCount() throws ReadException {
        return classDAO.getCodecoolerClassesCount();
    }

    private int getTeamsCount() throws ReadException {
        return teamDAO.getTeamsCount();
    }

    private int getQuestsCount() throws ConnectionException, ReadException {
        return questDAO.getQuestsCount();
    }

    private int getLevelsCount() throws ReadException {
        return levelDAO.getLevelsCount();
    }
}

