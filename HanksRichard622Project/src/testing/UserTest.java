package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.User;

public class UserTest {
	User testUser = null;
	@Before
	public void setUp() throws Exception {
		// Instantiate the User object for testing
		this.testUser = new User("testUser1");
	}

	// Test that we can get scores from the JSON storage file
	@Test
	public void testCanGetScoresFromJSON() {
		Map<String, Integer> expectedScores = new HashMap<>();
		expectedScores.put("SimpleIntervalUpMajorScale", 0);
		expectedScores.put("SimpleIntervalDownMajorScale", 0);
		Map<String, Integer> actualScores = testUser.getScores();
		assertEquals(expectedScores, actualScores);
		
	}
	
	// Test that we can increment values to the json file and the resulting file has the new values
	@Test
	public void testAddScoreToJsonFile(){
		Integer expectedScoreAfterIncrement = this.testUser.getScores().get("SimpleIntervalUpMajorScale") + 1;
		this.testUser.incrementScore("SimpleIntervalUpMajorScale");
		Integer actualScoreAfterIncrement = this.testUser.getScores().get("SimpleIntervalUpMajorScale");
		assertEquals(expectedScoreAfterIncrement, actualScoreAfterIncrement);
	}

}
