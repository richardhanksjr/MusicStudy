package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.MajorScale;
import edu.cs622.Question;
import edu.cs622.SimpleScaleChordQuestion;

public class SimpleScaleChordQuestionTest {
	Question ques;
	Question ques2;

	@Before
	public void setUp() throws Exception {
		String[] chordQualities = { "mediant", "tonic", "dominant"};
		ques = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(0), chordQualities);
		String[] chordQualities2 = {"leading tone", "mediant", "dominant"};
		ques2 = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(1), chordQualities2);
	}

	// Test for the correct formatting of the question
	@Test
	public void testForCorrectQuestionFormatting() {
		String expectedOutput = "If you take the mediant, tonic, and dominant in the key of C Major, what is the roman numeral for the resulting chord?";
		String actualOutput = ques.getQuestion();
		assertEquals(expectedOutput, actualOutput);
	}
	
	// Test question formatting with different values
	@Test
	public void testForCorrectQuestionFormatting2(){
		String expectedOutput = "If you take the leading tone, mediant, and dominant in the key of C# Major, what is the roman numeral for the resulting chord?";
		String actualOutput = ques2.getQuestion();
		assertEquals(expectedOutput, actualOutput);
	}
	
	// Test for the correct answer
	@Test
	public void testForCorrectAnswer(){
		String expectedAnswer = "I";
		String actualAnswer = ques.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the correct answer for other values
	@Test
	public void testForCorrectAnswer2(){
		String expectedAnswer = "iii";
		String actualAnswer = ques2.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the correct explanation
	@Test
	public void testForExplanation(){
		String expectedAnswer = "The mediant is E, the tonic is C, and the dominant is G.  In the key of C Major this is the I chord. I is the answer.";
		String actualAnswer = ques.getExplanation();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the correct explanation for other values
	@Test
	public void testForExplanation2(){
		String expectedAnswer = "The leading tone is C, the mediant is F, and the dominant is Ab.  In the key of C# Major this is the iii chord. iii is the answer.";
		String actualAnswer = ques2.getExplanation();
		assertEquals(expectedAnswer, actualAnswer);
	}

}
