package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.SimpleIntervalUpMajorScale;

public class SimpleIntervalUptest {
	SimpleIntervalUpMajorScale intUpCMajor;

	@Before
	public void setUp() throws Exception {
		intUpCMajor = new SimpleIntervalUpMajorScale(0);
	}

	// Test the formatting of the question for the given input
	@Test
	public void testQuestionFormattingGivenInputs() {
		String key = "C";
		String interval = "perfect fifth above";
		String modeDegreeName = "median";
		String expectedOutput = "In the key of C Major, what is the note a perfect fifth above the median?";
		assertEquals(expectedOutput, intUpCMajor.generateQuestion(key, interval, modeDegreeName));

	}

}
