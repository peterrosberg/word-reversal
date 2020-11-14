package com.pepp.wordreversal

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.net.URI
import java.net.URISyntaxException
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @Profile("stage")
    fun postgresDataSource(): DataSource? {
        val databaseUrl = System.getenv("DATABASE_URL")
        log.info("Initializing PostgreSQL database: $databaseUrl");

        try {
            val dbUri = URI(databaseUrl)

            val username = dbUri.userInfo.split(":")[0];
            val password = dbUri.userInfo.split(":")[1];
            val dbUrl = "jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}";

            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(dbUrl)
                    .username(username)
                    .password(password)
                    .build()

        } catch (e: URISyntaxException) {
            log.error("Invalid DATABASE_URL: $databaseUrl", e)
            return null
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(DataSourceConfig::class.java)
    }
}