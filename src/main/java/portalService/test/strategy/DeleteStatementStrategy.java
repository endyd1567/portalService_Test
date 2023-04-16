package portalService.test.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy{

    private Long id;

    public DeleteStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        PreparedStatement psmt = con.prepareStatement("delete from userinfo where id =? ") ;
        psmt.setLong(1,id);
        return psmt;
    }
}
