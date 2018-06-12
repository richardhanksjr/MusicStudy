package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class NaturalMinorScale extends SevenNoteAbstract {
	private String quality;
	static Map<Integer, String> modalDegreeNamesByNumber;
	
	public NaturalMinorScale(int root){
		this.root = root;
		// Set pitchesByNumber
		this.setPitchesToNameMappings();
		modalDegreeNames = this.setModalDegreeNames();
		modalDegreeNamesByNumber = setModalDegreeNumber();
		this.quality = "Natural Minor";
	}

	@Override
	protected Map<String, Integer> setModalDegreeNames() {
		Map<String, Integer> tempMap = new HashMap<>();
		tempMap.put("tonic", 0);
		tempMap.put("supertonic", 2);
		tempMap.put("mediant", 3);
		tempMap.put("subdominant", 5);
		tempMap.put("dominant", 7);
		tempMap.put("submediant", 8);
		tempMap.put("leading tone", 10);
		return tempMap;
	}

	@Override
	public int[] getDiatonicPitchRelationshipsForScaletype() {
		int[] diatonicPitches = {0, 2, 3, 5, 7, 8, 10};
		return diatonicPitches;
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

	@Override
	public String getQuality() {
		return this.quality;
	}



//	@Override
//	protected String getModalDegreeNameByPitchNumber(int pitchNumber) {
//		return modalDegreeNamesByNumber.get(pitchNumber);
//	}
	


}
