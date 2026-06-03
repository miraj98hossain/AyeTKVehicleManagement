package com.aye.backendservice.applicationEvent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserMenusCacheSyncEvent extends ApplicationEvent {
    private final Integer userMenuId;

    public UserMenusCacheSyncEvent(Object source, Integer userMenuId) {
        super(source);
        this.userMenuId = userMenuId;
    }
}