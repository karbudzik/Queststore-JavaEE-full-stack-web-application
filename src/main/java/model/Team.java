package model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class Team {
    private int id;
    private String name;
    private String city;
    private Date startDate;
    private List<Codecooler> members;
    private Map<Artifact, Date> artifactBought;


    public Team(){

    }

    public static final class Builder{
        private int id;
        private String name;
        private String city;
        private Date startDate;
        private List<Codecooler> members;
        private Map<Artifact, Date> artifactBought;

        public Builder withID(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withStartDate(Date date){
            this.startDate = date;
            return this;
        }

        public Builder withMembers(List<Codecooler> members){
            this.members = members;
            return this;
        }

        public Builder withArtifactsBought(Map<Artifact, Date> artifactBought){
            this.artifactBought = artifactBought;
            return this;
        }

        public Team build(){
            Team newTeam = new Team();

            newTeam.id = this.id;
            newTeam.city = this.city;
            newTeam.name = this.name;
            newTeam.startDate = this.startDate;
            newTeam.members = this.members;
            newTeam.artifactBought = this.artifactBought;
            return newTeam;
        }
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Codecooler> getMembers() {
        return members;
    }

    public void setMembers(List<Codecooler> members) {
        this.members = members;
    }

    public Map<Artifact, Date> getArtifactBought() {
        return artifactBought;
    }

    public void setArtifactBought(Map<Artifact, Date> artifactBought) {
        this.artifactBought = artifactBought;
    }

    public Integer calculateTotalTeamCoins(){
        return null;
    }
}
