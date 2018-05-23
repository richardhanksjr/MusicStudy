package edu.cs622;

public class SimpleIntervalUpGeneric<E extends Scale> extends AbstractQuestion {
	private String explanation;
	private String key;
	private E scaleInstance;



	// Constructors
	public SimpleIntervalUpGeneric(E e){
		this.scaleInstance = e;
		this.questionTemplate = "In the key of %s %s, what is the note a %s the %s?";
		// The key is represented by the String at the scaleInstance's root int
		this.key = E.getPitchNameMapping().get(e.getRoot());
		this.generateQuestionAndAnswer();
		
	}
	



	/**
	 * Inherited method from the superclass.  This version of the method takes care of calling the 
	 * string formatted method with the proper values.  This should be the method to use as the default
	 * unless the client needs to call with specific values
	 * TODO this is the place to randomize the specifics of the question.  For now, these are hard coded.
	 * @return
	 */
	@Override
	public void generateQuestionAndAnswer() {
		//String key = this.scaleInstance.getPitchesByNumber().get(this.scaleInstance.getRoot());
		this.generateQuestionAndAnswer(this.key, "perfect fifth above", "mediant");
	}
	
	/**
	 * This version of the method receives the parameters either directly from the caller or from the no-args
	 * version of the method.
	 * @param key the Key of the major scale (aka, the root/tonic)
	 * @param interval the String that represents what to call the interval quality and direction
	 * @param modeDegreeName the greek scale degree name (ie, supertonic, mediant)
	 */
	public void generateQuestionAndAnswer(String key, String interval, String modeDegreeName) {
		this.question = String.format(this.questionTemplate, key, this.scaleInstance.getQuality(), interval,  modeDegreeName);
		// Figure out the answer
		// Find the modeDegreeName int of the scale
		int modeDegreeInt = this.scaleInstance.getPitchByModeDegree(modeDegreeName);
		// Find the interval as the half steps as an int
		int intervalHalfSteps = E.getIntervalMapping().get(interval);
		// Find the int that many half steps above the modeDegreeName int
		int finalNote = E.getInterval(modeDegreeInt, intervalHalfSteps);
		// Convert the int to its String representation
		this.answer = E.getPitchNameMapping().get(finalNote);
		// Set the explanation
		String modeDegreeAsPitchName = E.getPitchNameMapping().get(modeDegreeInt);
		this.explanation = String.format("The %s of %s %s is %s.  The note a %s %s is %s.  The answer is %s.",
				modeDegreeName, key, this.scaleInstance.getQuality(), modeDegreeAsPitchName, interval, modeDegreeAsPitchName, this.answer, this.answer);
	}



	/**
	 * @return an explanation about how we arrived at the code.
	 * 
	 */
	@Override
	public String getExplanation() {
		return this.explanation;
	}



	/**
	 * return the key used for this question to update the scorign
	 */
	@Override
	public String getKey() {
		return "SimpleIntervalUpMajorScale";
	}




	public String getQuestion() {
		return this.question;
	}




	public String getAnswer() {
		return this.answer;
	}

}
