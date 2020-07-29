package service;

import DAO.UserJDBCDAO;
import exception.ReadException;
import model.CMSUser;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserJDBCDAO dao;

    @BeforeEach
    public void init() throws ReadException {
        MockitoAnnotations.initMocks(this);
        given(dao.getAllAdmins()).willReturn(prepareMockData());
    }

    @Test
    void should_sort_users_by_name() throws ReadException {
        //given;
        String userType = "admin";
        String sortBy = "name";
        Boolean order = true;
        //when:
        List<CMSUser> sortedListByName = userService.getAllUsers(userType, sortBy, order);
        //then;
        Assertions.assertEquals("Karolina", sortedListByName.get(0).getName());
        Assertions.assertEquals("Michal", sortedListByName.get(1).getName());
        Assertions.assertEquals("Rafal", sortedListByName.get(2).getName());
        verify(dao, times(1)).getAllAdmins();
    }

    @Test
    void should_throw_read_exception() throws ReadException {
        //given:
        String userType = "admin";
        String sortBy = "name";
        Boolean order = true;
        //when:
        given(dao.getAllAdmins()).willThrow(new ReadException(" "));
        //then:
        Assertions.assertThrows(ReadException.class, ()->userService.getAllUsers(userType, sortBy, order));

    }

    private List<CMSUser> prepareMockData() {
        CMSUser user1 = new CMSUser.Builder()
                .userName("Michal")
                .build();

        CMSUser user2 = new CMSUser.Builder()
                .userName("Rafal")
                .build();

        CMSUser user3 = new CMSUser.Builder()
                .userName("Karolina")
                .build();

        return Arrays.asList(user1, user2, user3);
    }
}