package portalService.test.user;


import portalService.test.connection.ConnectionConst;
import portalService.test.connection.ConnectionMaker;

import javax.sql.DataSource;
import java.sql.*;

import static portalService.test.connection.ConnectionConst.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws SQLException {

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        User user;

        try {
            con = dataSource.getConnection();
            psmt = con.prepareStatement("select id,name,password from userinfo where id = ?");
            psmt.setLong(1,id);
            rs = psmt.executeQuery();
            rs.next();

            user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        } finally {
            try {
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return user;
    }

    public void insert(User user) throws SQLException {

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            psmt = con.prepareStatement("insert into userinfo(name,password) values(?,?) " , Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1,user.getName());
            psmt.setString(2,user.getPassword());
            psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
        } finally {
            try {
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        }
    }

