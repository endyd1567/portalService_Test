package portalService.test.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static portalService.test.connection.ConnectionConst.*;

public class HallaConnectionMaker implements ConnectionMaker{

    @Override
    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL_HALLA, USERNAME_HALLA, PASSWORD_HALLA);
        return con;
    }
}
