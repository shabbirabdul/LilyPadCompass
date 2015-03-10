

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.playmc.lilypadcompass.ExchangeRate;

/**
 * The class <code>ExchangeRateTest</code> contains tests for the class <code>{@link ExchangeRate}</code>.
 *
 * @generatedBy CodePro at 3/9/15 11:09 PM
 * @author shabbir
 * @version $Revision: 1.0 $
 */
public class TestExchangeRate {
	/**
	 * Run the double getEuroUsdRate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testGetEuroUsdRate_1()
		throws Exception {
		ExchangeRate fixture = new ExchangeRate();

		double result = fixture.getEuroUsdRate();

		// add additional test code here
		assertEquals(100.0, result, 0.1);
	}

	/**
	 * Run the double getGoldEuroRate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testGetGoldEuroRate_1()
		throws Exception {
		ExchangeRate fixture = new ExchangeRate();

		double result = fixture.getGoldEuroRate();

		// add additional test code here
		assertEquals(100.0, result, 0.1);
	}

	/**
	 * Run the double getSilverEuroRate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testGetSilverEuroRate_1()
		throws Exception {
		ExchangeRate fixture = new ExchangeRate();

		double result = fixture.getSilverEuroRate();

		// add additional test code here
		assertEquals(100.0, result, 0.1);
	}

	/**
	 * Run the void main(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testMain_1()
		throws Exception {
		String[] args = new String[] {};

		ExchangeRate.main(args);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TestExchangeRate.class);
	}
}