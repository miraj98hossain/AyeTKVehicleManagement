package com.aye.backendservice.applicationEvent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserAccessCacheGenerateEvent extends ApplicationEvent {


    public UserAccessCacheGenerateEvent(Object source) {
        super(source);
    }

}