package edu.cs622;

/**
 * Class for generating a question and answer to test downward intervals in a major scale
 * @author Richard Hanks
 *
 */

public class SimpleIntervalDownMajorScale extends AbstractScalarIntervalQuestion{
	
	private String answerExplanation;


	// Constructors
	public SimpleIntervalDownMajorScale(int root){
		this.scaleInstance = new MajorScale(root);
		this.key = MajorScale.getPitchNameMapping().get(root);
		this.questionTemplate = "What is the note a %s the fifth scale degree in %s Major?";
		this.generateQuestionAndAnswer();
	}



	@Override
	public void generateQuestionAndAnswer() {
		this.generateQuestionAndAnswer("major second below");
		
	}
	
	/**
	 * This does the heavy lifting of generating the question, the answer, and the explanation of the answer
	 * @param interval
	 */
	public void generateQuestionAndAnswer(String interval){
		this.question = String.format(this.questionTemplate, interval, this.key);
		// Generate answer
		// Get the fifth scale degree of the key
		int fifth = Scale.getInterval(this.scaleInstance.getRoot(), 7);
		// Convert the interval to a number of halfsteps
		int halfStepsInInterval = Scale.getIntervalMapping().get(interval);
		// Get the given interval below the fifth
		int answerInt = Scale.getInterval(fifth, halfStepsInInterval);
		// set the answer as the string
		this.answer = Scale.getPitchNameMapping().get(answerInt);
		String fifthString = Scale.getPitchNameMapping().get(fifth);
		this.answerExplanation = String.format("The fifth scale degree of %s Major is %s.  A %s %s is %s. %s is the correct answer.",
							this.key, fifthString, interval, fifthString, this.answer, this.answer);
	}


	/** 
	 * Provides the explanation to the user about how the correct answer is derived
	 * @return the explanation
	 */
	@Override
	public String getExplanation() {
		return this.answerExplanation;
	}


	/**
	 * return the key to be used to update the scoring for this type of question
	 */
	@Override
	public String getKey() {
		return "SimpleIntervalDownMajorScale";
	}



	@Override
	public String[] getIncorrectAnswerOptions() {
		// TODO Auto-generated method stub
		return null;
	}

}
