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
	public Boolean checkAnswer(String givenAnswer) {
		String formattedAnswer = givenAnswer.substring(0, 1).toUpperCase() + givenAnswer.substring(1).toLowerCase();
		return (formattedAnswer.equals(this.answer));
	}

}

