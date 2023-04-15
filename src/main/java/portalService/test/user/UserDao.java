package portalService.test.user;


import portalService.test.connection.ConnectionConst;

import java.sql.*;

import static portalService.test.connection.ConnectionConst.*;

public abstract class UserDao {

    public User findById(Long id) throws SQLException {
        Connection con = getConnection();
        PreparedStatement psmt = con.prepareStatement("select id,name,password from userinfo where id = ?");
        psmt.setLong(1,id);
        ResultSet rs = psmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        psmt.close();
        con.close();

        return user;
    }

    public void insert(User user) throws SQLException {
        Connection con = getConnection();
        PreparedStatement psmt = con.prepareStatement("insert into userinfo(name,password) values(?,?) " , Statement.RETURN_GENERATED_KEYS);
        psmt.setString(1, user.getName());
        psmt.setString(2,user.getPassword());
        psmt.executeUpdate();

        ResultSet rs = psmt.getGeneratedKeys();
        rs.next();
        user.setId(rs.getLong(1));

        rs.close();
        psmt.close();
        con.close();

    }

    abstract public Connection getConnection() throws SQLException;


}
