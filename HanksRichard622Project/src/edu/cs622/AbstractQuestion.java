package edu.cs622;

/**
 * Abstract class for providing instance variables that will be needed by all
 * concrete subclasses
 * @author Rich
 *
 */
public abstract class AbstractQuestion implements Question {
	protected String questionTemplate;
	protected String question;
	protected String answer;
	public abstract String[] getIncorrectAnswerOptions();
	public Boolean checkAnswer(String givenAnswer) {
		// Check that the format of the answer given matches what's expected
		if(givenAnswer.equals(this.answer)){
			return true;
		}else if (givenAnswer.toLowerCase().equals(this.answer)){
			return true;
		}
		// If the given format doesn't match, try one more time to account for the expected format of notes (Bb, for example)
		String formattedAnswer = givenAnswer.substring(0, 1).toUpperCase() + givenAnswer.substring(1).toLowerCase();
		return (formattedAnswer.equals(this.answer));
	}

}

