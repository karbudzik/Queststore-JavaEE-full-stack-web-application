package sort;

import exception.NoComparatorException;
import model.Team;

import java.util.Comparator;

public class ComparatorTeam implements Comparing<Team> {
    public Comparator<Team> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(Team::getName);
            case CITY:
                return Comparator.comparing(Team::getCity);
            case DATE:
                return Comparator.comparing(Team::getStartDate);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}
