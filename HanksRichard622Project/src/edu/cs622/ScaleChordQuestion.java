package edu.cs622;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ScaleChordQuestion extends AbstractScalarIntervalQuestion{
	public static Map<List<Integer>, String> chordsMajorScale = createChordMappingMajorScale();
	public static Map<String, List<Integer>> intervalsInChordMajorScale = createIntervalsToRomanNumeralMappingMajorScale();

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
		tempChords.put(Arrays.asList(0, 4, 9 ), "vi");
		tempChords.put(Arrays.asList(2, 5, 11), "vii");
		return tempChords;
	}
	
	private static  Map<String, List<Integer>> createIntervalsToRomanNumeralMappingMajorScale(){
		Map<String, List<Integer>> tempIntervals = new HashMap<>();
		tempIntervals.put("I",Arrays.asList(0, 4, 7));
		tempIntervals.put("ii",  Arrays.asList(2, 5, 9));
		tempIntervals.put("iii",  Arrays.asList(4, 7, 11));
		tempIntervals.put("IV",  Arrays.asList(0, 5, 9));
		tempIntervals.put("V",  Arrays.asList(2, 7, 11));
		tempIntervals.put("vi", Arrays.asList(0, 4, 9));
		tempIntervals.put("vii",  Arrays.asList(2, 5, 11));
		return tempIntervals;
	}
	
	/**
	 * Provides a way to find all the chords in a MAJOR scale by their roman numeral representation.
	 * THIS IS THE REVERSE OF craeteChordMappingMajorScale
	 * @return the intervals in the chord reduced so that all intervals are within an octave.
	 */
	
	public static List<Integer> chordIntervalsLookupMajorScale(String romanNumeral){
		return intervalsInChordMajorScale.get(romanNumeral);
	}
	
	/**
	 * Given the intervals in a Major chord, returns a list of the scale degree names (e.g. tonic, mediant, etc.)
	 */
	
	public static List<String> scaleDegreeNamesForChordIntervals(List<Integer> chordIntervals){
		List<String> degreeNames = new ArrayList<>();
		for (int interval: chordIntervals){
			degreeNames.add(MajorScale.getModalDegreeNameByPitchNumber(interval));
		}
		return degreeNames;
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
