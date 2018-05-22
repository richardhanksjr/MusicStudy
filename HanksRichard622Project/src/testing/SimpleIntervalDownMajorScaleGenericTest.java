package testing;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.cs622.AbstractQuestion;
import edu.cs622.MajorScale;
import edu.cs622.SimpleIntervalDownMajorScaleGeneric;

public class SimpleIntervalDownMajorScaleGenericTest {

	@Before
	public void setUp() throws Exception {
		AbstractQuestion scale = new SimpleIntervalDownMajorScaleGeneric<MajorScale>(new MajorScale(5));
		System.out.println(scale.getExplanation());
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
