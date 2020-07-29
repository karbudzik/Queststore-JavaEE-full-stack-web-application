package model;

import java.sql.Date;
import java.util.List;

public class CodecoolerClass {
    private int id;
    private String name;
    private String city;
    private Date startDate;
    private Date endDate;
    private List<Codecooler> members;


    public CodecoolerClass(){

    }

    public static final class Builder{
        private int id;
        private String name;
        private String city;
        private Date startDate;
        private Date endDate;
        private List<Codecooler> members;

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

        public Builder withEndDate(Date date){
            this.endDate = date;
            return this;
        }

        public Builder withMembers(List<Codecooler> members){
            this.members = members;
            return this;
        }

        public CodecoolerClass build(){
            CodecoolerClass newCodecoolerClass = new CodecoolerClass();

            newCodecoolerClass.id = this.id;
            newCodecoolerClass.city = this.city;
            newCodecoolerClass.name = this.name;
            newCodecoolerClass.startDate = this.startDate;
            newCodecoolerClass.endDate = this.endDate;
            newCodecoolerClass.members = this.members;
            return newCodecoolerClass;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Codecooler> getMembers() {
        return members;
    }

    public void setMembers(List<Codecooler> members) {
        this.members = members;
    }

    @Override
    public String toString(){
        return String.format("ID = %d, name = %s, city = %s, day of start = %s, day of end = %s"
                ,id, name, city, startDate, endDate);
    }
}
