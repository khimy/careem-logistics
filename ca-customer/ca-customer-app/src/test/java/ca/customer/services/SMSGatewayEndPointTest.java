package ca.customer.services;

import ca.customer.services.endpoints.SMSGatewayEndPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sa.notification.dao.models.JsonOption;
import ca.customer.dao.models.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by Vivek on 15-02-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SMSGatewayEndPointTest {
    private final String PROMOTIONAL_URL="http://cloud.smsindiahub.in/vendorsms/pushsms.aspx?user=SWARAJ&password=SWARAJ123&msisdn=918951152580&sid=SWARAJ&msg=Dear+Neeraj%2C+Thanks+for+becoming+member+of+Swaraj+Abhiyan.+Your+membership+no+is+TestMembership.+Your+user+id+is+TestUsername++and+your+password+is+TESTPAssword.+Kindly+login+at+https%3A%2F%2Fmember.swarajabhiyan.org%2F%23%21login+to+add+your+details.+Jai+Hind+Swaraj+Abhiyan&fl=0&gwid=2";
    private final String PROMOTIONAL_URL1="http://cloud.smsindiahub.in/vendorsms/pushsms.aspx?";
    private final String PROMOTIONAL_URL2="user=SWARAJ&password=SWARAJ123&msisdn=918951152580&sid=SWARAJ&msg=Dear+Neeraj%2C+Thanks+for+becoming+member+of+Swaraj+Abhiyan.+Your+membership+no+is+TestMembership.+Your+user+id+is+TestUsername++and+your+password+is+TESTPAssword.+Kindly+login+at+https%3A%2F%2Fmember.swarajabhiyan.org%2F%23%21login+to+add+your+details.+Jai+Hind+Swaraj+Abhiyan&fl=0&gwid=2";

    private ca.customer.services.endpoints.SMSGatewayEndPoint SMSGatewayEndPoint;
    private EntityModel entityModel;

    @Before
    public void setUp(){
        entityModel =new EntityModel();
        entityModel.setNotificationMessage("ABCD");
        JsonOption jsonOption=new JsonOption();
        jsonOption.setPhoneNumber("8951152580");
        entityModel.setJsonOption(jsonOption);

    }

    @Test
    public void sendSms() {
        try {
            // Construct data
            String user = "username=" + URLEncoder.encode("vivek081141@gmail.com", "UTF-8");
            String hash = "&hash=" + URLEncoder.encode("India@01", "UTF-8");
            String message = "&message=" + URLEncoder.encode("This is your message", "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode("918951152580", "UTF-8");

            // Send data
            String data = "http://api.textlocal.in/send/?" + user + hash + numbers + message + sender;
            URL url = new URL(data);
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

        } catch (Exception e) {
            System.out.println("Error SMS "+e);
        }
    }

    @Test
    public void sendSmsSA_valid() throws IOException {
        // Construct data
        String user = "username=" + URLEncoder.encode("vivek081141@gmail.com", "UTF-8");
        String hash = "&hash=" + URLEncoder.encode("India@01", "UTF-8");
        String message = "&message=" + URLEncoder.encode("This is your message", "UTF-8");
        String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
        String numbers = "&numbers=" + URLEncoder.encode("918951152580", "UTF-8");

        // Send data
        String data = PROMOTIONAL_URL;
        URL url = new URL(data);
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
    }

    @Test
    public void testGetUserAsyncWithCallback_valid() throws UnsupportedEncodingException {
        SMSGatewayEndPoint =new SMSGatewayEndPoint();
        System.out.println(PROMOTIONAL_URL);


        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> reqentity = new HttpEntity<Object>(headers);
        //String url=URLEncoder.encode(PROMOTIONAL_URL);
        //ResponseEntity<String> resp = rest.exchange(url, HttpMethod.GET, reqentity, String.class);
       //  System.out.println(resp);


        final String oUrl = "http://myhost/name/{name}";
        final URI expanded = new UriTemplate(PROMOTIONAL_URL).expand(PROMOTIONAL_URL2);
        final String fUrl = URLDecoder.decode(expanded.toString(), "UTF-8");
        rest.getForObject(fUrl, Object.class);
    }

    @Test
    public void testGetUserAsyncWithCallback_invalid() {
        JsonOption jsonOption=new JsonOption();
        jsonOption.setPhoneNumber("");
        entityModel.setJsonOption(jsonOption);
        SMSGatewayEndPoint = new SMSGatewayEndPoint();
        SMSGatewayEndPoint.smsEndPoint(entityModel);
    }
}
