package portalService.test.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindStatementStrategy implements StatementStrategy{

    private Long id;

    public FindStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        PreparedStatement psmt = con.prepareStatement("select id,name,password from userinfo where id=? ");
        psmt.setLong(1,id);
        return psmt;
    }
}
