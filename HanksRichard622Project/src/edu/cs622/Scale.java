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
	private static Map<String, Integer> getIntervalMapping() {
		Map<String, Integer> sampleMapping = new HashMap<>();
		sampleMapping.put("perfect unison", 0);
		sampleMapping.put("minor second above",  1);
		sampleMapping.put("minor 2nd below", -1);
		sampleMapping.put("major second above",  2);
		sampleMapping.put("major 2nd below", -2);
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
	
	
	public abstract Map<Integer, String> getPitchesByNumber();
	public abstract Map<String, Integer> getPitchesByName();
}
