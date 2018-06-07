package testing;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.UserDatabase;

public class UserDatabaseTest {

	UserDatabase user;
	@Before
	public void setUp() throws Exception {
		user = new UserDatabase("test user3");
	}

	@Test
	public void testForGetScores() {
		user.getScores();
	}

}
