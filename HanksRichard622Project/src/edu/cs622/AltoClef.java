package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class AltoClef extends Clef{

	private String key;
	private Map<String, String> noteStaffMapping;
	
	public AltoClef(){
		this.key = "alto clef";
		this.noteStaffMapping = this.setNoteStaffMapping();
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



}
