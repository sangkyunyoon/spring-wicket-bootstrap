package spring.wicket.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import spring.wicket.bootstrap.dao.PropertyClass;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "spring.wicket.bootstrap")
public class SpringConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SpringConfiguration.class);


    @Bean
    public static PropertyOverrideConfigurer propertyOverride() {
        final PropertyOverrideConfigurer properties = new PropertyOverrideConfigurer();
        log.debug("IN SPRINGCONFIGURATION --------------------------------------------------");
        Properties jProperties = new Properties();
        try {
            jProperties.load(
                    SpringConfiguration.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        Resource location = new FileSystemResource(
                jProperties.getProperty("spring.properties.file"));
        properties.setLocation(location);

        return properties;
    }

    @Bean
    public PropertyClass propertyClass() {
        PropertyClass properties = new PropertyClass();
        return properties;
    }
}
