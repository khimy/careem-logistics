package org.sa.notification.services;

import org.sa.notification.dao.models.NotificationModel;
import org.sa.notification.dao.models.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by Vivek on 19-02-2017.
 */
@Component
public class CallBackService<ResponseEntity> implements ListenableFutureCallback<ResponseEntity> {

    @Autowired
    private NotificationService notificationService;

    private NotificationModel notificationModel;

    @Override
    public void onFailure(Throwable ex) {
        notificationModel.setNotificationStatus(NotificationStatus.FAILED);
        notificationModel.setNotificationMessage(ex.getMessage());
    }
    @Override
    public void onSuccess(ResponseEntity result) {
        try{
            notificationModel.setNotificationStatus(NotificationStatus.SENT);
            notificationService.saveOrUpdateNotification(notificationModel);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void setNotificationModel(NotificationModel notificationModel) {
        this.notificationModel = notificationModel;
    }

    public NotificationModel getNotificationModel() {
        return notificationModel;
    }
}
