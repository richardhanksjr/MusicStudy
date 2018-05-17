package edu.cs622;

/**
 * Interface for the Question functionality
 * @author Richard Hanks
 *
 */
public interface Question {

	public abstract Boolean checkAnswer(String givenAnswer);
	void generateQuestionAndAnswer();
	String getExplanation();
}
