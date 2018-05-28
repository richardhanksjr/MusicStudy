package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;
import edu.cs622.User;

public class UserTest {
	User testUser = null;
	@Before
	public void setUp() throws Exception {
		// Instantiate the User object for testing
		// Create a random user name
		String userName = RandomStringUtils.random(10, true, false);
		this.testUser = new User(userName);
	}

	// Test that we can get scores from the JSON storage file
//	@Test
//	public void testCanGetScoresFromJSON() {
//		Map<String, Integer> expectedScores = new HashMap<>();
//		expectedScores.put("Compound Scalar Intervals Up", 0);
//		expectedScores.put("Compound Scalar Intervals Down", 0);
//		Map<String, Integer> actualScores = testUser.getScores();
//		assertEquals(expectedScores, actualScores);
//		
//	}
	
	// Test that we can increment values to the json file and the resulting file has the new values
	@Test
	public void testAddScoreToJsonFile(){
		Integer expectedScoreAfterIncrement = this.testUser.getScores().get("Compound Scalar Intervals Up") + 1;
		this.testUser.incrementScore("Compound Scalar Intervals Up");
		Integer actualScoreAfterIncrement = this.testUser.getScores().get("Compound Scalar Intervals Up");
		assertEquals(expectedScoreAfterIncrement, actualScoreAfterIncrement);
	}
	
	// Test that we can get back a mapping of a given question type and the current score
	// for that user based on the given question type as the parameter
	@Test
	public void testGetSpecificScoreByType(){
		Map<String, Integer> expectedScores = new HashMap<>();
		expectedScores.put("Compound Scalar Intervals Up", 0);
		Map<String, Integer> actualScores = this.testUser.getSpecificScore("Compound Scalar Intervals Up");
		assertEquals(expectedScores, actualScores);
		
		
	}

}
