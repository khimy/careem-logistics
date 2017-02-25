package ca.customer.services;

import ca.customer.exception.InvalidCustomer;
import org.sa.notification.api.Notification;
import org.sa.notification.api.NotificationType;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vivek on 23-02-2017.
 *
 * The content of Notification Request will vary
 * depending on the notification type.
 * This needs to be validated in length.
 * Notification Validator will insure that.
 *
 */
public class NotificationValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String ID_PATTERN = "[0-9]+";
    String STRING_PATTERN = "[a-zA-Z]+";
    String MOBILE_PATTERN = "{10}";


    public void validate(Notification notification) {
        if(NotificationType.DONATION.equals(notification.notificationType)){
            donationIdValidation(notification);
            mobileEmailValidation(notification);
        }
        if(NotificationType.PASSWORD_CHANGE.equals(notification.notificationType)){
            agentIdValidation(notification);
            mobileEmailValidation(notification);
        }
    }

    public void donationIdValidation(Notification notification) {
        if(notification.donationNotification==null ||notification.donationNotification.donationId==null){
            throw new InvalidCustomer("The donation id is mandatory for notification of type "+notification.notificationType);
        }
    }

    public void agentIdValidation(Notification notification) {
        if(notification.donationNotification==null || notification.donationNotification.agentId==null
            ||notification.donationNotification.agentId.isEmpty()){
            throw new InvalidCustomer("The agent id is mandatory for notification of type "+notification.notificationType);
        }
    }


    private void mobileEmailValidation(Notification notification) {
        if(notification.donationNotification.donorProfile==null){
            throw new InvalidCustomer("Invalid Mobile/Email for notification ");
        }
        String email=notification.donationNotification.donorProfile.email;
        String mobile=notification.donationNotification.donorProfile.mobile;

        if(StringUtils.isEmpty(email) && StringUtils.isEmpty(mobile)){
            throw new InvalidCustomer("Invalid Mobile/Email for notification ");
        }

        if(!StringUtils.isEmpty(mobile)){
            mobileValidation(mobile);
        }

        if(!StringUtils.isEmpty(email)){
            emailValidation(email);
        }
    }

    public void mobileValidation(String mobile){
        if (mobile.length()!=10) {
            throw new InvalidCustomer("Invalid Mobile for notification ");
        }
    }


    public void emailValidation(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidCustomer("Invalid Email for notification ");
        }
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }


}
