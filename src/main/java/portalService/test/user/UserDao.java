package portalService.test.user;


import portalService.test.connection.ConnectionConst;
import portalService.test.connection.ConnectionMaker;
import portalService.test.strategy.*;

import javax.sql.DataSource;
import java.sql.*;

import static portalService.test.connection.ConnectionConst.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws SQLException {
        StatementStrategy statementStrategy = new FindStatementStrategy(id);
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }


    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user,statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}

