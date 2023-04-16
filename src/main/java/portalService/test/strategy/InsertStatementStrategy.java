package portalService.test.strategy;

import portalService.test.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementStrategy implements StatementStrategy{

    private User user;

    public InsertStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        PreparedStatement psmt = con.prepareStatement("insert into userinfo(name,password) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        psmt.setString(1, user.getName());
        psmt.setString(2,user.getPassword());
        return psmt;
    }
}
