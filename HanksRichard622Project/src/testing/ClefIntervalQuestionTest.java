package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.AltoClef;
import edu.cs622.ClefIntervalQuestion;
import edu.cs622.TenorClef;

public class ClefIntervalQuestionTest {
	ClefIntervalQuestion clefInter;
	ClefIntervalQuestion clefInterSmallUp;
	ClefIntervalQuestion clefInterSmallDown;
	ClefIntervalQuestion clefInterLargeDown;
	ClefIntervalQuestion clefInterTenor;

	@Before
	public void setUp() throws Exception {
		clefInter = new ClefIntervalQuestion<AltoClef>(new AltoClef(), "first line", "third space");
		clefInterSmallUp = new ClefIntervalQuestion<AltoClef>(new AltoClef(), "first line", "first space");
		clefInterSmallDown = new ClefIntervalQuestion<AltoClef>(new AltoClef(), "first space", "first line");
		clefInterLargeDown = new ClefIntervalQuestion<AltoClef>(new AltoClef(), "third space", "first line");
		clefInterTenor = new ClefIntervalQuestion<TenorClef>(new TenorClef(), "first line", "third space");
	}

	
	@Test
	public void testCorrectQuestionFormatting() {
		String expectedFormatting = "What is the interval between the first line of the alto clef and the third space of the alto clef?";
		String actualFormatting = clefInter.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	@Test
	public void testCorrectQuestionFormattingTenor() {
		String expectedFormatting = "What is the interval between the first line of the tenor clef and the third space of the tenor clef?";
		String actualFormatting = clefInterTenor.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	// Test correctAnswer for large interval up
	@Test
	public void testCorrectAnswer(){
		String expectedAnswer = "major sixth above";
		String actualAnswer = clefInter.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test correctAnswer for large interval up
	@Test
	public void testCorrectAnswerTenor(){
		String expectedAnswer = "major sixth above";
		String actualAnswer = clefInterTenor.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test correct answer for small interval up
	@Test
	public void testCorrectAnswerSmallUp(){
		String expectedAnswer = "major second above";
		String actualAnswer = clefInterSmallUp.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	// Test correct answer for small interval down
	@Test
	public void testCorrectAnswerSmallDown(){
		String expectedAnswer = "major second below";
		String actualAnswer = clefInterSmallDown.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	// Test correct answer for large interval down
	@Test
	public void testCorrectAnswerLargeDown(){
		String expectedAnswer = "major sixth below";
		String actualAnswer = clefInterLargeDown.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
	}
	
	// Test explanation
	@Test
	public void testCorrectExplanation(){
		String expectedExplanation = "The first line of the alto clef is F.  The third space of the alto clef is D.  D is a major sixth above F.";
		String actualExplanation = clefInter.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}

}
