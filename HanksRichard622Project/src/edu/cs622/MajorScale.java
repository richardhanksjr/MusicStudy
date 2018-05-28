package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class MajorScale extends SevenNoteAbstract{
	private String quality;
	static Map<Integer, String> modalDegreeNamesByNumber;
	

	/**
	 * Concrete scale class meant to represent a Major(Ionian) scale
	 */

	
	// Constructors
	public MajorScale(int root){
		this.root = root;
		// Set pitchesByNumber
		this.setPitchesToNameMappings();
		// Set modal degree names
		modalDegreeNames = this.setModalDegreeNames();
		modalDegreeNamesByNumber = setModalDegreeNumber();
		this.quality = "Major";
	}
	
	/**
	 * Sets the mapping between modal degree names and the number of half steps to that pitch from the fundamental
	 * @return the correct mapping between degree names and halfsteps above fundamental
	 */
	protected Map<String, Integer> setModalDegreeNames() {
		Map<String, Integer> tempMap = new HashMap<>();
		tempMap.put("tonic", 0);
		tempMap.put("supertonic", 2);
		tempMap.put("mediant", 4);
		tempMap.put("subdominant", 5);
		tempMap.put("dominant", 7);
		tempMap.put("submediant", 9);
		tempMap.put("leading tone", 11);
		return tempMap;
	}

	/**
	 * Sets the mapping between a scale degree NUMBER and it's scale degree name.  This is the 
	 * opposite lookup to m
	 */
	@Override
	protected Map<Integer, String> setModalDegreeNumber() {
		Map<Integer, String> tempMap = new HashMap<>();
		tempMap.put(0,  "tonic");
		tempMap.put(2,  "supertonic");
		tempMap.put(4,  "mediant");
		tempMap.put(5,  "subdominant");
		tempMap.put(7,  "dominant");
		tempMap.put(9,  "submediant");
		tempMap.put(11,  "leading tone");
		return tempMap;
	}
	
	public static String getModalDegreeNameByPitchNumber(int pitchNumber){
		return modalDegreeNamesByNumber.get(pitchNumber);
	}
	
	/**
	 * Returns the quality associated with the scale (i.e. Major, minor)
	 */
	@Override
	public String getQuality() {
		return this.quality;
	}

	@Override
	public int[] getDiatonicPitchRelationshipsForScaletype() {
		int[] diatonicPitches = {0, 2, 4, 5, 7, 9, 11};
		return diatonicPitches;
	}
}


