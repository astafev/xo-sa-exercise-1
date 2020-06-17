package com.company.codeserver.controllers;

import com.company.SaExercise1ApplicationTest;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SdlcSystemRestControllerTest {
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
        this.mockMvc.perform(get("/api/v1/sdlc-systems/100/projects/100"))
                .andExpect(status().isOk())
                .andExpect(content().bytes(new byte[0]));
    }

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setId(null);
        project.setExternalId("testCreateProject");
        project.setName("testCreateProject");
        project.setSdlcSystem(new SdlcSystem() {{
            setId(1L);
        }});
        this.mockMvc.perform(post("/api/v1/sdlc-systems/1/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(
                        matchAll(
                                jsonPath("name").value(project.getName()),
                                jsonPath("id").exists())
                );
    }

    @Test
    public void testUpdateProject() throws Exception {
        Project project = SaExercise1ApplicationTest.getTestProject();
        project.setExternalId("testUpdateProject");
        project.setName("testUpdateProject");
        project.setId(4L);
        this.mockMvc.perform(put("/api/v1/sdlc-systems/" +
                project.getSdlcSystem().getId() + "/projects/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(matchAll(
                        jsonPath("id").value(project.getId()),
                        jsonPath("name").value(project.getName()),
                        jsonPath("sdlcSystem.id").value(project.getSdlcSystem().getId())
                ));
        getProjectAndTest(project);
    }

    @Test
    public void testUpdateProjectWrongId() throws Exception {
        Project project = SaExercise1ApplicationTest.getTestProject();
        this.mockMvc.perform(put("/api/v1/sdlc-systems/100/projects/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isNoContent())
                .andExpect(content().bytes(new byte[0]));
        getProjectAndTest(project);
    }

    private void getProjectAndTest(Project project) throws Exception {
        this.mockMvc.perform(get("/api/v1/sdlc-systems/" +
                project.getSdlcSystem().getId() + "/projects/" + project.getId()))
                .andExpect(status().isOk())
                .andExpect(matchAll(
                        jsonPath("id").value(project.getId()),
                        jsonPath("name").value(project.getName()),
                        jsonPath("sdlcSystem.id").value(project.getSdlcSystem().getId())
                ));
    }

}