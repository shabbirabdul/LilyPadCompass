

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.playmc.lilypadcompass.Calculator;

/**
 * The class <code>CalculatorTest</code> contains tests for the class <code>{@link Calculator}</code>.
 *
 * @generatedBy CodePro at 3/9/15 11:09 PM
 * @author shabbir
 * @version $Revision: 1.0 $
 */
public class TestCalculator {
	/**
	 * Run the int add(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testAdd_1()
		throws Exception {
		Calculator fixture = new Calculator();
		int x = 1;
		int y = 1;

		int result = fixture.add(x, y);

		// add additional test code here
		assertEquals(2, result);
	}

	/**
	 * Run the int multiply(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testMultiply_1()
		throws Exception {
		Calculator fixture = new Calculator();
		int x = 1;
		int y = 1;

		int result = fixture.multiply(x, y);

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int subtract(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/9/15 11:09 PM
	 */
	@Test
	public void testSubtract_1()
		throws Exception {
		Calculator fixture = new Calculator();
		int x = 1;
		int y = 1;

		int result = fixture.subtract(x, y);

		// add additional test code here
		assertEquals(0, result);
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
		new org.junit.runner.JUnitCore().run(TestCalculator.class);
	}
}