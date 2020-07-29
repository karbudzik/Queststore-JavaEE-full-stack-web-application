package sort;

import exception.NoComparatorException;
import model.Level;

import java.util.Comparator;

public class ComparatorLevel implements Comparing<Level> {
    public Comparator<Level> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(Level::getName);
            case DESCRIPTION:
                return Comparator.comparing(Level::getDescription);
            case DATE:
                return Comparator.comparing(Level::getDateOfAdding);
            case COINS:
                return Comparator.comparing(Level::getPrice);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}
