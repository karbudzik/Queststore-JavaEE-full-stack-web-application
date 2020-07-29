package model;

import java.sql.Date;

public class CMSUser {
    private int ID;
    private String name;
    private String email;
    private String password;
    private String city;
    private Date dateOfAdding;
    private String pictureURL;
    private boolean isAdmin;

    public CMSUser(int ID, String name, String email,
                   String password, String city, Date dateOfAdding,
                   String pictureURL, boolean isAdmin) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.dateOfAdding = dateOfAdding;
        this.pictureURL = pictureURL;
        this.isAdmin = isAdmin;
    }

    public CMSUser(){

    }

    public static final class Builder {
        private int userID;
        private String userName;
        private String email;
        private String password;
        private String city;
        private Date dateOfAdding;
        private String pictureURL;
        private boolean isAdmin;

        public Builder userID(int userID) {
            this.userID = userID;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.email = userEmail;
            return this;
        }

        public Builder userPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder userCity(String city) {
            this.city = city;
            return this;
        }

        public Builder userDate(Date date){
            this.dateOfAdding = date;
            return this;
        }

        public Builder userPicture(String url){
            this.pictureURL = url;
            return this;
        }

        public Builder userRole(boolean isAdmin){
            this.isAdmin = isAdmin;
            return this;
        }

        public CMSUser build() {

            CMSUser user = new CMSUser();
            user.ID = this.userID;
            user.name = this.userName;
            user.email = this.email;
            user.city = this.city;
            user.isAdmin = this.isAdmin;
            user.password = this.password;
            user.dateOfAdding = this.dateOfAdding;
            user.pictureURL = this.pictureURL;
            return user;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getRole() {
        if(isAdmin){
            return "Admin";
        }else{
            return "Mentor";
        }
    }

    @Override
    public String toString(){
        String date = dateOfAdding.toString();
        String role = this.getRole();
        return String.format("ID=%d name-%s email-%s password-%s city-%s Date-%s URL of picture - %s Role - %s",
                ID, name, email, password, city, date, pictureURL, role);
    }
}
