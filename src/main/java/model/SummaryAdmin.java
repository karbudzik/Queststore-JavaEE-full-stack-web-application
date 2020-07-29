package model;

public class SummaryAdmin extends SummaryMentor {
    private int adminsCount;
    private int mentorsCount;
    private int levelsCount;

    public SummaryAdmin(int studentsCount, int classesCount, int teamsCount, int questsCount, int artifactsCount, int adminsCount, int mentorsCount, int levelsCount) {
        super(studentsCount, classesCount, teamsCount, questsCount, artifactsCount);
        this.adminsCount = adminsCount;
        this.mentorsCount = mentorsCount;
        this.levelsCount = levelsCount;
    }

    public int getAdminsCount() {
        return adminsCount;
    }

    public int getMentorsCount() {
        return mentorsCount;
    }

    public int getLevelsCount() {
        return levelsCount;
    }
}
