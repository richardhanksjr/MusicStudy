package edu.cs622;

/**
 * Interface for the Question functionality
 * @author Richard Hanks
 *
 */
public interface Question {
	public abstract String generateQuestion();
	public abstract String generateAnswer();
	public abstract Boolean checkAnswer();
	void generateQuestionAndAnswer();
}
