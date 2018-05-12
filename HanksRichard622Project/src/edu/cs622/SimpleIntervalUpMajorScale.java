package edu.cs622;

/**
 * 
 * @author Richard Hanks
 * Concrete class of the <<<Question>>> and AbstactQuestion.
 * This class asks a simple question about diatonic intervals above
 * a diatonic root.
 *
 */

public class SimpleIntervalUpMajorScale extends AbstractMajorIntervalQuestion{
	


	// Constructors
	public SimpleIntervalUpMajorScale(int root){
		this.scaleInstance = new MajorScale(root);
		this.questionTemplate = "In the key of %s Major, what is the note a %s the %s?";
		// The key is represented by the String at the scaleInstance's root int
		this.key = Scale.getPitchNameMapping().get(root);
		this.quality = this.getQuality();
		this.generateQuestionAndAnswer();
		
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
	 * TODO this is the place to randomize the specifics of the question.  For now, these are hard coded.
	 * @return
	 */
	@Override
	public void generateQuestionAndAnswer() {
		String key = this.scaleInstance.getPitchesByNumber().get(this.scaleInstance.getRoot());
		this.generateQuestionAndAnswer("C", "perfect fifth above", "mediant");
	}
	
	/**
	 * This version of the method receives the parameters either directly from the caller or from the no-args
	 * version of the method.
	 * @param key the Key of the major scale (aka, the root/tonic)
	 * @param interval the String that represents what to call the interval quality and direction
	 * @param modeDegreeName the greek scale degree name (ie, supertonic, mediant)
	 */
	public void generateQuestionAndAnswer(String key, String interval, String modeDegreeName) {
		this.question = String.format(this.questionTemplate, key, interval, modeDegreeName);
		// Figure out the answer
		// Find the modeDegreeName int of the scale
		int modeDegreeInt = this.scaleInstance.getPitchByModeDegree(modeDegreeName);
		// Find the interval as the half steps as an int
		int intervalHalfSteps = Scale.getIntervalMapping().get(interval);
		// Find the int that many half steps above the modeDegreeName int
		int finalNote = Scale.getInterval(modeDegreeInt, intervalHalfSteps);
		// Convert the int to its String representation
		this.answer = Scale.getPitchNameMapping().get(finalNote);
	}



	@Override
	public String generateQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
