package sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortItems<T> {
    private List<T> allItems;
    private Comparator<T> comparator;

    public SortItems(List<T> allItems, Comparator<T> comparator){
        this.allItems = allItems;
        this.comparator = comparator;
    }

    public List<T> sort(boolean isAscending){
        if(!isAscending){
            comparator = comparator.reversed();
        }
        return allItems.stream().sorted(comparator).collect(Collectors.toList());
    }
}
