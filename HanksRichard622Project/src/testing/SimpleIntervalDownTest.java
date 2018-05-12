package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.SimpleIntervalDownMajorScale;

public class SimpleIntervalDownTest {
	SimpleIntervalDownMajorScale intDown;
	@Before
	public void setUp() throws Exception {
		intDown = new SimpleIntervalDownMajorScale(7);
	}

	// Test the formatting of the question string
	@Test
	public void testTheFormattingOfTheQuestion() {
		String expectedQuestion = "What is the note a major 2nd below the fifth scale degree in G Major?";
		assertEquals(expectedQuestion, intDown.getQuestion());

	}

}