package ca.customer.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 17-02-2017.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "local")
public class TemplateProcessorTest {

    private TemplateProcessor templateProcessor;

    @Before
    public void setup() {
        templateProcessor = new TemplateProcessor();
    }


    @Test
    public void test() {
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("name","vivek");
        String result = templateProcessor.getMessage(TemplateProcessor.SMS_TEMPLATE+ TemplateProcessor.TEMPLATE_EXT,data);
        Assert.assertNotNull(result);
        System.out.println(data);
    }
}
