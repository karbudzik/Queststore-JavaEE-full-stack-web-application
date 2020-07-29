package sort;

import exception.NoComparatorException;
import model.CodecoolerClass;

import java.util.Comparator;

public class ComparatorClass implements Comparing<CodecoolerClass> {

    @Override
    public Comparator<CodecoolerClass> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(CodecoolerClass::getName);
            case CITY:
                return Comparator.comparing(CodecoolerClass::getCity);
            case START_DATE:
                return Comparator.comparing(CodecoolerClass::getStartDate);
            case END_DATE:
                return Comparator.comparing(CodecoolerClass::getEndDate);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}
