package ca.customer.api;


import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class Notification {
    public Long id;
    @NotNull(message = "Notification Type can not be null")
    public NotificationType notificationType;
    public Map<Options,Object> options=new HashMap<Options,Object>();
    public DonationNotification donationNotification;

    public static class DonationNotification extends Notification{
        public DonorProfile donorProfile;
        public Long donationId;
        public Double donationAmount;
        public String agentId;
    }
}
