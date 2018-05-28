package edu.cs622;

public abstract class AbstractScalarIntervalQuestion extends AbstractQuestion{
	protected Scale scaleInstance;
	protected String key;
	protected String quality;
	protected String interval;
	protected String modeDegreeName;
	
	/**
	 * @Precondition the question has been instantiated
	 * @return The String representation of the question
	 */
	public String getQuestion() {
		return this.question;
	}

	public String getAnswer() {
		return this.answer;
	}


}
