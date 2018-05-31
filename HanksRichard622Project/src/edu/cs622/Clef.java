package edu.cs622;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Clef {
	public abstract String getKey();
	public abstract String getNoteAtStaffLocation(String staffLocation);
	public abstract Map<String, String> getNoteStaffMap();
	public static List<String> staffLocations(){
		List<String> staffLocations = new ArrayList<>(Arrays.asList("first line", "first space", "second line", "second space",
				"third line", "third space", "fourth line", "fourth space", "fifth line"));
		return staffLocations;
	}
	public static String getRandomStaffLocation(){
		Random rand = new Random();
		return staffLocations().get(rand.nextInt(staffLocations().size()));
	}
}
