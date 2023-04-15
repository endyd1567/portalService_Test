package portalService.test.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static portalService.test.connection.ConnectionConst.*;

public class JejuUserDao extends UserDao{
    @Override
    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL_JEJU, USERNAME_JEJU, PASSWORD_JEJU);
        return con;
    }
}
