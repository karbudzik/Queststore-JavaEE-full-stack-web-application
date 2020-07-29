package model;

import java.sql.Date;

public class Quest {
    private int ID;
    private String name;
    private String description;
    private int value;
    private QuestType type;
    private Date dateOfAdding;
    private String pictureUrl;

    public Quest() {

    }

    public Quest(int ID, String name, String description, int value, String type, Date dateOfAdding, String pictureUrl) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.value = value;
        this.type = QuestType.getTypeByContent(type);
        this.dateOfAdding = dateOfAdding;
        this.pictureUrl = pictureUrl;
    }

    public Quest(String name, String description, int value, String type, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.type = QuestType.getTypeByContent(type);
        this.pictureUrl = pictureUrl;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public QuestType getType() {
        return type;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = QuestType.getTypeByContent(type);
    }
}
