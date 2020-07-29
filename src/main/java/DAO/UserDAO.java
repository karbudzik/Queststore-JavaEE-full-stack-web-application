package DAO;

import model.CMSUser;
import exception.ReadException;

import java.sql.Date;
import java.util.List;

public interface UserDAO {
    void addUser(int ID, String name, String email,
                 String password, String city, Date dateOfAdding,
                 String pictureURL, boolean isAdmin) throws ReadException;

    void addUser(CMSUser user) throws ReadException;

    void editUser(int ID, CMSUser user) throws ReadException;

    void deleteUser(int ID) throws ReadException;

    void changeUserPassword(int userId, String newPassword) throws ReadException;

    List<CMSUser> getAllUsers() throws ReadException;

    List<CMSUser> getAllAdmins() throws ReadException;

    List<CMSUser> getAllMentors() throws ReadException;

    CMSUser getCMSUser(int ID) throws ReadException;

    CMSUser getCMSUser(String email, String password) throws ReadException;

    boolean checkUser(String email, String password) throws ReadException;

    int getAdminsCount() throws ReadException;

    int getMentorsCount() throws ReadException;
}
