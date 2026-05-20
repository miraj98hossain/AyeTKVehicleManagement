package com.aye.backendservice.applicationEvent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserAccessCacheSyncEvent extends ApplicationEvent {
    private final Integer userId;
    private final Integer userAccessTempId;

    public UserAccessCacheSyncEvent(Object source, Integer userId, Integer userAccessTempId) {
        super(source);
        this.userId = userId;
        this.userAccessTempId = userAccessTempId;
    }

}