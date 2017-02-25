package org.sa.notification.services.endpoints;

import org.sa.notification.dao.models.NotificationModel;
import org.sa.notification.dao.models.NotificationStatus;
import org.sa.notification.services.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Arrays;

/**
 * Created by Vivek on 15-02-2017.
 */
@Component
public class SMSGatewayEndPoint {

    @Autowired
    private CallBackService callBackService;


    private final String BASE_URL="http://103.16.142.110/vendorsms/pushsms.aspx?user=SWARAJ&password=SWARAJ123" +
        "&msisdn={0}&sid=SWARAJ&msg={1}&fl=0&gwid=2";

    private final String PROMOTIONAL_URL="http://cloud.smsindiahub.in/vendorsms/pushsms.aspx?user=SWARAJ&password=" +
        "SWARAJ123&msisdn=91{0}&sid=SWARAJ&msg=Dear+{1}%2C+Thanks+for+becoming+member+of+Swaraj+Abhiyan.+Your+membership+no+is+TestMembership.+Your+user+id+is+TestUsername++and+your+password+is+TESTPAssword." +
        "+Kindly+login+at+https%3A%2F%2Fmember.swarajabhiyan.org%2F%23%21login+to+add+your+details.+Jai+Hind+Swaraj+Abhiyan&fl=0&gwid=2";



    public void smsEndPointSync(NotificationModel notificationModel){
        try{
           /* MessageFormat mf=new MessageFormat(PROMOTIONAL_URL);
            String data = mf.format(new Object[]{notificationModel.getJsonOption().getPhoneNumber(),notificationModel.getJsonOption().getFirstName()});
           */
            URL url = new URL(notificationModel.getNotificationMessage());
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String sResult="";
            while ((line = rd.readLine()) != null) {
                // Process line...
                sResult=sResult+line+" ";
            }
            rd.close();
            notificationModel.setNotificationMessage(sResult);
            notificationModel.setNotificationStatus(NotificationStatus.SENT);
        }catch (Exception ex){
            notificationModel.setNotificationStatus(NotificationStatus.FAILED);
            notificationModel.setNotificationMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void smsEndPoint(NotificationModel notificationModel) {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println( requestHeaders);
        HttpEntity entity = new HttpEntity("parameters",requestHeaders);
        MessageFormat mf = new MessageFormat(PROMOTIONAL_URL);
        Object[] args = {notificationModel.getJsonOption().getPhoneNumber()};
        String url=mf.format(args);
        System.out.println(url);
        ListenableFuture<ResponseEntity<String>> futureEntity = restTemplate
            .exchange(url, HttpMethod.GET, entity,
                String.class, "0");

        callBackService.setNotificationModel(notificationModel);
        futureEntity.addCallback(callBackService );
    }
}
