package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.MajorScale;
import edu.cs622.Question;
import edu.cs622.ScaleChordQuestion;
import edu.cs622.SimpleScaleChordQuestion;

public class SimpleScaleChordQuestionTest {
	Question ques;
	Question ques2;
	Question ques3;

	@Before
	public void setUp() throws Exception {
		String[] chordQualities = { "mediant", "tonic", "dominant"};
		ques = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(0), chordQualities);
		String[] chordQualities2 = {"leading tone", "mediant", "dominant"};
		String[] chordQualities3 = {"tonic", "submediant", "mediant"};
		ques2 = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(1), chordQualities2);
		ques3 = new SimpleScaleChordQuestion<MajorScale>(new MajorScale(4), chordQualities3);
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
	
	// Test values for submediant scale in E Major
	@Test
	public void testForCorrectQuestionFormatting3(){
		String expectedOutput = "If you take the tonic, submediant, and mediant in the key of E Major, what is the roman numeral for the resulting chord?";
		String actualOutput = ques3.getQuestion();
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
	
	// Test for correct answer for submediant in E Major
	@Test
	public void testForCorrectAnswer3(){
		String expectedAnswer = "vi";
		String actualAnswer = ques3.getAnswer();
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
	
	// Test for the correct mapping between a roman numeral for a chord and it's interval representation
	@Test
	public void testForCorrectRomanNumeralChordIntervalMapping(){
		List<Integer> expectedValue = new ArrayList<>(Arrays.asList(2, 7, 11));
		List<Integer> actualValue = ScaleChordQuestion.chordIntervalsLookupMajorScale("V");
		assertEquals(expectedValue, actualValue);
	}
	
	// Test that the we get the correct scale degree names given a list of chord intervals
	@Test
	public void testForCorrectScaleDegreeNamesForChordIntervals(){
		List<String> expectedValue = new ArrayList<>(Arrays.asList("tonic","mediant", "dominant"));
		List<String> actualValue = ScaleChordQuestion.scaleDegreeNamesForChordIntervals(new ArrayList<Integer>(Arrays.asList(0, 4, 7)));
		assertEquals(expectedValue, actualValue);
	}
}
