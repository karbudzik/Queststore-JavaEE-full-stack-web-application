package DAO;

import exception.ReadException;
import model.Codecooler;

import java.util.List;

public interface CodecoolerDAO {
    void addCodecooler(Codecooler codecooler) throws ReadException;

    void deleteCodecooler(int id) throws ReadException;

    List<Codecooler> getAllCodecoolers() throws ReadException;

    Codecooler getCodecoolerById(int id) throws ReadException;

    Codecooler getCodecooler(String email, String password) throws ReadException;

    void editCodecooler(int id, Codecooler codecooler) throws ReadException;

    boolean checkCodecooler(String email, String password) throws ReadException;

    List<Codecooler> getCodecoolersByTeamId(int teamId) throws ReadException;

    List<Codecooler> getCodecoolersByClassId(int classId) throws ReadException;

    int getCodecoolersCount() throws ReadException;

    void clearCodecoolerTeamId(int id) throws ReadException;

    void clearCodecoolerClassId(int id) throws ReadException;
}
