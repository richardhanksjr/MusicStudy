package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.MajorScale;
import edu.cs622.NaturalMinorScale;
import edu.cs622.SimpleIntervalDownGeneric;

public class SimpleIntervalDownGenericTest {

	SimpleIntervalDownGeneric<MajorScale> intDown;
	SimpleIntervalDownGeneric<NaturalMinorScale> intDownNaturalMinor;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Before
	public void setUp() throws Exception {
		intDown = new SimpleIntervalDownGeneric(new MajorScale(7));
		// Need to explicitly call with this value to override the default randomness that is passed to the no args version of generateQuestionAndAnswer()
		intDown.generateQuestionAndAnswer("major second below");
		intDownNaturalMinor = new SimpleIntervalDownGeneric(new NaturalMinorScale(0));
		// Need to explicitly call with this value to override the default randomness that is passed to the no args version of generateQuestionAndAnswer()
		intDownNaturalMinor.generateQuestionAndAnswer("major second below");
	}


	
	// Test that the answer is correct
	@Test
	public void testForCorrectAnswer(){
		String expectedAnswer = "C";
		assertEquals(expectedAnswer, intDown.getAnswer());

	}
	
	// Test that the answer check works
	@Test
	public void testCheckForCorrectAnswer(){
		String correctAnswer = "C";
		assertEquals(true, intDown.checkAnswer(correctAnswer));
	}

	
	// Test that the answer check returns false when the answer isn't correct
	@Test
	public void testCheckForIncorrectAnsewr(){
		String incorrectAnswer = "C#";
		assertEquals(false, intDown.checkAnswer(incorrectAnswer));
	}
	
	// Test that the feedback explanation to the user on the question is correct
	@Test
	public void testCorrectUserAnswerExplanation(){
		String expectedExplanation = "The fifth scale degree of G Major is D.  A major second below D is C. C is the correct answer.";
		String actualExplanation = intDown.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	// Test that the proper key name is returned
	@Test
	public void testReturnsCorrectKey(){
		String expectedKey = "Compound Scalar Intervals Down";
		String actualKey = intDown.getKey();
		assertEquals(expectedKey, actualKey);
		
	}
	
	
	// Test the formatting of the question string
	@Test
	public void testTheFormattingOfTheQuestion() {
		String expectedQuestion = "What is the note a major second below the fifth scale degree in G Major?";
		// Printing actual value to for homework submission example
		System.out.println(intDown.getQuestion());
		assertEquals(expectedQuestion, intDown.getQuestion());

	}
	
	// TEST THE NATURAL MINOR SCALE VERSION!!!!!
	// Test the formatting of the question string
	@Test
	public void testTheFormattingOfTheQuestionNM() {
		String expectedQuestion = "What is the note a major second below the fifth scale degree in C Natural Minor?";
		// Printing actual value to for homework submission example
		System.out.println(intDownNaturalMinor.getQuestion());
		assertEquals(expectedQuestion, intDownNaturalMinor.getQuestion());

	}
	
	// Test that the answer is correct
	@Test
	public void testForCorrectAnswerNM(){
		String expectedAnswer = "F";
		assertEquals(expectedAnswer, intDownNaturalMinor.getAnswer());

	}
	
	// Test that the answer check returns false when the answer isn't correct
	@Test
	public void testCheckForIncorrectAnsewrNM(){
		String incorrectAnswer = "C#";
		assertEquals(false, intDown.checkAnswer(incorrectAnswer));
	}
	
	// Test that the feedback explanation to the user on the question is correct
	@Test
	public void testCorrectUserAnswerExplanationNM(){
		String expectedExplanation = "The fifth scale degree of C Natural Minor is G.  A major second below G is F. F is the correct answer.";
		String actualExplanation = intDownNaturalMinor.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	// Test that the proper key name is returned
	@Test
	public void testReturnsCorrectKeyNM(){
		String expectedKey = "Compound Scalar Intervals Down";
		String actualKey = intDownNaturalMinor.getKey();
		assertEquals(expectedKey, actualKey);
		
	}

}
