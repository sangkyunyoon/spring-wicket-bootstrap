package spring.wicket.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * This class is needed for deployment on an application server.
 * It is the counterpart of the main method in WicketWebApplication.
 *
 * @author Stefan Kloe
 */
@Configuration
public class WarInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {
    private static final Logger log = LoggerFactory.getLogger(SpringConfiguration.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.debug("IN WARINITIALIZER --------------------------------------------------");
        return application.sources(WicketApplication.class);
    }


}