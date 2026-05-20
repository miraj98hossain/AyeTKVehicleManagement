package com.aye.backendservice.applicationEvent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserAccessTemplateCacheSyncEvent extends ApplicationEvent {
    private final Integer userAccessTempId;

    public UserAccessTemplateCacheSyncEvent(Object source, Integer userAccessTempId) {
        super(source);
        this.userAccessTempId = userAccessTempId;
    }

}