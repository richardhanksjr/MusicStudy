package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.MajorScale;
import edu.cs622.NaturalMinorScale;
import edu.cs622.Scale;

public class NaturalMinorScaleTest {
	Scale cNaturalMinor;

	@Before
	public void setUp() throws Exception {
		// Create a new NaturalMinorScale for testing
		cNaturalMinor = new NaturalMinorScale(0);
	}

	// Test that getters return the proper values
	@Test
	public void testGetRoot() {
		assertEquals(0, cNaturalMinor.getRoot());

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
			Map<Integer, String> pitchNameMapping = NaturalMinorScale.getPitchNameMapping();
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
			placeHolderMapping.put(2, "Eb");
			placeHolderMapping.put(3,  "F");
			placeHolderMapping.put(4, "G");
			placeHolderMapping.put(5,  "Ab");
			placeHolderMapping.put(6,  "Bb");
			
			Map<Integer, String> pitchesByNumber = cNaturalMinor.getPitchesByNumber();
			assertEquals(placeHolderMapping, pitchesByNumber);
		}
		
		// Test that the pitches by name mapping is correct
		@Test
		public void testCorrectMappingBetweenNamesAndPitchesInMajorScale(){
			Map<String, Integer> placeHolderMapping = new HashMap<String, Integer>();
			placeHolderMapping.put("C",  0);
			placeHolderMapping.put("D",  1);
			placeHolderMapping.put("Eb",  2);
			placeHolderMapping.put("F",  3);
			placeHolderMapping.put("G",  4);
			placeHolderMapping.put("Ab",  5);
			placeHolderMapping.put("Bb",  6);
			Map<String, Integer> pitchesByName = cNaturalMinor.getPitchesByName();
			assertEquals(placeHolderMapping, pitchesByName);
		}
		
		// Test getting the interval between two notes
		@Test
		public void testIntervalBetweenTwoNotes(){
			assertEquals(5, Scale.getInterval(0, 5));
			assertEquals(0, Scale.getInterval(0,  12));
			// Check for very large intervals above
			assertEquals(0, Scale.getInterval(0,  (12 + (12 * 400))));
			assertEquals(4, Scale.getInterval(5,  -1));
			assertEquals(11, Scale.getInterval(0,  -1));
			assertEquals(11, Scale.getInterval(0,  -13));
			// Check for very large intervals below
			assertEquals(11, Scale.getInterval(0,  (-13 + (12 * 400))));
		}
		
		// Get a scale degree name given an int pitch
		@Test
		public void testGetScaleDegreeNameForIntPitch(){
			assertEquals("C", cNaturalMinor.getScaleDegreeName(0));
			assertEquals("F", cNaturalMinor.getScaleDegreeName(3));
			// Given a value that doesn't exist, returns null
			assertNull(cNaturalMinor.getScaleDegreeName(13));
		}
		
		// Test getting a scale degree int given a pitch name
		@Test
		public void testGetScaleDegreeIntGivenName(){
			assertEquals(0, cNaturalMinor.getScaleDegreePitch("C"));
		}
		
		// Incomplete test of the mapping of interval names (ie, perfect 4th asc, minor 3rd desc)
//		@Test
//		public void testIntervalNamesMappedToIntervalIntegers(){
//			Map<String, Integer> sampleMapping = new HashMap<>();
//			sampleMapping.put("perfect unison", 0);
//			sampleMapping.put("minor second above",  1);
//			sampleMapping.put("minor second below", -1);
//			sampleMapping.put("major second above",  2);
//			sampleMapping.put("major second below", -2);
//			sampleMapping.put("perfect fifth above", 7);
//			assertEquals(sampleMapping, Scale.IntervalMapping);
//		}
		
		// Test the mapping between modal degree names and the number of half steps
		// above the tonic
		@Test
		public void testModalDegreeNamesMapping(){
			Map<String, Integer> tempMap = new HashMap<>();
			tempMap.put("tonic", 0);
			tempMap.put("supertonic", 2);
			tempMap.put("mediant", 3);
			tempMap.put("subdominant", 5);
			tempMap.put("dominant", 7);
			tempMap.put("submediant", 8);
			tempMap.put("leading tone", 10);
			assertEquals(tempMap, Scale.getModalDegreeNames());
		}
		
		
		// Test the ability to pass the modal scale degree name to a Scale
		// instance and get back the int representation of that pitch
		@Test
		public void testGetPitchRepresentedByModalNameInAKey(){
			// In the key of C, the supertonic is mapped by the pitch 2
			int expectedSupertonic = 2;
			int actualPitch = cNaturalMinor.getPitchByModeDegree("supertonic");
			assertEquals(expectedSupertonic, actualPitch);
			// leading tone should be represented by the pitch 11
			int expectedLeadingTone = 10;
			int actualLeadingTone = cNaturalMinor.getPitchByModeDegree("leading tone");
			assertEquals(expectedLeadingTone, actualLeadingTone);
		}
		
		// Check that this scale, a Major scale, returns "Major" as it's quality
		@Test
		public void testReturnsCorrectScaleQuality(){
			String expectedQuality = "Natural Minor";
			String actualQuality = cNaturalMinor.getQuality();
			assertEquals(expectedQuality, actualQuality);
		}

}
