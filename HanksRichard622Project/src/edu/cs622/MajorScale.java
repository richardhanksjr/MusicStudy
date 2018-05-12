package edu.cs622;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MajorScale extends Scale{
	/**
	 * Concrete scale class meant to represent a Major(Ionian) scale
	 */

	
	// Constructors
	public MajorScale(int root){
		this.root = root;

		// Set pitchesByNumber
		this.setPitchesToNameMappings();


	}

	
	/**
	 * Called by the constructor to set the relationship between the pitch number and the 
	 * pitch names.  THIS SETS BOTH MAPPINGS! In other words, where names are keys and where ints are keys
	 * @precondition the root is already set as a reference point for the rest of the pitches
	 * @postcondition the pitchesByNumber Map is instantiated
	 */
	private void setPitchesToNameMappings() {
		// The pitch relationships of the major scale
		int[] diatonicPitches = {0, 2, 4, 5, 7, 9, 11};
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
	 * Returns the scale degree associated with a given int pitch
	 */
	@Override
	public String getScaleDegreeName(int scaleDegree) {
		return this.pitchesByNumber.get(scaleDegree);
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
	 * Given the name of a scale degree, returns the pitch as an int
	 */
	@Override
	public int getScaleDegreePitch(String name) {
		// Make sure the name is upper case to match the expected formatting
		String nameFormatted = name.toUpperCase();
		return this.pitchesByName.get(nameFormatted);
	}


	/**
	 * Gets the pitch represented by the given mode name for the current key of the object
	 */
	@Override
	public int getPitchByModeDegree(String modeDegreeName) {
		// Get the int value of the number of halfsteps above the root for the given degree name
		int numHalfSteps = Scale.getModalDegreeNames().get(modeDegreeName);
		// calculate the pitch located the number of half steps above the root
		return Scale.getInterval(this.root, numHalfSteps);

	}

}
