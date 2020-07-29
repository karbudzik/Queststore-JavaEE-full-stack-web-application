package sort;

import exception.NoComparatorException;

public interface Comparing<T>{
    java.util.Comparator<T> getComparator(TypeColumn typeColumn) throws NoComparatorException;
}
