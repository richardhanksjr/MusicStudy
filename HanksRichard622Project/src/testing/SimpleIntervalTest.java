package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.SimpleInterval;

public class SimpleIntervalTest {

	SimpleInterval si;
	@Before
	public void setUp() throws Exception {
		si = new SimpleInterval(0, "minor second below");
	}

	// Test the correct format of the string
	@Test
	public void testCorrectQuestionFormat(){
		String expectedFormat = "What is the note a minor second below C?";
		String actualFormat = si.getQuestion();
		assertEquals(expectedFormat, actualFormat);
	}
	
	// Test for the correct answer
	@Test
	public void testForCorrectAnswer(){
		String expectedAnswer = "B";
		String actualAnswer = si.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the expected answer explanation
	@Test
	public void testForCorrectExplanation(){
		String expectedExplanation = "The note a minor second below C is B.  The answer is B.";
		String actualExplanation = si.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}

}
