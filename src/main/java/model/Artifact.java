package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artifact {
    private int id;
    private String name;
    private String description;
    private int value;
    private String type;
    private Date dateOfAdding;
    private String pictureUrl;
    private boolean isUsed;

    public Artifact(String name, String description, int value, String type) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.type = type;
        this.isUsed = false;
    }

    public Artifact(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt(1);
            this.name = resultSet.getString(2);
            this.description = resultSet.getString(3);
            this.value = resultSet.getInt(4);
            this.type = resultSet.getString(5);
            this.dateOfAdding = resultSet.getDate(6);
            this.pictureUrl = resultSet.getString(7);
            this.isUsed = false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Artifact(String name, String description, int value, String type, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.type = type;
        this.pictureUrl = pictureUrl;
    }

    public Artifact() {
    }

    public static final class Builder {
        private int id;
        private String name;
        private String description;
        private int value;
        private String type;
        private Date dateOfAdding;
        private String pictureUrl;
        private boolean isUsed = false;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder dateOfAdding(Date dateOfAdding) {
            this.dateOfAdding = dateOfAdding;
            return this;
        }

        public Builder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder isUsed(boolean isUsed) {
            this.isUsed = isUsed;
            return this;
        }

        public Artifact build() {
            Artifact artifact = new Artifact();
            artifact.id = this.id;
            artifact.name = this.name;
            artifact.description = this.description;
            artifact.value = this.value;
            artifact.type = this.type;
            artifact.dateOfAdding = this.dateOfAdding;
            artifact.pictureUrl = this.pictureUrl;
            artifact.isUsed = this.isUsed;
            return artifact;
        }
    }

    public int getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String isUsedtoString() {
        return (isUsed) ? "Used" : "Not Used";
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
