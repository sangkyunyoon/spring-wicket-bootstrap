package spring.wicket.bootstrap.theme;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * Wicket theme css resource reference
 *
 * @author miha
 */
public class NixmashCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final NixmashCssReference INSTANCE = new NixmashCssReference();

    /**
     * Private constructor.
     */
    public NixmashCssReference() {
        super(NixmashCssReference.class, "css/bootstrap.nixmash.css");
    }

}