package service;

import DAO.ArtifactDAO;
import exception.NoComparatorException;
import exception.ReadException;
import model.Artifact;
import model.Team;
import sort.*;

import java.util.Comparator;
import java.util.List;

public class ArtifactService {
    private final ArtifactDAO artifactDAO;

    public ArtifactService(ArtifactDAO artifactDAO) {
        this.artifactDAO = artifactDAO;
    }

    public List<Artifact> callArtifactUsageChange(String[] booleansArray, List<Artifact> artifactsList) throws ReadException {
        int count = 0;
        if (booleansArray == null || artifactsList == null || booleansArray.length == 0 || booleansArray.length != artifactsList.size()) {
            throw new IllegalArgumentException();
        }
        while (count < artifactsList.size()) {
            boolean isUsed = booleansArray[count].toUpperCase().trim().equals("USED");
            Artifact artifact = artifactsList.get(count);
            if (isUsed != artifact.isUsed()) {
                artifactDAO.markIfArtifactUsed(artifact.getId(), isUsed);
                artifact.setUsed(isUsed);
            }
            count++;
        }
        return artifactsList;
    }

    public List<Artifact> getAllArtifacts(String sortBy, Boolean order) throws ReadException {
        List<Artifact> allArtifacts = artifactDAO.getAllArtifacts();
        if (order != null && sortBy != null) {
            try {
                allArtifacts = sortList(allArtifacts, order, sortBy);
            } catch (NoComparatorException e) {
                throw new ReadException(e.getMessage());
            }
        }
        return allArtifacts;
    }

    private List<Artifact> sortList(List<Artifact> allArtifacts, boolean order, String sortBy) throws NoComparatorException {
        Comparing<Artifact> comparing = new ComparatorArtifact();
        TypeColumn typeColumn = TypeColumn.returnType(sortBy);
        Comparator<Artifact> comparator = comparing.getComparator(typeColumn);
        SortItems<Artifact> sortItems = new SortItems<>(allArtifacts, comparator);

        return sortItems.sort(order);
    }
}
