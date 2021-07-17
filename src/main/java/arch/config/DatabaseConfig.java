package arch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.username}")
    private String login;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .username(login)
                .password(password)
                .url("jdbc:oracle:thin:@localhost:1521:test")
                .build();
    }
}

