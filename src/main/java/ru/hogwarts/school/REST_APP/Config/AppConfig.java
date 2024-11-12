package ru.hogwarts.school.REST_APP.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Long defaultId() {
        return 1L;
    }

    @Bean
    public String defaultName() {
        return "Default Name";
    }

    @Bean
    public int defaultAge() {
        return 15;
    }
}
