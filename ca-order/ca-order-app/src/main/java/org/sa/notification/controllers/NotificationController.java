package org.sa.notification.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sa.notification.api.Notification;
import org.sa.notification.exception.InvalidNotificationRequest;
import org.sa.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/notification/")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService=notificationService;
    }

    @RequestMapping(path = "notify", method = RequestMethod.POST)
    public Notification notify(@Validated @RequestBody Notification notification) throws JsonProcessingException {
        return notificationService.generateNotification(notification);
    }

    @RequestMapping(path = "notify/{notificationId}", method = RequestMethod.GET)
    public String resendNotification(@RequestParam Long notificationId) throws IOException {
        if(notificationId==null){
            throw new InvalidNotificationRequest("The notification id is invalid");
        }
        return notificationService.resendNotification(notificationId);
    }


}
