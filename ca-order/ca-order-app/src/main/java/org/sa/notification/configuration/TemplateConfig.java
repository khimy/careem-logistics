package org.sa.notification.configuration;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import org.sa.notification.services.TemplateProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Vivek on 21-02-2017.
 * This is a singleton template config which gets loaded by
 * default ftl location
 */

public class TemplateConfig {

    private static Configuration configuration;

    private TemplateConfig(){}

    public static Configuration getConfiguration() throws IOException, URISyntaxException {
        if(configuration == null){
            synchronized(TemplateConfig.class){
                //double checking Singleton instance
                if(configuration == null){
                    initializeConfiguration();
                }
            }
        }
        return configuration;
    }

    private static void initializeConfiguration() throws URISyntaxException, IOException {
        configuration = new Configuration(Configuration.VERSION_2_3_25);
        URL url = TemplateProcessor.class.getClassLoader().getResource("templates");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(url.toURI())));
    }
}
