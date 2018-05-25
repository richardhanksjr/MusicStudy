package edu.cs622;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScaleChordQuestion extends AbstractScalarIntervalQuestion{
	public static Map<List<Integer>, String> chords = createChordMapping();

	/**
	 * This creates a mapping between chord intervals and their corresponding Roman numeral
	 * @return The mapping between chord intervals and their Roman numeral
	 */
	private static Map<List<Integer>, String> createChordMapping() {
		Map<List<Integer>, String> tempChords = new HashMap<>();
		tempChords.put(Arrays.asList(0, 4, 7), "I");
		tempChords.put(Arrays.asList(2, 5, 9), "ii");
		tempChords.put(Arrays.asList(4, 9, 12), "iii");
		return tempChords;
	}
	
	public static String chordLookup(List<Integer> intervals){
		return chords.get(intervals);
	}

}
