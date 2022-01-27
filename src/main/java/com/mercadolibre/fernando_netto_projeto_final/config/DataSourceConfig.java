package com.mercadolibre.fernando_netto_projeto_final.config;

import com.fury.api.FuryUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;


/**
 * Data source production configuration. Uses FuryUtils to get the username and password.
 * Configuration for other profiles are in application-${profile}.properties
 * Production config is located here because we must use FuryUtils to get the endpoint and password of the db.
 */
@Configuration
@Profile({"prod"})
//"dev", "beta", "stage", "production", "production-bigq", "beta-bigq", "dev-bigq", "jobs-beta", "jobs-production"})
public class DataSourceConfig {

    @Value("${db.endpoint}")
    private String dbEndpoint;
    @Value("${db.name}")
    private String db;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${db.connection-timeout}")
    private long connectionTimeout;
    @Value("${db.minimum-idle}")
    private int minimumIdle;
    @Value("${db.maximum-pool-size}")
    private int maxPoolSize;
    @Value("${db.idle-timeout}")
    private long idleTimeout;
    @Value("${db.max-lifetime}")
    private long maxLifeTime;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource getDataSource() throws Exception {
        String endPoint = FuryUtils.getEnv(dbEndpoint);
        String passEnv = FuryUtils.getEnv(password);
        String dbUrl = String.format(
                //"jdbc:mysql://%s/%s?useSSL=false",
                "jdbc:mysql://%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
                endPoint,
                db);

        HikariDataSource hikariDataSource = dataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .driverClassName(driverClassName)
                .url(dbUrl)
                .username(username)
                .password(passEnv)
                .build();

        configureSpecificDatasourceProperties(hikariDataSource);
        return hikariDataSource;
    }

    void configureSpecificDatasourceProperties(HikariDataSource hikariDataSource) {
        hikariDataSource.setConnectionTimeout(connectionTimeout);
        hikariDataSource.setMinimumIdle(minimumIdle);
        hikariDataSource.setMaximumPoolSize(maxPoolSize);
        hikariDataSource.setIdleTimeout(idleTimeout);
        hikariDataSource.setMaxLifetime(maxLifeTime);
    }
}