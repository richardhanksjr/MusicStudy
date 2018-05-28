package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.Question;
import edu.cs622.ScaleChordQuestion;

public class ScaleChordQuestionTest {
	Question ques;
	@Before
	public void setUp() throws Exception {
//		ques = new ScaleChordQuestion(new MajorScale(5));
	}

	// Test for the correct results for chord Roman numeral lookup
	@Test
	public void testForCorrectChordLookup() {
		String expectedValue = "I";
		String actualValue = ScaleChordQuestion.chordLookupMajorScale(Arrays.asList(0, 4, 7));
		assertEquals(expectedValue, actualValue);
		expectedValue = "ii";
		actualValue = ScaleChordQuestion.chordLookupMajorScale(Arrays.asList(2, 5, 9));
		assertEquals(expectedValue, actualValue);
		expectedValue = "iii";
		actualValue = ScaleChordQuestion.chordLookupMajorScale(Arrays.asList(4, 7, 11));
		assertEquals(expectedValue, actualValue);
	}

}
