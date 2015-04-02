package spring.wicket.bootstrap;

import org.apache.wicket.util.tester.WicketTester;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is used mainly to encapsulate the initialization of the
 * WicketTester.
 * 
 * Each WebApplication can only be used for tester instantiation once.
 * WicketWebApplication is a spring bean (singleton) therefore only a
 * WicketTester singleton is possible.
 * 
 * @author kloe
 * 
 */
@Component
public class TestService implements InitializingBean {

	@Autowired
	private WicketApplication wicketApplication;

	private WicketTester wicketTester;

	@Override
	public void afterPropertiesSet() throws Exception {
		wicketTester = new WicketTester(wicketApplication);
	}

	public WicketTester getWicketTester() {
		return wicketTester;
	}

}
