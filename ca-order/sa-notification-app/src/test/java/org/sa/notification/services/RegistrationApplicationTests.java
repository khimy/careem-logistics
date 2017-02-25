package org.sa.notification.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sa.notification.services.endpoints.EmailEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vivek on 22-02-2017.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RegistrationApplication.class)
@WebAppConfiguration
public class RegistrationApplicationTests {

    @Test
    public void contextLoads() {
    }

}*/
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = SADonationApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

//@WebAppConfiguration
public class RegistrationApplicationTests {

    @Autowired
    private EmailEndPoint emailEndPoint;


    @Test
    public void test(){
        emailEndPoint.sendEmail();
    }
}
