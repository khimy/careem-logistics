package org.sa.notification.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sa.notification.api.Notification;
import org.sa.notification.api.NotificationType;
import org.sa.notification.api.Options;
import org.sa.notification.dao.models.*;
import org.sa.notification.dao.repositories.NotificationRepository;
import org.sa.notification.exception.InvalidNotificationRequest;
import org.sa.notification.services.template.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

/**
 * Created by Vivek on 19-01-2017.
 */
@Service
public class NotificationService {

    private NotificationValidator notificationValidator=new NotificationValidator();

    private NotificationRepository notificationRepository;

    @Autowired
    private MessageGenerator messageGenerator;

    @Autowired
    private org.sa.notification.services.endpoints.SMSGatewayEndPoint SMSGatewayEndPoint;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void saveOrUpdateNotification(NotificationModel notificationModel){
        notificationRepository.save(notificationModel);
    }



    @Transactional
    public Notification generateNotification(Notification notification) throws JsonProcessingException {
        notificationValidator.validate(notification);
        NotificationModel notificationModel = toNotificationModel(notification);
        String message=messageGenerator.generateMessage(notificationModel);
        notificationModel.setNotificationMessage(message);
        increaseAttemptCount(notificationModel);
        saveOrUpdateNotification(notificationModel);
        SMSGatewayEndPoint.smsEndPointSync(notificationModel);
        notification.id=notificationModel.getId();
        return notification;
    }

    @Transactional
    public String resendNotification(Long notificationId) throws IOException {
        NotificationModel notificationModel=notificationRepository.findOne(notificationId);
        if(notificationModel==null){
            throw new InvalidNotificationRequest("The notification doesn't exist for id "+notificationId);
        }
        notificationModel.setJsonOption(new ObjectMapper().readValue(notificationModel.getOptions(),JsonOption.class));
        String message=messageGenerator.generateMessage(notificationModel);
        notificationModel.setNotificationMessage(message);
        increaseAttemptCount(notificationModel);
        saveOrUpdateNotification(notificationModel);
        SMSGatewayEndPoint.smsEndPoint(notificationModel);
        return "success";
    }


    public NotificationModel toNotificationModel(Notification notification) throws JsonProcessingException {
        NotificationModel notificationModel=new NotificationModel();
        notificationModel.setNotificationAction(NotificationAction.NO_ACTION);
        notificationModel.setNotificationStatus(NotificationStatus.PENDING);
        notificationModel.setNotificationType(toNotificationType(notification.notificationType));

        if(NotificationType.DONATION.equals(notification.notificationType)){
            notificationModel.setNotificationTo(String.valueOf(notification.donationNotification.donationId));
        }
        if(NotificationType.PASSWORD_CHANGE.equals(notification.notificationType)){
            notificationModel.setNotificationTo(String.valueOf(notification.donationNotification.agentId));
        }

        createJsonOption(notification, notificationModel);

        return notificationModel;
    }

    private void createJsonOption(Notification notification, NotificationModel notificationModel) throws JsonProcessingException {
        JsonOption jsonOption=new JsonOption();
        if(notification.options!=null){
            jsonOption.setNotificationMode(toNotificationMode(notification.options.get(Options.NOTIFICATION_MODE)));
        }
        jsonOption.setDonationAmount(Double.valueOf(notification.donationNotification.donationAmount));
        jsonOption.setDonationId(notification.donationNotification.donationId);
        jsonOption.setEmailId(notification.donationNotification.donorProfile.email);
        jsonOption.setFirstName(notification.donationNotification.donorProfile.name.first);
        jsonOption.setLastName(notification.donationNotification.donorProfile.name.last);
        jsonOption.setPhoneNumber(notification.donationNotification.donorProfile.mobile);
        jsonOption.setAgentId(notification.donationNotification.agentId);
        jsonOption.setGender(toGender(notification.donationNotification.donorProfile.gender));
        notificationModel.setOptions(new ObjectMapper().writeValueAsString(jsonOption));
        notificationModel.setJsonOption(jsonOption);
    }





    private Gender toGender(org.sa.notification.api.Gender gender) {
        Gender genderModel=Gender.MALE;

        switch (gender){
            case Male:
                genderModel= Gender.MALE;
                break;
            case Female:
                genderModel= Gender.FEMALE;
                break;
            case Other:
                genderModel= Gender.OTHER;
                break;
        }
        return genderModel;
    }


    private org.sa.notification.dao.models.NotificationMode toNotificationMode(Object o) {
        return org.sa.notification.dao.models.NotificationMode.SMS;
    }

    private org.sa.notification.dao.models.NotificationType toNotificationType(NotificationType notificationType) {
        org.sa.notification.dao.models.NotificationType temp= org.sa.notification.dao.models.NotificationType.DONATION;

        switch (notificationType){
            case DONATION:
                temp=org.sa.notification.dao.models.NotificationType.DONATION;
                break;
            case WELCOME_MESSAGE:
                temp=org.sa.notification.dao.models.NotificationType.WELCOME_MESSAGE;
                break;
            case PASSWORD_CHANGE:
                temp=org.sa.notification.dao.models.NotificationType.PASSWORD_CHANGE;
                break;
            case FAILURE_MESSAGE:
                temp=org.sa.notification.dao.models.NotificationType.FAILURE_MESSAGE;
                break;
        }

        return temp;
    }

    private void increaseAttemptCount(NotificationModel notificationModel){
        if(notificationModel.getAttempts()==null || notificationModel.getAttempts()==0L){
            notificationModel.setAttempts(1L);
        }else{
            notificationModel.setAttempts(notificationModel.getAttempts()+1);
        }
    }


}
