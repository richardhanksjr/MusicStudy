package edu.cs622;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SimpleScaleChordQuestion<E extends Scale> extends ScaleChordQuestion {
	private E scaleInstance;
	// For storing the scale degree names to be looked up
	private String[] chordScaleDegrees;
	
	// Constructor
	public SimpleScaleChordQuestion(E e, String[] chordScaleDegrees){
		scaleInstance = e;
		this.chordScaleDegrees = chordScaleDegrees;
		// Generate question, answer, and explanation
		this.questionTemplate = "If you take the %s, %s, and %s in the key of %s %s, what is the roman numeral for the resulting chord?";
		this.generateQuestionAndAnswer();
	}
	@Override
	public void generateQuestionAndAnswer() {
		String rootName = Scale.pitchNameMapping.get(this.scaleInstance.getRoot());
		this.question = String.format(this.questionTemplate, chordScaleDegrees[0], chordScaleDegrees[1], chordScaleDegrees[2],
				rootName, this.scaleInstance.getQuality());
		// Get the answer
		List<Integer> chordDegreeNumbers = new LinkedList<>();
		chordDegreeNumbers.add(Scale.getModalDegreeNames().get(chordScaleDegrees[0]));
		chordDegreeNumbers.add(Scale.getModalDegreeNames().get(chordScaleDegrees[1]));
		chordDegreeNumbers.add(Scale.getModalDegreeNames().get(chordScaleDegrees[2]));
		this.answer = ScaleChordQuestion.chordLookup(chordDegreeNumbers);
	}

	@Override
	public String getExplanation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
