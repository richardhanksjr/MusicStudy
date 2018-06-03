package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class AltoClef extends Clef{

	private String key;
	private Map<String, String> noteStaffMapping;
	private Map<String, Integer> noteStaffMappingToIntegers;
	
	public AltoClef(){
		this.key = "alto clef";
		this.noteStaffMapping = this.setNoteStaffMapping();
		this.noteStaffMappingToIntegers = this.setNoteStaffMappingToIntegers();
	}
	
	
	/**
	 * 
	 * @return A mapping where the key is the position of the note on the
	 * alto clef staff and the value is the Integer of the note, adjusted for octave.
	 * In other words, the not at the bottom of the staff is an octave below
	 * the note at the top of the staff, and the Integer values need to be calculated
	 * accordingly.
	 */
	private Map<String, Integer> setNoteStaffMappingToIntegers() {
		Map<String, Integer> tempMap = new HashMap<>();
		tempMap.put("first line",  5);
		tempMap.put("first space",  7);
		tempMap.put("second line",  9);
		tempMap.put("second space",  11);
		tempMap.put("third line",  12);
		tempMap.put("third space",  14);
		tempMap.put("fourth line",  16);
		tempMap.put("fourth space",  17);
		tempMap.put("fifth line",  19);
		return tempMap;
	}

	private Map<String, String> setNoteStaffMapping() {
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("first line", "F");
		tempMap.put("first space", "G");
		tempMap.put("second line",  "A");
		tempMap.put("second space", "B");
		tempMap.put("third line",  "C");
		tempMap.put("third space", "D");
		tempMap.put("fourth line", "E");
		tempMap.put("fourth space", "F");
		tempMap.put("fifth line",  "G");
		return tempMap;
	}
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public String getNoteAtStaffLocation(String staffLocation) {
		return this.noteStaffMapping.get(staffLocation);
	}
	
	@Override
	public Map<String, String> getNoteStaffMap(){
		return this.noteStaffMapping;
	}
	
	public Map<String, Integer> getNoteStaffMappingToIntegers(){
		return this.noteStaffMappingToIntegers;
	}



}
