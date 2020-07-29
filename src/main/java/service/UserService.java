package service;

import DAO.UserDAO;
import DAO.UserJDBCDAO;
import sort.TypeColumn;
import exception.ConnectionException;
import exception.NoComparatorException;
import exception.ReadException;
import model.CMSUser;
import sort.ComparatorUser;
import sort.Comparing;
import sort.SortItems;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


public class UserService {
    private UserDAO dao;
    private List<CMSUser> allUsers;

    public UserService() throws ConnectionException {
        try {
            dao = new UserJDBCDAO("src/main/resources/database.properties");
        } catch (IOException ex) {
            throw new ConnectionException(ex.getMessage());
        }
    }

    public List<CMSUser> getAllUsers(String userType, String sortBy, Boolean order) throws ReadException {

        allUsers = getListFromDatabase(userType);
        if (order != null && sortBy != null) {
            try {
                allUsers = sortList(allUsers, order, sortBy);
            } catch (NoComparatorException e) {
                //here should be the message for user, that the user cannot sorting the table
                e.printStackTrace();
            }
        }
        return allUsers;
    }

    private List<CMSUser> getListFromDatabase(String type) throws ReadException {
        if (type.equals("admin")) {
            allUsers = dao.getAllAdmins();
        } else {
            allUsers = dao.getAllMentors();
        }
        return allUsers;
    }

    private List<CMSUser> sortList(List<CMSUser> allUsers, boolean order, String sortBy) throws NoComparatorException {
        Comparing<CMSUser> comparing = new ComparatorUser();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<CMSUser> comparator = comparing.getComparator(typeColumn);
        SortItems<CMSUser> sortItems = new SortItems<>(allUsers, comparator);
        return sortItems.sort(order);
    }

}
