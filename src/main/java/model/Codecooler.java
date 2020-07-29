package model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Codecooler {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String city;
    private Date dateOfAdding;
    private String pictureURL;
    private Wallet wallet;
    private Integer classId;
    private Integer teamId;
    private Map<Quest, Date> questAchieved;
    private Map<Artifact, Date> artifactBought;

    public Codecooler(){

    }

    public static final class Builder{
        private int id;
        private String name;
        private String email;
        private String password;
        private String city;
        private Date dateOfAdding;
        private String pictureURL;
        private Wallet wallet;
        private Integer classId;
        private Integer teamId;
        private Map<Quest, Date> questAchieved;
        private Map<Artifact, Date> artifactBought;

        public Builder withID(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withPassword(String password){
            this.password = password;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withDateOfAdding(Date date){
            this.dateOfAdding = date;
            return this;
        }

        public Builder withPictureURL(String pictureURL){
            this.pictureURL = pictureURL;
            return this;
        }

        public Builder withWallet(Wallet wallet){
            this.wallet = wallet;
            return this;
        }

        public Builder withTeamId(Integer teamId){
            this.teamId = teamId;
            return this;
        }

        public Builder withClassId(Integer classId){
            this.classId = classId;
            return this;
        }

        public Builder withQuestAchieved(Map<Quest, Date> questAchieved){
            this.questAchieved = questAchieved;
            return this;
        }

        public Builder withArtifactBought(Map<Artifact, Date> artifactBought){
            this.artifactBought = artifactBought;
            return this;
        }

        public Codecooler build(){
            Codecooler newCodecooler = new Codecooler();

            newCodecooler.id = this.id;
            newCodecooler.city = this.city;
            newCodecooler.name = this.name;
            newCodecooler.artifactBought = this.artifactBought;
            newCodecooler.classId = this.classId;
            newCodecooler.dateOfAdding = this.dateOfAdding;
            newCodecooler.email = this.email;
            newCodecooler.password = this.password;
            newCodecooler.pictureURL = this.pictureURL;
            newCodecooler.questAchieved = this.questAchieved;
            newCodecooler.teamId = this.teamId;
            newCodecooler.wallet = this.wallet;
            return newCodecooler;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public void setDateOfAdding(Date dateOfAdding) {
        this.dateOfAdding = dateOfAdding;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Map<Quest, Date> getQuestAchieved() {
        return questAchieved;
    }

    public void setQuestAchieved(Map<Quest, Date> questAchieved) {
        this.questAchieved = questAchieved;
    }

    public Map<Artifact, Date> getArtifactBought() {
        return artifactBought;
    }

    public void setArtifactBought(Map<Artifact, Date> artifactBought) {
        this.artifactBought = artifactBought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codecooler that = (Codecooler) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
