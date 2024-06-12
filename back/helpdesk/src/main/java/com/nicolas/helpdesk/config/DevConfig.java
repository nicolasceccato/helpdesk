package com.nicolas.helpdesk.config;

import com.nicolas.helpdesk.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @PostConstruct
    public boolean initDB() {
        if (value.equals("create")) {
            this.dbService.initDB();
        }
        return false;
    }

}
