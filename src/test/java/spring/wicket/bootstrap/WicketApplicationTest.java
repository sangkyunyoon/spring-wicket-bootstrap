package spring.wicket.bootstrap;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.wicket.bootstrap.pages.HomePage;
import spring.wicket.bootstrap.pages.DemoPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebInitializer.class,
		WicketApplication.class, SpringConfiguration.class })
public class WicketApplicationTest {

	@Autowired
	private TestService testService;

	@Test
	public void testApplication() {
		WicketTester wicketTester = testService.getWicketTester();
		wicketTester.startPage(HomePage.class);
		wicketTester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void testMountedPage() {
		WicketTester wicketTester = testService.getWicketTester();
		wicketTester.startPage(DemoPage.class);
		wicketTester.assertRenderedPage(DemoPage.class);
	}

}
