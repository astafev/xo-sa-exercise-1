package com.company;

import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
@EnableJpaAuditing
public class SaExercise1Application {

    public static void main(String[] args) {
        SpringApplication.run(SaExercise1Application.class, args);
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(Project.class);
            config.exposeIdsFor(SdlcSystem.class);
        });
    }
}
