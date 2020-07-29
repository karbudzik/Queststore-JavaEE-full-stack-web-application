package DAO;

import model.Quest;
import exception.*;

import java.util.List;

public interface QuestDAO {
    void insertQuest(Quest quest) throws ReadException;

    List<Quest> getAllQuests() throws ReadException;

    void deleteQuest(int id) throws ReadException;

    Quest getQuestById(int id) throws ReadException;

    void updateQuest(int ID, Quest quest) throws ReadException;

    int getQuestsCount() throws ReadException;
}
