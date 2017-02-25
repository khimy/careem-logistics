package org.sa.notification.exception;

/**
 * @author vivek.
 * If the notification request is empty
 */
public class InvalidNotificationRequest extends RuntimeException{

    public InvalidNotificationRequest(String message) {
        super(message);
    }

}
