package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CMSUserTest {

    @Test
    @DisplayName("It should return Admin as String")
    public void shouldReturnAdminRoleAsString() {
        //given
        CMSUser user = new CMSUser.Builder()
                .userRole(true)
                .build();
        String expected = "Admin";
        //when
        String result = user.getRole();
        //then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("It should return Mentor as String")
    public void shouldReturnMentorRoleAsString() {
        // given
        CMSUser user = new CMSUser.Builder()
                .userRole(false)
                .build();
        String expected = "Mentor";
        //when
        String result = user.getRole();
        //then
        assertEquals(expected, result);
    }
}