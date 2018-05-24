package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class NaturalMinorScale extends SevenNoteAbstract {
	private String quality;
	
	public NaturalMinorScale(int root){
		this.root = root;
		// Set pitchesByNumber
		this.setPitchesToNameMappings();
		modalDegreeNames = this.setModalDegreeNames();
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

	@Override
	public String getQuality() {
		return this.quality;
	}
	


}
