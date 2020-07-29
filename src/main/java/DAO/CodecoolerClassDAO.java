package DAO;

import exception.ReadException;
import model.CodecoolerClass;

import java.util.List;

public interface CodecoolerClassDAO {
    void addCodecoolerClass(CodecoolerClass codecoolerClass) throws ReadException;

    void deleteCodecoolerClass(int id) throws ReadException;

    List<CodecoolerClass> getAllCodecoolerClasss() throws ReadException;

    CodecoolerClass getCodecoolerClassById(int id) throws ReadException;

    void editCodecoolerClass(int id, CodecoolerClass codecoolerClass) throws ReadException;

    int getCodecoolerClassesCount() throws ReadException;
}
