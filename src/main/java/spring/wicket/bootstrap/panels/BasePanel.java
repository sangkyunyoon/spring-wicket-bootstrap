package spring.wicket.bootstrap.panels;

import org.apache.wicket.markup.html.panel.Panel;
import spring.wicket.bootstrap.WicketApplication;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: daveburke
 * Date: 4/2/15
 * Time: 9:40 AM
 */
public class BasePanel extends Panel {

    public BasePanel(String markupId) {
        super(markupId);
    }

    public Properties getProperties() {
        return WicketApplication.get().getProperties();
    }

}
