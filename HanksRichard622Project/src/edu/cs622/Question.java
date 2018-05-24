package edu.cs622;

/**
 * Interface for the Question functionality
 * @author Richard Hanks
 *
 */
public interface Question {

	public abstract Boolean checkAnswer(String givenAnswer);
	public abstract String getQuestion();
	public abstract String getAnswer();
	void generateQuestionAndAnswer();
	String getExplanation();
	public abstract String getKey();
}
