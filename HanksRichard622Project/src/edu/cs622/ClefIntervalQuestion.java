package edu.cs622;

import java.util.List;
import java.util.Random;

public class ClefIntervalQuestion<E extends Clef> extends AbstractQuestion {
	private String firstStaffLocation;
	private String secondStaffLocation;
	private E e;
	private String explanation;
	private String[] incorrectAnswerOptions;
	
	public ClefIntervalQuestion(E e, String firstStaffLocation, String secondStaffLocation){
		this.firstStaffLocation = firstStaffLocation;
		this.secondStaffLocation = secondStaffLocation;
		this.e = e;
		this.questionTemplate = "What is the interval between the %s of the %s and the %s of the %s?";
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
		this.question = String.format(this.questionTemplate, this.firstStaffLocation,
				e.getKey(), this.secondStaffLocation, e.getKey());
		// Generate answer
		// Get the note int at the first location
		System.out.println("first location: " + this.firstStaffLocation);
		int firstLocationAsInt = e.getNoteStaffMappingToIntegers().get(this.firstStaffLocation);
		String firstLocationName = e.getNoteStaffMap().get(this.firstStaffLocation);
		// Get the note int at the second location
		System.out.println("second staff location: " + this.secondStaffLocation);

		int secondLocationAsInt = e.getNoteStaffMappingToIntegers().get(this.secondStaffLocation);
		String secondLocationName = e.getNoteStaffMap().get(this.secondStaffLocation);
		// return the interval name for that interval
		this.answer = Scale.getIntervalMappingByInt().get(secondLocationAsInt - firstLocationAsInt);
		this.explanation = String.format("The %s of the %s is %s.  The %s of the %s is %s.  %s is a %s %s.",
				this.firstStaffLocation, e.getKey(), firstLocationName, this.secondStaffLocation,
				e.getKey(), secondLocationName, secondLocationName, this.answer, firstLocationName);
		// Generate incorrect answers
		this.incorrectAnswerOptions = this.setIncorrectAnswerOptions();
		for(String elem: this.incorrectAnswerOptions){
			System.out.println(elem);
		}
}

	private String[] setIncorrectAnswerOptions() {
		String[] tempArr = new String[3];
		//For the length of the array
		String randElem;
		for(int i = 0; i < tempArr.length; i++){
			do{
				randElem = Scale.getRandomIntervalKey();
			}while(randElem.equals(this.answer));
			tempArr[i] = randElem;
		}
		return tempArr;		
	}


	@Override
	public String getExplanation() {
		return this.explanation;
	}

	@Override
	public String getKey() {
		return "Clef Interval Question";
	}

	@Override
	public String[] getIncorrectAnswerOptions() {
		return this.incorrectAnswerOptions;
	}

}
