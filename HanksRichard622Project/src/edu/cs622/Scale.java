package edu.cs622;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Scale {
/**
 * @author - Richard Hanks
 * Interface for representing a music scale including instance variables and methods for accessing
 * scale information.
 * @param root int The root of the scale, also known as the tonic
 * @param pitchesByNumber Map<int, String> A collection of the pitches in the scale. The key is the int value of the
 * position in the scale.  The value is the String value of the pitch name
 * @param pitchesByName Map<String, int>  A collection of the pitches in the scale.  The key is the String value of the 
 * pitch name. The value is the int value of the position in the scale
 * @param static pitchNameMapping Map<Integer, String> is the mapping of all the the pitch numbers in the chromatic
 * scale to the names of the corresponding pitches
 * @param availableQualities String is the qualities of the intervals allowed for a given type of scale (ie. Perfect, Major);
 */
	protected int root;
	protected Map<Integer, String> pitchesByNumber;
	protected Map<String, Integer> pitchesByName;
	public static final Map<Integer, String> pitchNameMapping = getPitchNameMapping();
	public static final Map<String, Integer> IntervalMapping = getIntervalMapping();
	protected String[] availableQualities;
	public static Map<String, Integer> modalDegreeNames = setModalDegreeNames();
	public abstract String getQuality();
	
	/**
	 * Static method returns the pitch, as an int, a given number
	 * of halfSteps above the given fundamental
	 * @param fundamental The base of the interval from which to calculate halfSteps
	 * @param halfSteps The distance to the top notes, in half steps
	 * @return the int value associated with the upper note in the interval
	 */
	public static int getInterval(int fundamental, int halfSteps){
		// Ensure that going down an interval loops properly
		if (halfSteps < 0){
			int funPlusHalf = fundamental + halfSteps;
			while(funPlusHalf < -12){
				funPlusHalf += 12;
			}
			return ((funPlusHalf) + 12) % 12;
		}
		return (fundamental + halfSteps) % 12;
	}
	


	/**
	 * Creates the static constant mapping between the names of intervals and their integer values
	 * @return a Map<String, Integer> where the key is the name of the interval and the value is the Integer
	 * representation of the interval in half steps (aka semi tones)
	 * TODO this mapping is incomplete.  Need to finish to be fully functional!
	 */
	static Map<String, Integer> getIntervalMapping() {
		Map<String, Integer> sampleMapping = new HashMap<>();
		sampleMapping.put("perfect unison", 0);
		sampleMapping.put("minor second above",  1);
		sampleMapping.put("minor second below", -1);
		sampleMapping.put("major second above",  2);
		sampleMapping.put("major second below", -2);
		sampleMapping.put("minor third above", 3);
		sampleMapping.put("minor third below",  -3);
		sampleMapping.put("major third above", 4);
		sampleMapping.put("major third below",  -4);
		sampleMapping.put("perfect fourth above",  5);
		sampleMapping.put("perfect fourth below",  -5);
		sampleMapping.put("tritone above", 6);
		sampleMapping.put("tritone below",  -6);
		sampleMapping.put("perfect fifth below", -7);
		sampleMapping.put("perfect fifth above", 7);
		sampleMapping.put("minor sixth above", 8);
		sampleMapping.put("minor sixth below",  -8);
		sampleMapping.put("major sixth above",  9);
		sampleMapping.put("major sixth below",  -9);
		sampleMapping.put("minor seventh above",  10);
		sampleMapping.put("minor seventh below",  -10);
		sampleMapping.put("major seventh above",  11);
		sampleMapping.put("major seventh below",  -11);
		sampleMapping.put("perfect octave above",  12);
		sampleMapping.put("perfect octave below",  -12);
		return sampleMapping;

	}
	public abstract String getScaleDegreeName(int scaleDegree);
	public abstract int getScaleDegreePitch(String name);
	public abstract int getRoot();
	
	/**
	 * Method called to instantiate the static mapping of pitch numbers  to their corresponding pitch names
	 * @return The mapping of pitch numbers to their corresponding pitch names.  C is represented by 0 and 
	 * each chromatic step higher, 1/2 step, corresponds to the next letter name, including accidentals
	 */
	public static Map<Integer, String> getPitchNameMapping() {
		Map<Integer, String> placeHolderMapping = new HashMap<Integer, String>();
		placeHolderMapping.put(0, "C");
		placeHolderMapping.put(1,  "C#");
		placeHolderMapping.put(2,  "D");
		placeHolderMapping.put(3, "Eb");
		placeHolderMapping.put(4,  "E");
		placeHolderMapping.put(5,  "F");
		placeHolderMapping.put(6,  "F#");
		placeHolderMapping.put(7,  "G");
		placeHolderMapping.put(8,  "Ab");
		placeHolderMapping.put(9,  "A");
		placeHolderMapping.put(10,  "Bb");
		placeHolderMapping.put(11,  "B");
		
		return placeHolderMapping;
	}
	
	/**
	 * Sets the mapping between modal degree names and the number of half steps to that pitch from the fundamental
	 * @return the correct mapping between degree names and halfsteps above fundamental.  Since the details of this 
	 * mapping will vary based on scale quality, we only define the tonic here, since this is common to all scale qualities.
	 */
	private static Map<String, Integer> setModalDegreeNames() {
		Map<String, Integer> tempMap = new HashMap<>();
		tempMap.put("tonic", 0);
		return tempMap;
	}
	public abstract Map<Integer, String> getPitchesByNumber();
	public abstract Map<String, Integer> getPitchesByName();

	public static Map<String, Integer> getModalDegreeNames() {
		return modalDegreeNames;
	}

	/**
	 * Given a modal degree name, returns the int of the pitch at that position
	 * @param modeDegreeName the modal name of the pitch to find
	 * @return the int value of the pitch at that position in the key
	 * @precondition modeDegreeName is a proper name, spelled correctly
	 */
	public abstract int getPitchByModeDegree(String modeDegreeName);
		
}
