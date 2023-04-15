package portalService.test.user;

import portalService.test.connection.ConnectionMaker;
import portalService.test.connection.JejuConnectionMaker;

public class DaoFactory {
    public UserDao getUserDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    private ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
