package com.personal.extractorsitesapi.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class ResourceBuildEvent extends ApplicationEvent {
    private HttpServletResponse response;
    private Long code;

    public ResourceBuildEvent(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }
}
