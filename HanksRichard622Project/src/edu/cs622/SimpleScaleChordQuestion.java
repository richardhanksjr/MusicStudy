package edu.cs622;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimpleScaleChordQuestion<E extends MajorScale> extends ScaleChordQuestion {
	private E scaleInstance;
	// For storing the scale degree names to be looked up
	private String[] chordScaleDegrees;
	private String explanation;
	
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
		
//		Collections.sort(chordDegreeNumbers);
		this.answer = ScaleChordQuestion.chordLookupMajorScale(chordDegreeNumbers);
		// Get the names of the notes in the chord
		// Since the pitches we have are the RELATIONSHIPS of the intervals, and not the absolute pitches, we need to transpose by the pitch of the root
		// Turn the transposed pitches into the note name for formatting in the explanation.
		String note1 = Scale.getPitchNameMapping().get(Scale.getInterval(chordDegreeNumbers.get(0), this.scaleInstance.getRoot()));
		String note2 = Scale.getPitchNameMapping().get(Scale.getInterval(chordDegreeNumbers.get(1), this.scaleInstance.getRoot()));
		String note3 = Scale.getPitchNameMapping().get(Scale.getInterval(chordDegreeNumbers.get(2), this.scaleInstance.getRoot()));
		this.explanation = String.format("The %s is %s, the %s is %s, and the %s is %s.  In the key of %s Major this is the %s chord. %s is the answer.",
				chordScaleDegrees[0], note1, chordScaleDegrees[1], note2, chordScaleDegrees[2], note3, rootName,
				this.answer, this.answer);
	}

	@Override
	public String getExplanation() {
		return this.explanation;
	}

	@Override
	public String getKey() {
		return "Simple Scale Chords";
	}

}
