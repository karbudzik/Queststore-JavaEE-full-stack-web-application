package sort;

public enum TypeColumn {
    NAME, EMAIL, CITY, DATE, DESCRIPTION, CLASS, TEAM, VALUE, TYPE, START_DATE, END_DATE, COINS;

    public static TypeColumn returnType(String type){
        type = type.toLowerCase();
        TypeColumn typeColumn;
        switch(type){
            case "name":
               typeColumn = NAME;
               break;
            case "email":
                typeColumn = EMAIL;
                break;
            case "city":
                typeColumn = CITY;
                break;
            case "date":
                typeColumn = DATE;
                break;
            case "description":
                typeColumn = DESCRIPTION;
                break;
            case "class":
                typeColumn = CLASS;
                break;
            case "team":
                typeColumn = TEAM;
                break;
            case "value":
                typeColumn = VALUE;
                break;
            case "type":
                typeColumn = TYPE;
                break;
            case "start-date":
                typeColumn = START_DATE;
                break;
            case "end-date":
                typeColumn = END_DATE;
                break;
            case "coins":
                typeColumn = COINS;
                break;
            default:
                throw new TypeNotPresentException(type,
                        new Throwable("That type doesn't exist in TypeColumn enum"));
        }
        return typeColumn;
    }
}
