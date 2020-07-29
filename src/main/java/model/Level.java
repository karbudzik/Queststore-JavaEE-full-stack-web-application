package model;

import java.sql.Date;

public class Level {

    private int Id;
    private String name;
    private String description;
    private int price;
    private Date dateOfAdding;
    private String pictureUrl;

    public Level(int id, String name, String description, int price, Date dateOfAdding, String pictureUrl) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dateOfAdding = dateOfAdding;
        this.pictureUrl = pictureUrl;
    }

    public Level(String name, String description, int price, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public void setDateOfAdding(Date dateOfAdding) {
        this.dateOfAdding = dateOfAdding;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Level{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateOfAdding=" + dateOfAdding +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
