package com.company;

import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
public class SaExercise1ApplicationTest {

    public static Project getTestProject() {
        Project project = new Project();
        project.setId(1L);
        project.setExternalId("SAMPLEPROJECT");
        project.setName("Sample Project");
        project.setSdlcSystem(new SdlcSystem() {{
            setId(1L);
        }});
        return project;
    }

    @Test
    void contextLoads() {
    }
}
