package edu.cs622;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScaleChordQuestion extends AbstractScalarIntervalQuestion{
	public static Map<List<Integer>, String> chordsMajorScale = createChordMappingMajorScale();

	/**
	 * This creates a mapping between chord intervals and their corresponding Roman numeral
	 * This mapping doesn't put everything in root position but, rather, reduces all intervals be within one octave of the 
	 * key root.  For example, the iii chord is 4, 9, 12 in root position, but is instead formatted like 0, 4, 9.  This
	 * allows the lookup of a chord regardless of the inversion. THIS MAPPING ONLY WORKS FOR MAJOR SCALE!!!
	 * @return The mapping between chord intervals and their Roman numeral
	 */
	private static Map<List<Integer>, String> createChordMappingMajorScale() {
		Map<List<Integer>, String> tempChords = new HashMap<>();
		tempChords.put(Arrays.asList(0, 4, 7), "I");
		tempChords.put(Arrays.asList(2, 5, 9), "ii");
		tempChords.put(Arrays.asList(4, 7, 11), "iii");
		tempChords.put(Arrays.asList(0, 5, 9), "IV");
		tempChords.put(Arrays.asList(2, 7, 11), "V");
		tempChords.put(Arrays.asList(0, 9, 4 ), "VI");
		tempChords.put(Arrays.asList(11, 2, 5), "vii");
		return tempChords;
	}
	
	/**
	 * Given a triad of chord intervals, returns the corresponding Roman numeral
	 * @param intervals
	 * @return
	 */
	public static String chordLookupMajorScale(List<Integer> intervals){
		// Order the intervals to be in ascending order, which is the expected format of the lookup keys
		List<Integer> innerCopy = new ArrayList<>(intervals);
		Collections.sort(innerCopy);
		return chordsMajorScale.get(innerCopy);
	}
}
