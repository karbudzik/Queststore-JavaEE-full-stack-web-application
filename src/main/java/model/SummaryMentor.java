package model;

public class SummaryMentor {

    protected int studentsCount;
    protected int classesCount;
    protected int teamsCount;
    protected int questsCount;
    protected int artifactsCount;

    public SummaryMentor(int studentsCount, int classesCount, int teamsCount, int questsCount, int artifactsCount) {
        this.studentsCount = studentsCount;
        this.classesCount = classesCount;
        this.teamsCount = teamsCount;
        this.questsCount = questsCount;
        this.artifactsCount = artifactsCount;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public int getTeamsCount() {
        return teamsCount;
    }

    public int getQuestsCount() {
        return questsCount;
    }

    public int getArtifactsCount() {
        return artifactsCount;
    }
}
