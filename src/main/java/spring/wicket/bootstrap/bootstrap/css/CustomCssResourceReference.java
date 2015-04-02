package spring.wicket.bootstrap.bootstrap.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A simple stylesheet to fix some styles for the demo page.
 *
 * @author miha
 * @version 1.0
 */
public class CustomCssResourceReference extends CssResourceReference {

    public static final CustomCssResourceReference INSTANCE = new CustomCssResourceReference();

    /**
     * Construct.
     */
    public CustomCssResourceReference() {
        super(CustomCssResourceReference.class, "custom.css");
    }
}
