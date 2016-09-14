package demo.pages;

import demo.config.MockWicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HomePageTest
{
	private WicketTester tester;

    @Autowired
    private ApplicationContext ctx;

	@Before
	public void setUp()
	{
        MockWicketApplication wicketApplication = new MockWicketApplication();
        wicketApplication.setApplicationContext(ctx);

		tester = new WicketTester(wicketApplication);
	}

	@Test
	public void testRenderHomePage()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}
}
