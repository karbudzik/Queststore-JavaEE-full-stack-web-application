package DAO;

import exception.ConnectionException;
import exception.ReadException;
import model.Codecooler;
import model.CodecoolerClass;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CodecoolerClassJDBCDAO implements CodecoolerClassDAO {
    private PGSimpleDataSource dataSource;

    public CodecoolerClassJDBCDAO(PGSimpleDataSource ds) {
        this.dataSource = ds;
    }

    public CodecoolerClassJDBCDAO() {
        try {
            dataSource = DataSourceReader.getDataSource("src/main/resources/database.properties");
        } catch (IOException e) {
            throw new ConnectionException("Sorry, couldn't connect to database");
        }
    }

    @Override
    public void addCodecoolerClass(CodecoolerClass codecoolerClass) throws ReadException {
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("INSERT INTO class VALUES (DEFAULT, ?,?,?,?)")) {
            pst.setString(1, codecoolerClass.getName());
            pst.setString(2, codecoolerClass.getCity());
            pst.setDate(3, codecoolerClass.getStartDate());
            pst.setDate(4, codecoolerClass.getEndDate());

            pst.execute();
        } catch (SQLException ex) {
            throw new ReadException("You cannot add class. " + ex.getMessage());
        }
    }

    @Override
    public void deleteCodecoolerClass(int id) throws ReadException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = this.dataSource.getConnection();
            con.setAutoCommit(false);
            deleteCodecoolerAssignToClasses(con, id);
            pst = con.prepareStatement("DELETE FROM class WHERE class_id = ?");
            pst.setInt(1, id);
            pst.execute();
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throw new ReadException("The transaction has failed. Rollback is not possible. ");
            }
            throw new ReadException("You cannot delete this class. " + ex.getMessage());
        } finally {
            try {
                pst.close();
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new ReadException("The problem with close connection. " + ex.getMessage());
            }
        }
    }

    @Override
    public List<CodecoolerClass> getAllCodecoolerClasss() throws ReadException {
        List<CodecoolerClass> classes = new ArrayList<>();
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM class")) {
            ResultSet rs = pst.executeQuery();
            createListOfClasses(rs, classes);
        } catch (SQLException ex) {
            throw new ReadException("You cannot access to classes. " + ex.getMessage());
        }
        return classes;
    }

    @Override
    public CodecoolerClass getCodecoolerClassById(int id) throws ReadException {
        Connection con = null;
        try {
            con = this.dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement("SELECT * FROM class WHERE class_id = ?");
            pst.setInt(1, id);
            CodecoolerClass newClass = createNewClass(pst, con);
            con.commit();

            return newClass;
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                throw new ReadException("Problem with rollback. " + e);
            }
            throw new ReadException("You cannot access to this class. " + ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new ReadException("A problem with close connection. " + ex);
            }
        }
    }

    @Override
    public void editCodecoolerClass(int id, CodecoolerClass codecoolerClass) throws ReadException {
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "UPDATE class SET name = ?, city = ?, start_date = ?, end_date = ? WHERE class_id = ?")) {
            pst.setString(1, codecoolerClass.getName());
            pst.setString(2, codecoolerClass.getCity());
            pst.setDate(3, codecoolerClass.getStartDate());
            pst.setDate(4, codecoolerClass.getEndDate());
            pst.setInt(5, id);

            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new ReadException("You cannot edit this class. " + ex.getMessage());
        }
    }

    @Override
    public int getCodecoolerClassesCount() throws ReadException {
        try (Connection con = this.dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM class")) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ReadException("You cannot access the database.");
        }
        throw new ReadException("Problem with data in database");
    }

    private CodecoolerClass createNewClass(PreparedStatement pst, Connection con) throws SQLException, ReadException {
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String city = rs.getString(3);
            Date startDate = rs.getDate(4);
            Date endDate = rs.getDate(5);

            List<Codecooler> codecoolerList = addMembersToClass(con, id);

            return new CodecoolerClass.Builder()
                    .withID(id)
                    .withName(name)
                    .withCity(city)
                    .withStartDate(startDate)
                    .withEndDate(endDate)
                    .withMembers(codecoolerList)
                    .build();
        } else {
            throw new ReadException("This class doesn't exist!");
        }
    }

    private List<CodecoolerClass> createListOfClasses(ResultSet rs, List<CodecoolerClass> listOfClasses) throws SQLException, ReadException {
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String city = rs.getString(3);
            Date startDate = rs.getDate(4);
            Date endDate = rs.getDate(5);

            listOfClasses.add(new CodecoolerClass.Builder()
                    .withID(id)
                    .withName(name)
                    .withCity(city)
                    .withStartDate(startDate)
                    .withEndDate(endDate)
                    .build());
        }

        return listOfClasses;
    }

    private void deleteCodecoolerAssignToClasses(Connection con, int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("DELETE FROM codecooler WHERE class_id=?");
        pst.setInt(1, id);
        pst.execute();
    }

    private List<Codecooler> addMembersToClass(Connection con, int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT * FROM codecooler WHERE class_id=?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        List<Codecooler> codecoolerList = new LinkedList<>();

        while (rs.next()) {
            Integer idCodecooler = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);

            Codecooler codecooler = new Codecooler.Builder()
                    .withID(idCodecooler)
                    .withName(name)
                    .withEmail(email)
                    .build();

            codecoolerList.add(codecooler);
        }

        return codecoolerList;
    }
}
