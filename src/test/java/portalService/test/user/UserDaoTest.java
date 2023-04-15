package portalService.test.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    public void get() throws SQLException {
        Long id = 1l;
        String name = "umdu";
        String password = "1234";

        UserDao userDao = new UserDao();
        User user = userDao.findById(id);

        assertThat(user.getId()).isEqualTo(1l);
        assertThat(user.getName()).isEqualTo("umdu");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

}