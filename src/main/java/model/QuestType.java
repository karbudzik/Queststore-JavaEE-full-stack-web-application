package model;

public enum QuestType {
    BASIC,
    EXTRA;

    public static QuestType getTypeByContent(String content) {
        if (content.toLowerCase().equals("basic")) {
            return QuestType.BASIC;
        } else {
            return QuestType.EXTRA;
        }
    }
}
