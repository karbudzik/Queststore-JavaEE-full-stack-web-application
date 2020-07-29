package sort;

import exception.NoComparatorException;
import model.CMSUser;

import java.util.Comparator;

public class ComparatorUser  implements Comparing<CMSUser> {
        public Comparator<CMSUser> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        Comparator<CMSUser> comparator = null;
        switch(typeColumn){
            case NAME:
                comparator = Comparator.comparing(CMSUser::getName);
                break;
            case EMAIL:
                comparator = Comparator.comparing(CMSUser::getEmail);
                break;
            case CITY:
                comparator = Comparator.comparing(CMSUser::getCity);
                break;
            case DATE:
                comparator = Comparator.comparing(CMSUser::getDateOfAdding);
                break;
            default:
                throw new NoComparatorException("There is no comparator for a insert typeColumn.");
        }
        return comparator;
    }
}
