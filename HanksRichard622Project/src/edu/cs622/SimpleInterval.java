package edu.cs622;

/**
 * Class that asks simple questions like "What is the note a perfect fifth above c?"
 * @param key String the string representation of this question type
 * @param base the note from which to derive the interval.  The "starting note."
 * @param interval String the name of the interval which maps to the number of half-steps away from base
 * @author Rich
 *
 */

public class SimpleInterval extends AbstractQuestion {

	private String key;
	private int base;
	private String interval;
	private String answerExplanation;
	
	public SimpleInterval(int base, String interval){
		this.key = "Simple Interval";
		this.questionTemplate = "What is the note a %s %s?";
		this.base = base;
		this.interval = interval;
		// Generate the question, answer,and explanation
		this.generateQuestionAndAnswer();
	}
	
	/**
	 * 
	 * This method does the heavy lifting for this class by creating the question, the answer, and the explanation
	 */
	@Override
	public void generateQuestionAndAnswer() {
		// Generate the question
		String baseAsString = Scale.pitchNameMapping.get(this.base);
		this.question = String.format(this.questionTemplate, this.interval, baseAsString);
		// Generate the answer
		int intervalAsInt = Scale.IntervalMapping.get(this.interval);
		int answerAsInt = Scale.getInterval(this.base, intervalAsInt);
		this.answer = Scale.pitchNameMapping.get(answerAsInt);
		this.answerExplanation = String.format("The note a %s %s is %s.  The answer is %s.", this.interval,
				baseAsString, this.answer, this.answer);
	}


	@Override
	public String getExplanation() {
		return this.answerExplanation;
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public String getQuestion() {
		return this.question;
	}
	
	@Override
	public String getAnswer() {
		return this.answer;
	}

}
