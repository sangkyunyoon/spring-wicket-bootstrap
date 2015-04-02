package spring.wicket.bootstrap.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.wicketstuff.annotation.mount.MountPath;
import spring.wicket.bootstrap.WicketApplication;

/**
 * sample homepage
 *
 * @author Stefan Kloe
 *
 */
@MountPath(value = "/")
public class HomePage extends BasePage {


    public HomePage(PageParameters parameters) {
        super(parameters);
     }

        @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        CssResourceReference cssResourceReference = new CssResourceReference(
                WicketApplication.class, "home.css");
        response.render(CssHeaderItem.forReference(cssResourceReference));
    }


}
