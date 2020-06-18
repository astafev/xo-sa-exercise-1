package com.company.resourceapi;

import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RepositoryEventHandler
public class CustomRepositoryRestConfigurer extends AbstractRepositoryEventListener<Project> {
    protected void onBeforeLinkSave(Project parent, Object linked) {
        log.info("asdf");
    }
    @HandleBeforeLinkSave
    public void beforeLinkSaveEvent(Project deviceLoop, SdlcSystem persistentBag) {
        log.info("asdf");
    }
    protected void onBeforeSave(Project entity) {
        log.info("asdf");
    }
}
