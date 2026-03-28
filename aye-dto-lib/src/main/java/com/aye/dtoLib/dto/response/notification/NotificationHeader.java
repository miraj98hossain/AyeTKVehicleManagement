package com.aye.dtoLib.dto.response.notification;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NotificationHeader {
    List<NotificationDashBoard> boards;
}
