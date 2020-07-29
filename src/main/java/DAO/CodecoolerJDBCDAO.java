package DAO;

import exception.ReadException;
import model.Codecooler;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import exception.ConnectionException;
import model.Wallet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CodecoolerJDBCDAO implements CodecoolerDAO {
    private PGSimpleDataSource ds;

    public CodecoolerJDBCDAO() {
    }

    public CodecoolerJDBCDAO(PGSimpleDataSource ds) {
        this.ds = ds;
    }

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
    public void addCodecooler(Codecooler codecooler) throws ReadException {
        try (Connection connection = connectToDB()) {
            int wallet_id = addNewWallet();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO codecooler (name, email, password, city, picture_url, class_id, team_id, wallet_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, codecooler.getName());
            statement.setString(2, codecooler.getEmail());
            statement.setString(3, codecooler.getPassword());
            statement.setString(4, codecooler.getCity());
            statement.setString(5, codecooler.getPictureURL());

            if (codecooler.getClassId() == null || codecooler.getClassId() == 0) {
                statement.setNull(6, 4);
            } else {
                statement.setInt(6, codecooler.getClassId());
            }

            if (codecooler.getTeamId() == null || codecooler.getTeamId() == 0) {
                statement.setNull(7, 4);
            } else {
                statement.setInt(7, codecooler.getTeamId());
            }

            statement.setInt(8, wallet_id);
            statement.execute();
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't insert this team to database");
        }
    }

    @Override
    public void deleteCodecooler(int id) throws ReadException {

    }

    @Override
    public List<Codecooler> getAllCodecoolers() throws ReadException {
        List<Codecooler> codecoolerList = new ArrayList<>();
        try (Connection connection = connectToDB()) {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM codecooler");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Codecooler codecooler = extractCodecoolerFromResultSet(rs);
                codecoolerList.add(codecooler);
            }
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get codecoolers list");
        }
        return codecoolerList;
    }

    @Override
    public Codecooler getCodecoolerById(int id) throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM codecooler WHERE codecooler_id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return extractCodecoolerFromResultSet(rs);
            }
            throw new ReadException("Sorry, this codecooler does not exist");
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get this codecooler");
        }
    }

    @Override
    public Codecooler getCodecooler(String email, String password) throws ReadException {
        return null;
    }

    @Override
    public void editCodecooler(int id, Codecooler codecooler) throws ReadException {
        String query = "UPDATE codecooler SET name = ?, email = ?, password = ?, city = ?, date_of_adding = ?, picture_url = ?, class_id = ?, team_id = ? WHERE codecooler_id = ?";
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codecooler.getName());
            statement.setString(2, codecooler.getEmail());
            statement.setString(3, codecooler.getPassword());
            statement.setString(4, codecooler.getCity());
            statement.setDate(5, codecooler.getDateOfAdding());
            statement.setString(6, codecooler.getPictureURL());

            if (codecooler.getClassId() == 0) {
                statement.setNull(7, 4);
            } else {
                statement.setInt(7, codecooler.getClassId());
            }

            if (codecooler.getTeamId() == 0) {
                statement.setNull(8, 4);
            } else {
                statement.setInt(8, codecooler.getTeamId());
            }

            statement.setInt(9, codecooler.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot update this codecooler");
        }
    }

    @Override
    public boolean checkCodecooler(String email, String password) throws ReadException {
        return false;
    }

    @Override
    public List<Codecooler> getCodecoolersByTeamId(int teamId) throws ReadException {
        List<Codecooler> codecoolerList = new ArrayList<>();
        try (Connection connection = connectToDB()) {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM codecooler WHERE team_id = ?");
            pst.setInt(1, teamId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Codecooler codecooler = extractCodecoolerFromResultSet(rs);
                codecoolerList.add(codecooler);
            }
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get this codecooler");
        }
        return codecoolerList;
    }

    @Override
    public List<Codecooler> getCodecoolersByClassId(int classId) throws ReadException {
        return null;
    }

    @Override
    public int getCodecoolersCount() throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM codecooler");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access the database.");
        }
        throw new ReadException("Problem with data in database");
    }

    @Override
    public void clearCodecoolerTeamId(int id) throws ReadException {
        String query = "UPDATE codecooler SET team_id = NULL WHERE codecooler_id = ?";
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot delete this codecooler from the team!");
        }
    }

    @Override
    public void clearCodecoolerClassId(int id) throws ReadException {
        String query = "UPDATE codecooler SET class_id = NULL WHERE codecooler_id = ?";
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot delete this codecooler from the team!");
        }
    }

    private Codecooler extractCodecoolerFromResultSet(ResultSet rs) throws SQLException, ReadException {
        return new Codecooler.Builder()
                .withID(rs.getInt("codecooler_id"))
                .withName(rs.getString("name"))
                .withEmail(rs.getString("email"))
                .withPassword(rs.getString("password"))
                .withCity(rs.getString("city"))
                .withDateOfAdding(rs.getDate("date_of_adding"))
                .withPictureURL(rs.getString("picture_url"))
                .withClassId(rs.getInt("class_id"))
                .withTeamId(rs.getInt("team_id"))
                .withWallet(getWalletById(rs.getInt("wallet_id")))
                .build();
    }

    private Wallet getWalletById(int id) throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM wallet WHERE wallet_id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return extractWalletFromResultSet(rs);
            }
            throw new ReadException("Sorry, the wallet doesn't exist!");
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't get this wallet!");
        }
    }

    private Wallet extractWalletFromResultSet(ResultSet rs) throws SQLException {
        int walletId = rs.getInt("wallet_id");
        int coinsTotal = rs.getInt("coins_total");
        int coinsAvailable = rs.getInt("coins_available");

        return new Wallet(walletId, coinsTotal, coinsAvailable);
    }

    private int addNewWallet() throws ReadException {
        try (Connection connection = connectToDB()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO wallet (coins_total, coins_available) VALUES (0, 0) RETURNING wallet_id;");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()) {
                return rs.getInt(1);
            }
            throw new ReadException("Sorry, couldn't create wallet for the codecooler");
        } catch (SQLException e) {
            throw new ReadException("Sorry, couldn't create wallet for the codecooler");
        }
    }
}
