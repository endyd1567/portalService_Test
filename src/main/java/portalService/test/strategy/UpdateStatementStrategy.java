package portalService.test.strategy;

import portalService.test.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementStrategy implements StatementStrategy{

    private User user;

    public UpdateStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        PreparedStatement psmt = con.prepareStatement("update userinfo set name=? , password=? where id=? ");
        psmt.setString(1,user.getName());
        psmt.setString(2,user.getPassword());
        psmt.setLong(3,user.getId());
        return psmt;
    }
}
