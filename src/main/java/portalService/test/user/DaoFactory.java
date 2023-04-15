package portalService.test.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portalService.test.connection.ConnectionMaker;
import portalService.test.connection.JejuConnectionMaker;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
