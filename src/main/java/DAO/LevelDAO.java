package DAO;

import exception.ReadException;
import model.Level;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface LevelDAO {

    void insertNewLevel(Level level) throws  ReadException;
    void updateLevel(Level level, int levelId) throws  ReadException;
    void  deleteLevel(int levelId) throws  ReadException;
    List<Level> getLevelsList() throws  ReadException;

    int getLevelsCount() throws ReadException;

    Level getLevelToUpdate(int levelId) throws  ReadException;

}
