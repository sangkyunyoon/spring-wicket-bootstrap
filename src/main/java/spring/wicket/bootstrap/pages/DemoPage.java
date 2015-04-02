package spring.wicket.bootstrap.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import spring.wicket.bootstrap.dao.PropertyClass;
import spring.wicket.bootstrap.services.TextService;

/**
 * sample page to show mounting and spring integration
 *
 * @author Stefan Kloe
 *
 */

public class DemoPage extends BasePage {

    /* spring integration the wicket way */
    @SpringBean
    private TextService textService;

    @SpringBean
    private PropertyClass propertyClass;


    public DemoPage(PageParameters parameters) {
        super(parameters);

        add(new Label("propertyItem", String.format("PropertyClass.Color from external resource file: %s", propertyClass.getColor())));

         /* spring integration in wicket component */
        add(new Label("textService", String.format("From Spring Example Service: %s",
                textService.getText())));
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }

}
