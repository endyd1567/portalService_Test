package portalService.test.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import portalService.test.connection.HallaConnectionMaker;
import portalService.test.connection.JejuConnectionMaker;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    public void get() throws SQLException {
        Long id = 1l;
        String name = "umdu";
        String password = "1234";

        JejuConnectionMaker jejuConnectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(jejuConnectionMaker);
        User user = userDao.findById(id);

        assertThat(user.getId()).isEqualTo(1l);
        assertThat(user.getName()).isEqualTo("umdu");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    public void insert() throws SQLException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1234");

        JejuConnectionMaker jejuConnectionMaker = new JejuConnectionMaker();
        UserDao userDao = new UserDao(jejuConnectionMaker);
        userDao.insert(user);

        assertThat(user.getId()).isGreaterThan(1l);

        User insertedUser = userDao.findById(user.getId());

        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        assertThat(insertedUser.getName()).isEqualTo(user.getName());
        assertThat(insertedUser.getPassword()).isEqualTo(user.getPassword());


    }

    @Test
    public void getForHalla() throws SQLException {
        Long id = 1l;
        String name = "umdu";
        String password = "1234";

        HallaConnectionMaker hallaConnectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(hallaConnectionMaker);
        User user = userDao.findById(id);

        assertThat(user.getId()).isEqualTo(1l);
        assertThat(user.getName()).isEqualTo("umdu");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    public void insertForHalla() throws SQLException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1234");

        HallaConnectionMaker hallaConnectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(hallaConnectionMaker);
        userDao.insert(user);

        assertThat(user.getId()).isGreaterThan(1l);

        User insertedUser = userDao.findById(user.getId());

        assertThat(insertedUser.getId()).isEqualTo(user.getId());
        assertThat(insertedUser.getName()).isEqualTo(user.getName());
        assertThat(insertedUser.getPassword()).isEqualTo(user.getPassword());


    }

}