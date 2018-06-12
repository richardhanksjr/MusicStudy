package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.AbstractQuestion;
import edu.cs622.MajorScale;
import edu.cs622.NaturalMinorScale;
import edu.cs622.SimpleScaleDegreeQuestion;

public class SimpleScaleDegreeQuestionTest {

	// Major scale version
	AbstractQuestion quest;
	// Natural minor version
	AbstractQuestion questNM;
	// Natural minor version to test the sixth scale degree, which is unique to natural minor
	AbstractQuestion questNMSixth;
	// Natural minor scale in a different key
	AbstractQuestion questNMD;
	
	@Before
	public void setUp() throws Exception{
		quest = new SimpleScaleDegreeQuestion<MajorScale>(new MajorScale(0), "third");
		questNM = new SimpleScaleDegreeQuestion<NaturalMinorScale>(new NaturalMinorScale(0), "third");
		questNMSixth = new SimpleScaleDegreeQuestion<NaturalMinorScale>(new NaturalMinorScale(0), "sixth");
		questNMD = new SimpleScaleDegreeQuestion<NaturalMinorScale>(new NaturalMinorScale(2), "third");
	}
	
	@Test
	public void testCorrectQuestionFormatting(){
		String expectedFormatting = "What is the third scale degree of C Major?";
		String actualFormatting = quest.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	@Test
	public void testCorrectQuestionFormattingNM(){
		String expectedFormatting = "What is the third scale degree of C Natural Minor?";
		String actualFormatting = questNM.getQuestion();
		assertEquals(expectedFormatting, actualFormatting);
	}
	
	@Test
	public void testForCorrectAnswer(){
		String expectedAnswer = "E";
		String actualAnswer = quest.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
		
	}
	
	@Test
	public void testForCorrectAnswerNM(){
		String expectedAnswer = "Eb";
		String actualAnswer = questNM.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
		
	}
	
	@Test
	public void testForCorrectAnswerNMD(){
		String expectedAnswer = "F";
		String actualAnswer = questNMD.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
		
	}
	
	@Test
	public void testForCorrectAnswerNMSixth(){
		String expectedAnswer = "Ab";
		String actualAnswer = questNMSixth.getAnswer();
		assertEquals(expectedAnswer, actualAnswer);
		
	}
	
	@Test
	public void testForCorrectExplanation(){
		String expectedExplanation = "The third scale degree of C Major is E.  E is the correct answer.";
		String actualExplanation = quest.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	@Test
	public void testForCorrectExplanationNM(){
		String expectedExplanation = "The third scale degree of C Natural Minor is Eb.  Eb is the correct answer.";
		String actualExplanation = questNM.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
	
	@Test
	public void testForCorrectExplanationNMSixth(){
		String expectedExplanation = "The sixth scale degree of C Natural Minor is Ab.  Ab is the correct answer.";
		String actualExplanation = questNMSixth.getExplanation();
		assertEquals(expectedExplanation, actualExplanation);
	}
}
