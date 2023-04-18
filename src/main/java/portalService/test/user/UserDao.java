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
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement("select id,name,password from userinfo where id = ?");
            psmt.setLong(1, id);
            return psmt;
        };
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }


    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement("insert into userinfo(name,password) values(?,?)"
            ,Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPassword());
            return psmt;
        };
        jdbcContext.jdbcContextForInsert(user,statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement("update userinfo set name=? , password=? where id=? ");
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPassword());
            psmt.setLong(3,user.getId());
            return psmt;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement("delete from userinfo where id = ?");
            psmt.setLong(1, id);
            return psmt;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}

