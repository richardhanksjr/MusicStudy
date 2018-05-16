package edu.cs622;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * For keeping track of user profiles
 * @author Richard Hanks
 *
 */
public class User implements Scorable{
	private String userName;
	private Map<String, Integer> scores;
	
	public User(String username){
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setScores(Map<String, Integer> scores) {
		this.scores = scores;
	}
	@Override
	public Map<String, Integer> getScores() {
		Map<String, Integer> scores = new HashMap<>();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("scores.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject userScores = (JSONObject) jsonObject.get("testUser");
			System.out.println(userScores);
			Long SimpleIntervalUpMajorScale = (Long) userScores.get("SimpleIntervalUpMajorScale");
			System.out.println(SimpleIntervalUpMajorScale);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return scores;
	}
	@Override
	public Map<String, Integer> incrementScore(String questionType) {
		// TODO Auto-generated method stub
		return null;
	}

}
