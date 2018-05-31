package edu.cs622;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Class for generating a question and answer to test downward intervals in a major scale
 * @author Richard Hanks
 *
 */

public class SimpleIntervalDownGeneric<E extends Scale> extends AbstractScalarIntervalQuestion{
	
	private String answerExplanation;
	private String[] incorrectAnswerOptions;


	// Constructors
	public SimpleIntervalDownGeneric(E e){
		this.scaleInstance = e;
		this.key = E.getPitchNameMapping().get(this.scaleInstance.getRoot());
		this.questionTemplate = "What is the note a %s the fifth scale degree in %s %s?";
		this.generateQuestionAndAnswer();
	}


	@Override
	public void generateQuestionAndAnswer() {
		// Get a random interval
		Random rand = new Random();
		Map<String, Integer> intervalMapping = Scale.getIntervalMapping();
		String randomKey = new ArrayList<String>(intervalMapping.keySet()).get(rand.nextInt(intervalMapping.keySet().size()));
		// Call overloaded method with random key
		this.generateQuestionAndAnswer(randomKey);
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
		this.answerExplanation = String.format("The fifth scale degree of %s %s is %s.  A %s %s is %s. %s is the correct answer.",
							this.key, this.scaleInstance.getQuality(), fifthString, interval, fifthString, this.answer, this.answer);
		this.incorrectAnswerOptions = this.setIncorrectAnswerOptions(answerInt);
	}


	/**
	 * Sets the incorrect answer options with options that are appropriate to the actual answer
	 * @return an array of incorrect answers
	 */
	private String[] setIncorrectAnswerOptions(int answerInt) {
		Random rand = new Random();
		Map<Integer, String> pitchNameMapping = E.getPitchNameMapping();
		ArrayList<Integer> keys = new ArrayList<>(pitchNameMapping.keySet());
		int numAnswers = 3;
		String[] answers = new String[numAnswers];
		int index;
		for(int i = 0; i < numAnswers; i++){
			index = rand.nextInt(keys.size());
			// If the random int is the answer, get another int until they are different
			while(index == answerInt){
				index = rand.nextInt(keys.size());
			}
			answers[i] = pitchNameMapping.get(index);
		}
		return answers;
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


	/**
	 * Return an array of answer options that are incorrect, appropriate to the type of answer (e.g., Letter, Roman Numeral)
	 */
	@Override
	public String[] getIncorrectAnswerOptions() {
		return this.incorrectAnswerOptions;
	}

}

