package org.sa.notification.dao.models;

import javax.persistence.*;

/**
 * Created by Vivek on 19-01-2017.
 */
@Entity
@Table(name = NotificationModel.TBL_NOTIFICATION)
public class NotificationModel extends AbstractPersistable {
    public static final String TBL_NOTIFICATION = "TBL_NOTIFICATION";

    @Column(name="USER_ID",nullable = false,length = 30)
    private String notificationTo;

    @Enumerated(EnumType.STRING)
    @Column(name="NOTIFICATION_STATUS",nullable = false)
    private NotificationStatus notificationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="NOTIFICATION_ACTION",nullable = false,length = 30)
    private NotificationAction notificationAction;

    @Column(name="NOTIFICATION_TYPE",nullable = false,length = 30)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name="NOTIFICATION_MESSAGE",length = 4000,nullable = false)
    private String notificationMessage;

    /** to capture the number of attempts **/
    @Column(name="ATTEMPT_COUNT",nullable = false)
    private Long attempts;

    @Column(name="OPTIONS",length = 1000)
    private String options;

    @Transient
    private JsonOption jsonOption;

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getAttempts() {
        return attempts;
    }

    public void setAttempts(Long attempts) {
        this.attempts = attempts;
    }


    public String getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(String notificationTo) {
        this.notificationTo = notificationTo;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public NotificationAction getNotificationAction() {
        return notificationAction;
    }

    public void setNotificationAction(NotificationAction notificationAction) {
        this.notificationAction = notificationAction;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }


    public void setJsonOption(JsonOption jsonOption) {
        this.jsonOption = jsonOption;
    }

    public JsonOption getJsonOption() {
        return jsonOption;
    }
}
