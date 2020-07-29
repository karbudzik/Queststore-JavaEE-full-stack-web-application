package sort;

import exception.NoComparatorException;
import model.Artifact;

import java.util.Comparator;

public class ComparatorArtifact implements Comparing<Artifact>{
    public Comparator<Artifact> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(Artifact::getName);
            case DESCRIPTION:
                return Comparator.comparing(Artifact::getDescription);
            case VALUE:
                return Comparator.comparing(Artifact::getValue);
            case TYPE:
                return Comparator.comparing(Artifact::getType);
            case DATE:
                return Comparator.comparing(Artifact::getDateOfAdding);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}
