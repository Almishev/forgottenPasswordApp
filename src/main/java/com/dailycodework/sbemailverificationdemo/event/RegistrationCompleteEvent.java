package com.dailycodework.sbemailverificationdemo.event;

import com.dailycodework.sbemailverificationdemo.user.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private  User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
