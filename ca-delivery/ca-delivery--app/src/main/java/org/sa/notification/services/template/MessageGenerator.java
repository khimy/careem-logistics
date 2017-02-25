package org.sa.notification.services.template;

import org.sa.notification.dao.models.NotificationModel;
import org.sa.notification.services.TemplateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 15-02-2017.
 */
@Component
public class MessageGenerator {

    private TemplateProcessor templateProcessor;

    @Autowired
    public MessageGenerator(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    public String generateMessage(NotificationModel notificationModel) {
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",notificationModel.getJsonOption().getPhoneNumber());
        map.put("name",notificationModel.getJsonOption().getFirstName());
        return templateProcessor.getMessage(TemplateProcessor.SMS_TEMPLATE+TemplateProcessor.TEMPLATE_EXT,map);
    }
}
