package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.AltoClef;
import edu.cs622.SimpleClefQuestion;
import edu.cs622.TenorClef;

public class SimpleClefQuestionTest {

	SimpleClefQuestion<AltoClef> altoQuestion;
	SimpleClefQuestion<TenorClef> tenorQuestion;
	@Before
	public void setUp() throws Exception {	
		altoQuestion = new SimpleClefQuestion<AltoClef>(new AltoClef(), "third space");
		tenorQuestion = new SimpleClefQuestion<TenorClef>(new TenorClef(), "third space");
	}

	// Test for the proper formatting of the question
	@Test
	public void testForProperQuestionFormattingAlto() {
		String expectedFormatting = "What is the third space of the alto clef staff?";
		String actualFormatting = altoQuestion.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	// Test for the proper formatting of the question
	@Test
	public void testForProperQuestionFormattingTenor() {
		String expectedFormatting = "What is the third space of the tenor clef staff?";
		String actualFormatting = tenorQuestion.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	// Test for the correct answer
	@Test
	public void testForCorrectAnswerAlto(){
		String expectedAnswer = "D";
		String actualAnswer = altoQuestion.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for the correct answer
	@Test
	public void testForCorrectAnswerTenor(){
		String expectedAnswer = "B";
		String actualAnswer = tenorQuestion.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test for correct explanation
	@Test
	public void testForCorrectExplanationAlto(){
		String expectedExplanation = "The third space of the alto clef is D.  The correct answer is D";
		String actualExplanation = altoQuestion.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	// Test for correct explanation
	@Test
	public void testForCorrectExplanationTenor(){
		String expectedExplanation = "The third space of the tenor clef is B.  The correct answer is B";
		String actualExplanation = tenorQuestion.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	// Test for correct question key
	@Test
	public void testForCorrectQuestionKeyAlto(){
		String expectedQuestionKey = "Simple Clef Question";
		String actualQuestionKey = altoQuestion.getKey();
		assertEquals(expectedQuestionKey, actualQuestionKey);
	}
	
	// Test for correct question key
	@Test
	public void testForCorrectQuestionKeyTenor(){
		String expectedQuestionKey = "Simple Clef Question";
		String actualQuestionKey = tenorQuestion.getKey();
		assertEquals(expectedQuestionKey, actualQuestionKey);
	}
	
	@Test
	public void testForIncorrectAnswerOptions(){
		// Since the options are random, we can only test the properties that we can expect
		// Length of the array is 3
		int expectedLength = 3;
		int actualLength = altoQuestion.getIncorrectAnswerOptions().length;
		assertEquals(expectedLength, actualLength);
		// None of the values in the array is equal to the actual answer
		for(String answer: altoQuestion.getIncorrectAnswerOptions()){
			assertNotEquals(answer, altoQuestion.getAnswer());
		}
	}

}
