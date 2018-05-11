package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


import edu.cs622.MajorScale;
import edu.cs622.Scale;

public class MajorScaleTest {
	Scale cMajorScale;

	@Before
	public void setUp() throws Exception {
		// Create a MajorScale instance for testing
		cMajorScale = new MajorScale(0);
	}

	// Test that getters return the proper values
	@Test
	public void testGetRoot() {
		assertEquals(0, cMajorScale.getRoot());

	}
	
   // Test that the object has access to the correct mapping between pitch numbers and pitch names
	@Test
	public void testCorrectMappingBetweenPitchesAndNamesInChromaticScale(){
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
		Set<Integer> placeHolderKeys = placeHolderMapping.keySet();
		Map<Integer, String> pitchNameMapping = MajorScale.getPitchNameMapping();
		Set<Integer> pitchNameMappingKeys = pitchNameMapping.keySet();
		assertEquals(placeHolderKeys, pitchNameMappingKeys);
		assertEquals(placeHolderMapping, pitchNameMapping);
	
		
	}
	
	// Test that the pitches by number are correct for the given root
	@Test
	public void testCorrectMappingBetweenPitchesAndNamesInMajorScale(){
		Map<Integer, String> placeHolderMapping = new HashMap<Integer, String>();
		placeHolderMapping.put(0, "C");
		placeHolderMapping.put(1, "D");
		placeHolderMapping.put(2, "E");
		placeHolderMapping.put(3,  "F");
		placeHolderMapping.put(4, "G");
		placeHolderMapping.put(5,  "A");
		placeHolderMapping.put(6,  "B");
		
		Map<Integer, String> pitchesByNumber = cMajorScale.getPitchesByNumber();
		assertEquals(placeHolderMapping, pitchesByNumber);
	}
	
	// Test that the pitches by name mapping is correct
	@Test
	public void testCorrectMappingBetweenNamesAndPitchesInMajorScale(){
		Map<String, Integer> placeHolderMapping = new HashMap<String, Integer>();
		placeHolderMapping.put("C",  0);
		placeHolderMapping.put("D",  1);
		placeHolderMapping.put("E",  2);
		placeHolderMapping.put("F",  3);
		placeHolderMapping.put("G",  4);
		placeHolderMapping.put("A",  5);
		placeHolderMapping.put("B",  6);
		Map<String, Integer> pitchesByName = cMajorScale.getPitchesByName();
		assertEquals(placeHolderMapping, pitchesByName);
	}
	
	// Test getting the interval between two notes
	@Test
	public void testIntervalBetweenTwoNotes(){
		assertEquals(5, Scale.getInterval(0, 5));
		assertEquals(0, Scale.getInterval(0,  12));
		assertEquals(4, Scale.getInterval(5,  -1));
		assertEquals(11, Scale.getInterval(0,  -1));
	}
	
	// Get a scale degree name given an int pitch
	@Test
	public void testGetScaleDegreeNameForIntPitch(){
		assertEquals("C", cMajorScale.getScaleDegreeName(0));
		assertEquals("F", cMajorScale.getScaleDegreeName(3));
		assertNull(cMajorScale.getScaleDegreeName(13));
	}
	
	// Test getting a scale degree int given a pitch name
	@Test
	public void testGetScaleDegreeIntGivenName(){
		assertEquals(0, cMajorScale.getScaleDegreePitch("C"));
	}

}
