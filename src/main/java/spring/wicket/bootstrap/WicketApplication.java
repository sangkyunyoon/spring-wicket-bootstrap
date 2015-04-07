package spring.wicket.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyCssResourceReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.*;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import net.ftlines.wicketsource.WicketSource;
import org.apache.wicket.*;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import spring.wicket.bootstrap.bootstrap.css.CustomCssResourceReference;
import spring.wicket.bootstrap.bootstrap.css.FilteringHeaderResponseDecorator;
import spring.wicket.bootstrap.bootstrap.css.FixBootstrapStylesCssResourceReference;
import spring.wicket.bootstrap.bootstrap.css.UtilityCssResourceReference;
import spring.wicket.bootstrap.bootstrap.js.ApplicationJavaScript;
import spring.wicket.bootstrap.pages.DemoPage;
import spring.wicket.bootstrap.pages.HomePage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//import FixBootstrapStylesCssResourceReference;

/**
 * The web application class also serves as spring boot starting point by using
 * spring boot's EnableAutoConfiguration annotation and providing the main
 * method.
 *
 * @author Stefan Kloe
 */


@Component
@EnableAutoConfiguration
@ComponentScan
public class WicketApplication extends WebApplication {
    private Properties properties;

    private final static Logger log = LoggerFactory
            .getLogger(WicketApplication.class);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * spring boot main method to build context
     *
     * @param args
     */
    public static void main(String[] args) {
        log.debug("IN WICKETAPPLICATION MAIN --------------------------------------------------");
        SpringApplication.run(WicketApplication.class, args);
    }

    /**
     * <ul>
     * <li>making the wicket pages injectable by activating the
     * SpringComponentInjector</li>
     * <li>mounting the test page</li>
     * <li>logging spring service method output to showcase working
     * integration</li>
     * </ul>
     */
    @Override
    protected void init() {

        super.init();
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, applicationContext));

        mountPage("/demos.html", DemoPage.class);

        getApplicationSettings().setUploadProgressUpdatesEnabled(true);

        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);


        log.debug("IN WICKETWEBAPPLICATION INIT --------------------------------------------------");

        setHeaderResponseDecorator(new FilteringHeaderResponseDecorator());
        configureBootstrap();
        configureResourceBundles();

//        optimizeForWebPerformance();

        new AnnotatedMountScanner().scanPackage("spring.boot.wicket.pages").mount(this);

        if (Strings.isTrue(properties.getProperty("cdn.useCdn"))) {
            final String cdn = properties.getProperty("cdn.baseUrl");

            StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
        }

        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            securePackageResourceGuard.addPattern("+*.woff2");
        }

        if (RuntimeConfigurationType.DEVELOPMENT.equals(getConfigurationType()))
            WicketSource.configure(this);
    }

    public WicketApplication() {
        super();
        log.debug("IN WICKETWEBAPPLICATION CONSTRUCTOR --------------------------------------------------");
        properties = loadProperties();
        setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));
    }


    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {
        ResourceBundles bundles = getResourceBundles();
        bundles.addJavaScriptBundle(WicketApplication.class, "core.js",
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getJQueryReference(),
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketEventReference(),
                (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketAjaxReference(),
                ModernizrJavaScriptReference.instance()
        );

        bundles.addJavaScriptBundle(WicketApplication.class, "bootstrap.js",
                (JavaScriptResourceReference) Bootstrap.getSettings().getJsResourceReference(),
                (JavaScriptResourceReference) PrettifyJavaScriptReference.INSTANCE,
                ApplicationJavaScript.INSTANCE
        );

        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap-extensions.js",
                JQueryUICoreJavaScriptReference.instance(),
                JQueryUIWidgetJavaScriptReference.instance(),
                JQueryUIMouseJavaScriptReference.instance(),
                JQueryUIDraggableJavaScriptReference.instance(),
                JQueryUIResizableJavaScriptReference.instance(),
                Html5PlayerJavaScriptReference.instance()
        );

        bundles.addCssBundle(WicketApplication.class, "bootstrap-extensions.css",
                Html5PlayerCssReference.instance(),
                OpenWebIconsCssReference.instance()
        );

        bundles.addCssBundle(WicketApplication.class, "application.css",
                (CssResourceReference) PrettifyCssResourceReference.INSTANCE,
                FixBootstrapStylesCssResourceReference.INSTANCE,
                CustomCssResourceReference.INSTANCE,
                UtilityCssResourceReference.INSTANCE
        );

    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {

        final IBootstrapSettings settings = new BootstrapSettings();
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Flatly);

        settings.setJsResourceFilterName("footer-container");
        settings.setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
        BootstrapLess.install(this);

    }

    /**
     * @return used configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * loads all configuration properties from disk
     *
     * @return configuration properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream stream = getClass().getResourceAsStream("/config.properties");
            try {
                properties.load(stream);
            } finally {
                IOUtils.closeQuietly(stream);
            }
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return properties;
    }

    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type"));
    }


}
