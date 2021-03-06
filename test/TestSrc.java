import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment1.DirParser;

/**
 * Run some tests on our own source code
 * 
 * @author daniel
 *
 */
public class TestSrc {

	// I'll be checking against sysout
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(System.out);
	}

	@Test
	public void testString() {
		DirParser.main(new String[] { AllTests.BASEDIR + "/src", "java.lang.String" });
		assertEquals("java.lang.String. Declarations found: 0; references found: 10.\n", outContent.toString());
	}

	@Test
	public void testDirParser() {
		DirParser.main(new String[] { AllTests.BASEDIR + "/src", "assignment1.DirParser" });
		assertEquals("assignment1.DirParser. Declarations found: 1; references found: 2.\n", outContent.toString());
	}

	@Test
	public void testReferenceCounter() {
		DirParser.main(new String[] { AllTests.BASEDIR + "/src", "assignment1.ReferenceCounter" });
		assertEquals("assignment1.ReferenceCounter. Declarations found: 1; references found: 0.\n",
				outContent.toString());// cross file references are impossible to determine without a complete
										// classpath so references = 0
	}

	@Test
	public void testCounts() {
		DirParser.main(new String[] { AllTests.BASEDIR + "/src", "assignment1.ReferenceCounter$counts" });
		assertEquals("assignment1.ReferenceCounter$counts. Declarations found: 1; references found: 3.\n",
				outContent.toString());
	}

}
