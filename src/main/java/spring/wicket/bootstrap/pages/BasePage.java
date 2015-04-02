package spring.wicket.bootstrap.pages;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import spring.wicket.bootstrap.WicketApplication;
import spring.wicket.bootstrap.bootstrap.js.ApplicationJavaScript;
import spring.wicket.bootstrap.bootstrap.css.FixBootstrapStylesCssResourceReference;
import spring.wicket.bootstrap.panels.Footer;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: daveburke
 * Date: 3/27/15
 * Time: 1:36 PM
 */
abstract class BasePage extends GenericWebPage<Void> {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public BasePage(final PageParameters parameters) {
        super(parameters);


        add(new HtmlTag("html"));
        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Bootstrap Demo")));
        add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));

        add(newNavbar("navbar"));
        add(newNavigation("navigation"));

        add(new Footer("footer"));

        add(new Code("code-internal"));

        add(new HeaderResponseContainer("footer-container", "footer-container"));
    }

    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);

        // show brand name
//        navbar.setBrandName(Model.of("Spring Wicket Bootstrap"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                        new NavbarButton<Void>(HomePage.class, Model.of("Home")).setIconType(GlyphIconType.home),
                        new NavbarButton<Void>(DemoPage.class, Model.of("Demos"))
                )
        );

        return navbar;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
        response.render(new FilteredHeaderItem(JavaScriptHeaderItem.forReference(ApplicationJavaScript.INSTANCE), "footer-container"));
        response.render(RespondJavaScriptReference.headerItem());

        if (!getRequest().getRequestParameters().getParameterValue("bootlint").isNull()) {
            response.render(BootlintHeaderItem.INSTANCE);
        }
//        CssResourceReference cssResourceReference = new CssResourceReference(
//                WicketApplication.class, "custom.css");
//        response.render(CssHeaderItem.forReference(cssResourceReference));
    }

    private void configureTheme(PageParameters pageParameters) {
        StringValue theme = pageParameters.get("theme");

        if (!theme.isEmpty()) {
            IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
            settings.getActiveThemeProvider().setActiveTheme(theme.toString(""));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

//        configureTheme(getPageParameters());
    }

    public Properties getProperties() {
        return WicketApplication.get().getProperties();
    }

    protected boolean hasNavigation() {
        return false;
    }

    /**
     * creates a new navigation component.
     *
     * @param markupId The component's markup id
     * @return a new navigation component.
     */
    private Component newNavigation(String markupId) {
        WebMarkupContainer navigation = new WebMarkupContainer(markupId);
        navigation.add(new AffixBehavior("200"));
        navigation.setVisible(hasNavigation());

        return navigation;
    }


}