package com.company;

import com.company.resourceapi.CustomRepositoryRestConfigurer;
import com.company.resourceapi.ExceptionHandlerHelper;
import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import com.company.resourceapi.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableJpaAuditing
public class SaExercise1Application {

    public static void main(String[] args) {
        SpringApplication.run(SaExercise1Application.class, args);
    }
    /*@Bean
    public CustomRepositoryRestConfigurer helper() {
        return new CustomRepositoryRestConfigurer();
    }*/

    @Bean
    public JsonDeserializer<Project> sdlcDbBackedDeserializer(
            @Qualifier("sdlcSystemRepository") CrudRepository<SdlcSystem, Long> repository) {
        return new StdDeserializer<Project>(Project.class) {
            @Override
            public Project deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                final Project project = new Project();
//                project.setSdlcSystem(new SdlcSystem());
//                project.getSdlcSystem().setId(1L);
                return project;
            }
        };
    }
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(JsonDeserializer<Project> sdlcDbBackedDeserializer) {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Project.class);
                config.exposeIdsFor(SdlcSystem.class);
            }

            @Override
            public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
                objectMapper.registerModule(new SimpleModule("MyCustomModule") {
                    @Override
                    public void setupModule(SetupContext context) {
                        SimpleSerializers serializers = new SimpleSerializers();
                        SimpleDeserializers deserializers = new SimpleDeserializers();

//                        serializers.addSerializer(Project.class, new MyEntitySerializer());
//                        final JsonDeserializer<? extends JsonNode> deserializer =
//                                JsonNodeDeserializer.getDeserializer(Project.class);
                        deserializers.addDeserializer(Project.class, sdlcDbBackedDeserializer);

//                        context.addSerializers(serializers);
                        context.addDeserializers(deserializers);
                    }
                });
            }
        };
    }
}


