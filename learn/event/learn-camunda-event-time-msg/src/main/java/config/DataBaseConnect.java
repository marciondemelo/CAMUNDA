package config;

import javax.sql.DataSource;

import config.entity.CredentialDataBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConnect {
    private final CredentialDataBase credentialDataBase;

    public DataBaseConnect(CredentialDataBase credentialDataBase) {
        this.credentialDataBase = credentialDataBase;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(credentialDataBase.getClassName());
        dataSource.setUrl(credentialDataBase.getUrl());
        return dataSource;
    }
}
