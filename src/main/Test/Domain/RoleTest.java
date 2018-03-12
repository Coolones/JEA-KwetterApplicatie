package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoleTest {

    Role role;

    @Before
    public void setUp() throws Exception {
        role = new Role("Administrator", true, true, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRole() {
        assertEquals("Administrator", role.getRole());
    }

    @Test
    public void isCanDeleteOthers() {
        assertTrue(role.isCanDeleteOthers());
    }

    @Test
    public void isCanGiveRights() {
        assertTrue(role.isCanGiveRights());
    }

    @Test
    public void isCanBlockUsers() {
        assertTrue(role.isCanBlockUsers());
    }
}