package com.personal.extractorsitesapi.event.listener;

import com.personal.extractorsitesapi.event.ResourceBuildEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.applet.AppletEvent;
import sun.applet.AppletListener;

import java.net.URI;

@Component
public class ResourceBuildListener implements ApplicationListener<ResourceBuildEvent> {

    @Override
    public void onApplicationEvent(ResourceBuildEvent resourceBuildEvent) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(resourceBuildEvent.getCode()).toUri();
        resourceBuildEvent.getResponse().setHeader("Location", uri.toASCIIString());
    }
}
