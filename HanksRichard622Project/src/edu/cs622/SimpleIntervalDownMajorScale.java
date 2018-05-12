package edu.cs622;

/**
 * Class for generating a question and answer to test downward intervals in a major scale
 * @author Richard Hanks
 *
 */

public class SimpleIntervalDownMajorScale extends AbstractMajorIntervalQuestion{
	
	// Constructors
	public SimpleIntervalDownMajorScale(int root){
		this.scaleInstance = new MajorScale(root);
		this.key = Scale.getPitchNameMapping().get(root);
		this.questionTemplate = "What is the note a %s the fifth scale degree in %s Major?";
		this.generateQuestionAndAnswer();
	}

	@Override
	public String generateQuestion() {
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

	@Override
	public void generateQuestionAndAnswer() {
		this.generateQuestionAndAnswer("major 2nd below");
		
	}
	
	public void generateQuestionAndAnswer(String interval){
		this.question = String.format(this.questionTemplate, interval, this.key);
	}

}
