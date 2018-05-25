package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.MajorScale;
import edu.cs622.Question;
import edu.cs622.SimpleScaleChordQuestion;

public class SimpleScaleChordQuestionTest {
	Question ques;

	@Before
	public void setUp() throws Exception {
		String[] chordQualities = {"tonic", "mediant", "dominant"};
		ques = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(0), chordQualities);
	}

	// Test for the correct formatting of the question
	@Test
	public void testForCorrectQuestionFormatting() {
		String expectedOutput = "If you take the tonic, mediant, and dominant in the key of C Major, what is the roman numeral for the resulting chord?";
		String actualOutput = ques.getQuestion();
		assertEquals(expectedOutput, actualOutput);
	}
	
	// Test for the correct answer
	@Test
	public void testForCorrectAnswer(){
		String expectedAnswer = "I";
		String actualAnswer = ques.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the correct explanation
	@Test
	public void testForExplanation(){
		String expectedAnswer = "The tonic is C, the mediant is E, and the dominant is G.  In the key of C Major this is the I chord. I is the answer.";
		String actualAnswer = ques.getExplanation();
		assertEquals(expectedAnswer, actualAnswer);
	}

}
