package com.company.resourceapi.controllers;

import com.company.SaExercise1ApplicationTest;
import com.company.resourceapi.entities.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetProjectCorrectId() throws Exception {
        Project testProject = SaExercise1ApplicationTest.getTestProject();
        getProjectAndTest(testProject);
    }

    @Test
    public void testGetProjectWrongId() throws Exception {
        this.mockMvc.perform(get("/api/v2/projects/100"))
                .andExpect(status().isNotFound());
    }

    private void getProjectAndTest(Project project) throws Exception {
        this.mockMvc.perform(get("/api/v2/projects/" + project.getId()))
                .andExpect(status().isOk())
                .andExpect(matchAll(
                        jsonPath("id").value(project.getId()),
                        jsonPath("name").value(project.getName()),
                        jsonPath("sdlcSystem.id").value(project.getSdlcSystem().getId())
                ));
    }
}