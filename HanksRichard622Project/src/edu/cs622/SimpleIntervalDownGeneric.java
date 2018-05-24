package edu.cs622;

/**
 * Class for generating a question and answer to test downward intervals in a major scale
 * @author Richard Hanks
 *
 */

public class SimpleIntervalDownGeneric<E extends Scale> extends AbstractScalarIntervalQuestion{
	
	private String answerExplanation;


	// Constructors
	public SimpleIntervalDownGeneric(E e){
		this.scaleInstance = e;
		this.key = E.getPitchNameMapping().get(this.scaleInstance.getRoot());
		this.questionTemplate = "What is the note a %s the fifth scale degree in %s %s?";
		this.generateQuestionAndAnswer();
	}



	@Override
	public void generateQuestionAndAnswer() {
		this.generateQuestionAndAnswer("major 2nd below");
		
	}
	
	/**
	 * This does the heavy lifting of generating the question, the answer, and the explanation of the answer
	 * @param interval
	 */
	public void generateQuestionAndAnswer(String interval){
		this.question = String.format(this.questionTemplate, interval, this.key, this.scaleInstance.getQuality());
		// Generate answer
		// Get the fifth scale degree of the key
		int fifth = Scale.getInterval(this.scaleInstance.getRoot(), 7);
		// Convert the interval to a number of halfsteps
		int halfStepsInInterval = E.getIntervalMapping().get(interval);
		// Get the given interval below the fifth
		int answerInt = E.getInterval(fifth, halfStepsInInterval);
		// set the answer as the string
		this.answer = E.getPitchNameMapping().get(answerInt);
		String fifthString = E.getPitchNameMapping().get(fifth);
//		TODO Change "major" to the quality of E at runtime.  Will need to create an abstract getQuality() method on Scale to check
		this.answerExplanation = String.format("The fifth scale degree of %s %s is %s.  A %s %s is %s. %s is the correct answer.",
							this.key, this.scaleInstance.getQuality(), fifthString, interval, fifthString, this.answer, this.answer);
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
		return "Compound Scalar Intervals Down";
	}

}

