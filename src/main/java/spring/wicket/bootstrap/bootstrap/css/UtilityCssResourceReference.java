package spring.wicket.bootstrap.bootstrap.css;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A simple stylesheet to fix some styles for the demo page.
 *
 * @author miha
 * @version 1.0
 */
public class UtilityCssResourceReference extends CssResourceReference {

    public static final UtilityCssResourceReference INSTANCE = new UtilityCssResourceReference();

    /**
     * Construct.
     */
    public UtilityCssResourceReference() {
        super(UtilityCssResourceReference.class, "utility.css");
    }
}
