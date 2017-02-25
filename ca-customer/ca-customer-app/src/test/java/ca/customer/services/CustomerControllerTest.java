package ca.customer.services;

import ca.customer.api.Address;
import ca.customer.api.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
//@ContextConfiguration(classes = SADonationApplication.class)
/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)
@AutoConfigureJson
@AutoConfigureJsonTesters
@WebMvcTest
@ActiveProfiles(profiles = "local")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})*/


@RunWith(SpringRunner.class)
@AutoConfigureJson
@AutoConfigureJsonTesters
@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Customer> createJacksonTester;

    final String BASE_URL = "http://localhost:9096/";

    @Test
    @WithMockUser
    public void createCustomer() throws Exception {
        Customer customer =new Customer();
        customer.address=new Address();
        customer.name.first="Vivek";
        customer.name.last="Mukesh";
        customer.email="vivek@gmail.com";
        customer.mobile="8951152580";

        this.mvc.perform(post(BASE_URL+"customer/")
            .content(createJacksonTester.write(customer).getJson())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk());

    }

}
