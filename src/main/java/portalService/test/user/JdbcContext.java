package portalService.test.user;

import portalService.test.strategy.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {

    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User jdbcContextForFind(StatementStrategy statementStrategy) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);
            rs = psmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));

            }
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void jdbcContextForInsert(User user , StatementStrategy statementStrategy) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);

            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);
            psmt.executeUpdate();
        } finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User find(String sql , Object[] params) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
            return psmt;
        };
        return jdbcContextForFind(statementStrategy);
    }

    public void insert(User user , String sql , Object[] params) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
            return psmt;
        };
        jdbcContextForInsert(user,statementStrategy);
    }

    public void update(String sql , Object[] params) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            PreparedStatement psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
            return psmt;
        };
        jdbcContextForUpdate(statementStrategy);
    }



}
