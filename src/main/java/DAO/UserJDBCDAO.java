package DAO;

import exception.ConnectionException;
import model.CMSUser;
import exception.ReadException;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UserJDBCDAO implements UserDAO {
    private final String DBUrl;
    private final String DBUser;
    private final String DBPassword;
    private List<CMSUser> dicOfUsers;

    public UserJDBCDAO(String path) throws IOException {
        Properties prop = PropertiesReader.readProperties(path);
        DBUrl = prop.getProperty("db.url");
        DBUser = prop.getProperty("db.user");
        DBPassword = prop.getProperty("db.passwd");
        dicOfUsers = new ArrayList<>();
    }

    public UserJDBCDAO() throws ConnectionException {
        Properties prop = null;
        try {
            prop = PropertiesReader.readProperties("src/main/resources/database.properties");
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }
        DBUrl = prop.getProperty("db.url");
        DBUser = prop.getProperty("db.user");
        DBPassword = prop.getProperty("db.passwd");
        dicOfUsers = new ArrayList<>();
    }

    @Override
    public void addUser(int ID, String name, String email, String password, String city,
                        Date dateOfAdding, String pictureURL, boolean isAdmin) throws ReadException {
        String query = "INSERT INTO cms_user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DBUrl, DBUser, this.DBPassword);
             PreparedStatement pst = con.prepareStatement(query))
        {
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, city);
            pst.setDate(5, dateOfAdding);
            pst.setString(6, pictureURL);
            pst.setBoolean(7, isAdmin);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot insert user");
        }
    }

    @Override
    public void addUser(CMSUser user) throws ReadException {

        if(checkEmail(user.getEmail())){
            throw new ReadException("You cannot add a new user, because this e-mail is exist in a database");
        }

        String query = "INSERT INTO cms_user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DBUrl, this.DBUser, this.DBPassword);
             PreparedStatement pst = con.prepareStatement(query))
        {
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getCity());
            pst.setDate(5, user.getDateOfAdding());
            pst.setString(6, user.getPictureURL());
            pst.setBoolean(7, user.isAdmin());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot insert user" + ex);
        }
    }

    @Override
    public void editUser(int ID, CMSUser user) throws ReadException {
        String actualEmail = getCMSUser(ID).getEmail();
        if(checkEmail(user.getEmail()) && !actualEmail.equals(user.getEmail())){
            throw new ReadException("You cannot edit this user, because a provide e-mail is exist in a database");
        }

        String query = "UPDATE cms_user SET name = ?, email = ?, password = ?, city = ?, date_of_adding = ? ,picture_url = ?, is_admin = ? WHERE cms_user_id = ?";
        try (Connection con = DriverManager.getConnection(DBUrl, this.DBUser, this.DBPassword);
             PreparedStatement pst = con.prepareStatement(query))
        {
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getCity());
            pst.setDate(5, user.getDateOfAdding());
            pst.setString(6, user.getPictureURL());
            pst.setBoolean(7, user.isAdmin());
            pst.setInt(8, ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot insert user");
        }
    }

    @Override
    public void deleteUser(int ID) throws ReadException {
        try(Connection con = DriverManager.getConnection(DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("DELETE FROM cms_user WHERE cms_user_id = ?")){
            pst.setInt(1, ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ReadException("You cannot access to database");
        }
    }

    @Override
    public List<CMSUser> getAllUsers() throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user")) {
            ResultSet rs = pst.executeQuery();
            return fillListOfUsers(rs);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.");
        }
    }

    @Override
    public List<CMSUser> getAllAdmins() throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE is_admin='t'")) {
            ResultSet rs = pst.executeQuery();
            return fillListOfUsers(rs);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n" + ex);
        }
    }

    @Override
    public List<CMSUser> getAllMentors() throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE is_admin='f'")) {
            ResultSet rs = pst.executeQuery();
            return fillListOfUsers(rs);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n " + ex);
        }
    }

    @Override
    public CMSUser getCMSUser(String email, String password) throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE email = ? AND password = ?")) {
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return createNewUser(rs);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database. " + ex);
        }
    }

    @Override
    public CMSUser getCMSUser(int ID) throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE cms_user_id = ?")) {
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            return createNewUser(rs);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n " + ex);
        }
    }

    @Override
    public boolean checkUser(String email, String password) throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE email = ? AND password = ?")) {
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n " + ex);
        }
    }

    @Override
    public int getAdminsCount() throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM cms_user WHERE is_admin='t'")) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database. \n" + ex);
        }
        throw new ReadException("Problem with data in database.");
    }

    @Override
    public int getMentorsCount() throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM cms_user WHERE is_admin='f'")) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n " + ex);
        }
        throw new ReadException("Problem with data in database.");
    }

    public void changeUserPassword(int userId, String newPassword) throws ReadException {

        String query = "UPDATE cms_user SET password = ? WHERE cms_user_id = ?";
        try (Connection con = DriverManager.getConnection(DBUrl, this.DBUser, this.DBPassword);
             PreparedStatement pst = con.prepareStatement(query))
        {
            pst.setString(1, newPassword);
            pst.setInt(2, userId);
            pst.executeUpdate();

        } catch (SQLException ex) {
            throw new ReadException("You cannot update user. \n" + ex);
        }

    }

    private List<CMSUser> fillListOfUsers(ResultSet rs) throws ReadException {
        dicOfUsers.clear();
        try {
            while (rs.next()) {
                CMSUser user = new CMSUser.Builder()
                        .userID(rs.getInt(1))
                        .userName(rs.getString(2))
                        .userEmail(rs.getString(3))
                        .userPassword(rs.getString(4))
                        .userCity(rs.getString(5))
                        .userDate(rs.getDate(6))
                        .userPicture(rs.getString(7))
                        .userRole(rs.getBoolean(8))
                        .build();

                dicOfUsers.add(user);
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot import list of users. " + ex);
        }
        return dicOfUsers;
    }

    private CMSUser createNewUser(ResultSet rs) throws SQLException, ReadException {
        if(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            String password = rs.getString(4);
            String city = rs.getString(5);
            Date date = rs.getDate(6);
            String url = rs.getString(7);
            boolean role = rs.getBoolean(8);

            return new CMSUser.Builder()
                    .userID(id)
                    .userName(name)
                    .userEmail(email)
                    .userPassword(password)
                    .userCity(city)
                    .userDate(date)
                    .userPicture(url)
                    .userRole(role)
                    .build();
        }
        else{
            throw new ReadException("This user doesn't exist!");
        }
    }

    private boolean checkEmail(String email) throws ReadException {
        try(Connection con = DriverManager.getConnection(this.DBUrl, this.DBUser, this.DBPassword);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM cms_user WHERE email = ?")) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to database.\n " + ex);
        }
    }
}
