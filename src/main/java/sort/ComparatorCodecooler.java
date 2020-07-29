package sort;

import DAO.CodecoolerClassDAO;
import DAO.TeamDAO;
import exception.NoComparatorException;
import exception.ReadException;
import model.Codecooler;

import java.util.Comparator;

public class ComparatorCodecooler implements Comparing<Codecooler> {

    private final TeamDAO teamDAO;
    private final CodecoolerClassDAO classDAO;

    public ComparatorCodecooler(TeamDAO teamDAO, CodecoolerClassDAO classDAO) {
        this.teamDAO = teamDAO;
        this.classDAO = classDAO;
    }

    @Override
    public Comparator<Codecooler> getComparator(TypeColumn typeColumn) throws NoComparatorException {
        switch (typeColumn) {
            case NAME:
                return Comparator.comparing(Codecooler::getName);
            case EMAIL:
                return Comparator.comparing(Codecooler::getEmail);
            case CITY:
                return Comparator.comparing(Codecooler::getCity);
            case CLASS:
                return Comparator.comparing(
                        Codecooler::getClassId, (id1, id2) -> {
                            try {
                                String className1 = classDAO.getCodecoolerClassById(id1).getName();
                                String className2 = classDAO.getCodecoolerClassById(id2).getName();
                                return className1.compareTo(className2);
                            } catch (ReadException e) {
                                return id1.compareTo(id2);
                            }
                        }
                );
            case TEAM:
                return Comparator.comparing(
                        Codecooler::getTeamId, (id1, id2) -> {
                            try {
                                String teamName1 = teamDAO.getTeamById(id1).getName();
                                String teamName2 = teamDAO.getTeamById(id2).getName();
                                return teamName1.compareTo(teamName2);
                            } catch (ReadException e) {
                                return id1.compareTo(id2);
                            }
                        }
                );
            case DATE:
                return Comparator.comparing(Codecooler::getDateOfAdding);
        }
        throw new NoComparatorException("There is no comparator for a insert typeColumn.");
    }
}
