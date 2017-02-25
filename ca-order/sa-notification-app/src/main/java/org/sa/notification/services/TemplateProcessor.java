package org.sa.notification.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.sa.notification.configuration.TemplateConfig;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Vivek on 17-02-2017.
 * This class accepts the name of FTL file
 * and the data as input
 * and returns a string as generated template
 *
 */

@Service
public class TemplateProcessor {

    public static final String SMS_TEMPLATE="sms";
    public static final String TEMPLATE_EXT=".ftl";

    /**
     *
     * @param templateName eg:sms.ftl
     * @param data
     * @return
     */
    public String getMessage(String templateName,Map<String, Object> data) {
        try {
            Template template = TemplateConfig.getConfiguration().getTemplate(templateName);
            String readyParsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
            return readyParsedTemplate;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


}
