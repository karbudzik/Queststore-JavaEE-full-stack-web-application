package service;

import DAO.ArtifactDAO;

import exception.ReadException;
import model.Artifact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtifactServiceTest {

    private Artifact artifactInitiallyNotUsed;
    private Artifact artifactInitiallyUsed;
    private List<Artifact> artifactsList;

    @InjectMocks
    ArtifactService artifactService;

    @Mock
    ArtifactDAO artifactDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        artifactInitiallyNotUsed = new Artifact.Builder()
                .id(66)
                .isUsed(false)
                .build();
        artifactInitiallyUsed = new Artifact.Builder()
                .id(88)
                .isUsed(true)
                .build();
        artifactsList = new ArrayList<>(Arrays.asList(artifactInitiallyNotUsed, artifactInitiallyUsed));
    }

    @Test
    public void should_throw_IllegalArgumentException_when_empty_list() {
        // given
        String[] emptyArray = new String[0];
        List<Artifact> emptyArtifactsList = new ArrayList<>();

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            artifactService.callArtifactUsageChange(emptyArray, emptyArtifactsList);
        });
    }

    @Test
    public void should_throw_IllegalArgumentException_when_one_argument_is_null() {
        // given
        String[] notNullArray = new String[5];
        List<Artifact> notNullArtifactsList = artifactsList;

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            artifactService.callArtifactUsageChange(notNullArray, null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            artifactService.callArtifactUsageChange(null, notNullArtifactsList);
        });
    }

    @Test
    public void should_throw_IllegalArgumentException_when_arguments_have_different_size() {
        // given
        String[] booleansArrayWithFourArguments = {"USED", "NOT USED", "USED", "NOT USED"};
        List<Artifact> artifactsListWithTwoElements = artifactsList;

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            artifactService.callArtifactUsageChange(booleansArrayWithFourArguments, artifactsListWithTwoElements);
        });
    }

    @Test
    public void should_change_artifacts_isUsed_property_when_provided_value_is_different() throws ReadException {
        // given
        String[] booleansArray = {"USED", "NOT USED"};

        // when
        artifactService.callArtifactUsageChange(booleansArray, artifactsList);

        // then
        Assertions.assertTrue(artifactInitiallyNotUsed.isUsed());
        Assertions.assertFalse(artifactInitiallyUsed.isUsed());
        verify(artifactDAO, times(2)).markIfArtifactUsed(anyInt(), anyBoolean());
    }

    @Test
    public void should_not_change_artifacts_isUsed_property_when_provided_value_is_the_same() throws ReadException {
        // given
        String[] booleansArray = {"NOT USED", "USED"};

        // when
        artifactService.callArtifactUsageChange(booleansArray, artifactsList);

        // then
        Assertions.assertFalse(artifactInitiallyNotUsed.isUsed());
        Assertions.assertTrue(artifactInitiallyUsed.isUsed());
        verify(artifactDAO, times(0)).markIfArtifactUsed(anyInt(), anyBoolean());
    }
}