package edu.cs622;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleClefQuestion<E extends Clef> extends AbstractQuestion{

	/**
	 * Constructor takes the location on the staff where the note is to be found
	 * @param staffLocation
	 */
	private String staffLocation;
	private E clef;
	private String question;
	private String questionTemplate;
	private String explanation;
	private String key;
	private String[] incorrectAnswerOptions;
	
	public SimpleClefQuestion(E e, String staffLocation) {
		this.staffLocation = staffLocation;
		this.clef = e;
		this.key = "Simple Clef Question";
		this.questionTemplate = "What is the %s of the %s staff?";
		this.generateQuestionAndAnswer();
	}

	@Override
	public String getQuestion() {
		return this.question;
	}

	@Override
	public String getAnswer() {
		return this.answer;
	}

	@Override
	public void generateQuestionAndAnswer() {
		this.question = String.format(this.questionTemplate, staffLocation, this.clef.getKey());
		// Generate the answer
		// Get the note at the given staffLocation
		this.answer = this.clef.getNoteAtStaffLocation(this.staffLocation);
		this.explanation = String.format("The %s of the %s is %s.  The correct answer is %s",
				this.staffLocation, clef.getKey(), this.answer, this.answer);
		this.incorrectAnswerOptions = this.setIncorrectAnswerOptions();
	}

	private String[] setIncorrectAnswerOptions() {
		Random rand = new Random();
//		ArrayList<String> keys = new ArrayList<>(clef.getNoteStaffMap().keySet());
 		List<String> keys = new ArrayList<>(Clef.staffLocations());
		int numAnswers = 3;
		String[] answers = new String[numAnswers];
		int index;
		String randomAnswer;
		for(int i = 0; i < numAnswers; i++){
			index = rand.nextInt(keys.size());
			randomAnswer = clef.getNoteStaffMap().get(keys.get(index));
			// If the randomAnswer and the actual answer are the same, try again
			while(randomAnswer.equals(this.answer)){
				index = rand.nextInt(keys.size());
				randomAnswer = clef.getNoteStaffMap().get(keys.get(index));
			}
			answers[i] = randomAnswer;
		}
		return answers;
	}

	@Override
	public String getExplanation() {
		return this.explanation;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String[] getIncorrectAnswerOptions() {
		return this.incorrectAnswerOptions;
	}

}
