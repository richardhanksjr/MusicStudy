package edu.cs622;

/**
 * 
 * @author Richard Hanks
 * Concrete class of the <<<Question>>> and AbstactQuestion.
 * This class asks a simple question about diatonic intervals above
 * a diatonic root.
 *
 */

public class SimpleIntervalUpMajorScale extends AbstractQuestion{
	
	private Scale scaleInstance;
	private String key;
	private String quality;
	private String interval;
	private String modeDegreeName;

	// Constructors
	public SimpleIntervalUpMajorScale(int root){
		this.scaleInstance = new MajorScale(root);
		this.questionTemplate = "In the key of %s Major, what is the note a %s the %s?";
		// The key is represented by the String at the scaleInstance's root int
		this.key = this.scaleInstance.getScaleDegreeName(root);
		this.quality = this.getQuality();
		
	}
	
	/**
	 * Selects randomly from among the available diatonic scale qualities.  Used by the constructor to 
	 * set the quality instance variable.
	 * @return
	 */
	private String getQuality() {
		// TODO Auto-generated method stub
		return null;
	}


	public String generateQuestion(String key, String interval, String modeDegreeName) {
		return String.format(this.questionTemplate, key, interval, modeDegreeName);
	}

	@Override
	public String generateAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkAnswer() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Inherited method from the superclass.  This version of the method takes care of calling the 
	 * string formatted method with the proper values.  This should be the method to use as the default
	 * unless the client needs to call with specific values
	 * @return
	 */
	@Override
	public String generateQuestion() {
		
		return this.generateQuestion("C", "perfect", "fifth", "median");
	}

}
