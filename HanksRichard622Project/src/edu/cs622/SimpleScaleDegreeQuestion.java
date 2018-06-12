package edu.cs622;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Question for identifying scale degrees in a scale by their position. Ex.  What is the third scale degree in D Major?
 * This class is only for 7 note scales that derive from SevenNoteAbstract.  This will not include, as an example, chromatic scales, whole tone scales, etc.
 * @author Rich
 *
 */
public class SimpleScaleDegreeQuestion<E extends SevenNoteAbstract> extends AbstractQuestion{

	private E scaleInstance;
	private String scaleDegreePosition;
	private String explanation;
	private String key;
	private String[] incorrectAnswerOptions;
	public SimpleScaleDegreeQuestion(E e, String scaleDegreePosition) {
		this.scaleInstance = e;
		this.scaleDegreePosition = scaleDegreePosition;
		this.questionTemplate = "What is the %s scale degree of %s %s?";
		// Generate question, answer, and explanation
		this.generateQuestionAndAnswer();
		this.key = "Simple Scale Degree";
	}

	@Override
	public String getQuestion() {
		return this.question;
	}

	@Override
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * Calls the overloaded method with a "default" param
	 */
	@Override
	public void generateQuestionAndAnswer() {
		this.question = String.format(this.questionTemplate,
				this.scaleDegreePosition, Scale.getPitchNameMapping().get(this.scaleInstance.getRoot()), this.scaleInstance.getQuality());
		// generate answer
		// Get the modal name that maps to the scaleDegreePosition and then the representative int
		String scalePositionAsModalName = this.scaleInstance.getPitchIntByScalePosition(this.scaleDegreePosition);
		// Get the int that represents the scaleDegreePostion in the given key (aka, scaleDegreePosition above root)
		int scaleDegreeAsInt = this.scaleInstance.setModalDegreeNames().get(scalePositionAsModalName);
		int transposedScaleDegree = Scale.getInterval(this.scaleInstance.getRoot(), scaleDegreeAsInt);
		// Convert to the String representation of that interval
		this.answer = Scale.pitchNameMapping.get(transposedScaleDegree);
		this.explanation = String.format("The %s scale degree of %s %s is %s.  %s is the correct answer.",
				this.scaleDegreePosition, Scale.getPitchNameMapping().get(this.scaleInstance.getRoot()),
				this.scaleInstance.getQuality(), this.answer, this.answer);
		this.incorrectAnswerOptions = this.setIncorrectAnswerOptions(this.answer);
	}

	
	@Override
	public String getExplanation() {
		return this.explanation;
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	private String[] setIncorrectAnswerOptions(String answer) {
		Random rand = new Random();
		String[] choicesHolder = new String[3];
		List<String> answerOptions = SevenNoteAbstract.notesInChromaticScale();
		String currentRandomChoice = null;
		for(int i = 0; i < choicesHolder.length; i++){
			do{
				currentRandomChoice = answerOptions.get(rand.nextInt(answerOptions.size()));
			}while(currentRandomChoice.equals(this.answer) || Arrays.asList(choicesHolder).contains(currentRandomChoice));
			choicesHolder[i] = currentRandomChoice;
		}
		return choicesHolder;
	}

	@Override
	public String[] getIncorrectAnswerOptions() {
		return this.incorrectAnswerOptions;
	}

}
