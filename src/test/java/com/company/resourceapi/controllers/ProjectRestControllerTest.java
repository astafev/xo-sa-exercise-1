package com.company.resourceapi.controllers;

import com.company.SaExercise1ApplicationTest;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectRestControllerTest {
    public static final String API_V_2_PROJECTS = "/api/v2/projects/";
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
        this.mockMvc.perform(get(API_V_2_PROJECTS + "100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setExternalId("testCreateProject_v2");
        project.setName("testCreateProject_v2");
        project.setSdlcSystem(new SdlcSystem() {{
            setId(1L);
        }});
        this.mockMvc.perform(post(API_V_2_PROJECTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated());
    }

    private void getProjectAndTest(Project project) throws Exception {
        this.mockMvc.perform(get(API_V_2_PROJECTS + project.getId()))
                .andExpect(status().isOk())
                .andExpect(matchAll(
                        jsonPath("id").value(project.getId()),
                        jsonPath("name").value(project.getName()),
                        jsonPath("sdlcSystem.id").value(project.getSdlcSystem().getId())
                ));
    }
}