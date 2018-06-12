package edu.cs622;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class for abstracting the functionality common to all scales with 7 diatonic notes
 * @author Rich
 *
 */
public abstract class SevenNoteAbstract extends Scale {
	

	protected abstract  Map<String, Integer> setModalDegreeNames();
	protected abstract Map<Integer, String> setModalDegreeNumber();
//	protected abstract String getModalDegreeNameByPitchNumber(int pitchNumber);
	
	public String getPitchIntByScalePosition(String positionName) {
		// Map the scale position to the modal name
		String modalName = null;
		switch(positionName){
		case "first": modalName = "tonic";
		break;
		case "second": modalName = "supertonic";
		break;
		case "third": modalName = "mediant";
		break;
		case "fourth": modalName = "subdominant";
		break;
		case "fifth": modalName = "dominant";
		break;
		case "sixth": modalName = "submediant";
		break;
		case "seventh": modalName = "leading tone";
		break;
		}
		// Return the int at the modal name
//		return Scale.getPitchNameMapping().get(this.setModalDegreeNames().get(modalName));
		return modalName;
	}


	/**
	 * Returns the scale degree associated with a given int pitch
	 */
	@Override
	public String getScaleDegreeName(int scaleDegree) {
		return this.pitchesByNumber.get(scaleDegree);
	}
	
	/**
	 * 
	 * @return an int[] of the scale degrees that map to the pitch collection for this type of scale
	 */
	public abstract int[] getDiatonicPitchRelationshipsForScaletype();

	/**
	 * Given the name of a scale degree, returns the pitch as an int
	 */
	@Override
	public int getScaleDegreePitch(String name) {
		// Make sure the name is upper case to match the expected formatting
		String nameFormatted = name.toUpperCase();
		return this.pitchesByName.get(nameFormatted);
	}

	@Override
	public int getRoot() {
		return this.root;
	}

	@Override
	public Map<Integer, String> getPitchesByNumber() {
		return this.pitchesByNumber;
	}

	@Override
	public Map<String, Integer> getPitchesByName() {
		return this.pitchesByName;
	}

	/**
	 * Gets the pitch represented by the given mode name for the current key of the object
	 */
	@Override
	public int getPitchByModeDegree(String modeDegreeName) {
		// Get the int value of the number of halfsteps above the root for the given degree name
		int numHalfSteps = MajorScale.getModalDegreeNames().get(modeDegreeName);
		// calculate the pitch located the number of half steps above the root
		return Scale.getInterval(this.root, numHalfSteps);

	}
	
	/**
	 * Called by the constructor to set the relationship between the pitch number and the 
	 * pitch names.  THIS SETS BOTH MAPPINGS! In other words, where names are keys and where ints are keys
	 * @precondition the root is already set as a reference point for the rest of the pitches
	 * @postcondition the pitchesByNumber Map is instantiated
	 */
	protected void setPitchesToNameMappings() {
		// The pitch relationships of the major scale
		int[] diatonicPitches = this.getDiatonicPitchRelationshipsForScaletype();
		this.pitchesByNumber = new HashMap<Integer, String>();
		this.pitchesByName = new HashMap<String, Integer>();
		// For each element in the diatonicPitches array
		for (int i = 0; i < diatonicPitches.length; i++){
			// put an entry in the map where the key is the current index of the for loop
			// and the value is the value in the chromatic pitches map at the position indicated
			// by the value of the diatonicPitches array at this index
			int pitchCorrectedByTonic = (diatonicPitches[i] + this.root) % 12;
			this.pitchesByNumber.put(i,Scale.pitchNameMapping.get(pitchCorrectedByTonic));
			// Set the reverse mapping, where the pitch name is the key
			this.pitchesByName.put(Scale.pitchNameMapping.get(pitchCorrectedByTonic), i);
			
		}
	}
	
	/**
	 * @return a random string that maps to a scale position in a seven note scale (ex. "third", "fourth")
	 */
	public static String getRandomScalePosition(){
		List<String> scalePositions = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth",
				"fifth", "sixth", "seventh"));
		Random rand = new Random();
		return scalePositions.get(rand.nextInt(scalePositions.size()));
	}

}
