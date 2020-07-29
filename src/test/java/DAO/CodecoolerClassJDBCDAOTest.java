package DAO;

import exception.ReadException;
import model.CodecoolerClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CodecoolerClassJDBCDAOTest {

    @Mock
    private PGSimpleDataSource mockDataSource;

    @Mock
    private Connection mockCon;

    @Mock
    private PreparedStatement mockPst;

    @Mock
    private ResultSet mockRs;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Try create and upload to database new class.")
    public void shouldAddNewClassWithoutExceptions() throws SQLException, ReadException {
        //given:
        when(mockDataSource.getConnection()).thenReturn(mockCon);
        when(mockCon.prepareStatement("INSERT INTO class VALUES (DEFAULT, ?,?,?,?)")).thenReturn(mockPst);

        //when:
        CodecoolerClassJDBCDAO dao = new CodecoolerClassJDBCDAO(mockDataSource);
        dao.addCodecoolerClass(new CodecoolerClass.Builder()
                .withName("SuperKlasa")
                .withCity("Mielec")
                .withStartDate(new java.sql.Date(213423653423L))
                .withEndDate(new java.sql.Date(3456754345654L))
                .build());

        //then:
        verify(mockPst, times(2)).setString(anyInt(), anyString());
        verify(mockPst, times(2)).setDate(anyInt(), any(Date.class));
        verify(mockPst, times(1)).execute();
    }
    
}