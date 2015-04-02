package spring.wicket.bootstrap.bootstrap.css;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.FilteringHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;

/**
 * Created with IntelliJ IDEA.
 * User: daveburke
 * Date: 3/28/15
 * Time: 12:04 PM
 */
public final class FilteringHeaderResponseDecorator implements IHeaderResponseDecorator {

    @Override
    public IHeaderResponse decorate(IHeaderResponse response) {
        return new FilteringHeaderResponse(response);
    }
}