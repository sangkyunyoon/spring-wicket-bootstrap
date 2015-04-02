package spring.wicket.bootstrap.panels;

import org.apache.wicket.markup.html.basic.Label;
import spring.wicket.bootstrap.WicketApplication;

/**
 * The default page footer.
 *
 * @author miha
 * @version 1.0
 */
public class Footer extends BasePanel {

    /**
     * Construct.
     *
     * @param markupId The pages markup id.
     */
    public Footer(String markupId) {
        super(markupId);

        String version = getProperties().getProperty("version");
        String buildDate = getProperties().getProperty("build.date");

        add(new Label("versionLabel", String.format("Release: %s", version)));
        add(new Label("configurationLabel", String.format("Wicket Runtime Configuration: %s", WicketApplication.get().getConfigurationType())));
        add(new Label("buildDateLabel", String.format("Build Date: %s", buildDate)));

    }

}
