package sort;

import exception.NoComparatorException;
import model.Quest;

import java.util.Comparator;

public class ComparatorQuest implements Comparing<Quest> {

    @Override
    public Comparator<Quest> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(Quest::getName);
            case DESCRIPTION:
                return Comparator.comparing(Quest::getDescription);
            case VALUE:
                return Comparator.comparing(Quest::getValue);
            case TYPE:
                return Comparator.comparing(Quest::getType);
            case DATE:
                return Comparator.comparing(Quest::getDateOfAdding);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}