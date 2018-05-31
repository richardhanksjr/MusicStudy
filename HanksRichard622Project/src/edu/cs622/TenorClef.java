package edu.cs622;

import java.util.HashMap;
import java.util.Map;

public class TenorClef extends Clef {
	private String key;
	private Map<String, String> noteStaffMapping;
	
	public TenorClef(){
		this.key = "tenor clef";
		this.noteStaffMapping = this.setNoteStaffMapping();
	}
	
	private Map<String, String> setNoteStaffMapping() {
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("first line", "D");
		tempMap.put("first space", "E");
		tempMap.put("second line",  "F");
		tempMap.put("second space", "G");
		tempMap.put("third line",  "A");
		tempMap.put("third space", "B");
		tempMap.put("fourth line", "C");
		tempMap.put("fourth space", "D");
		tempMap.put("fifth line",  "E");
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
