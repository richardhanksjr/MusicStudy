package edu.cs622;

import static java.lang.Math.toIntExact;

import java.io.FileReader;
import java.io.FileWriter;
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
	private JSONParser parser = new JSONParser();
	
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
	
	/**
	 * For this user, get all of the scores
	 * @return
	 */
	@Override
	public Map<String, Integer> getScores() {
		Map<String, Integer> scores = new HashMap<>();
		JSONObject jsonObject = this.getJsonObjectFromFile();
		JSONObject userScores = this.getScoresJsonObject(jsonObject);
		// Get the expected names/keys for each of the score categories
		JSONArray categoryNames = (JSONArray) jsonObject.get("categoryNames");
		for(int i = 0; i < categoryNames.size(); i++ ){
			String key = (String) categoryNames.get(i);
			// A little funky here, but this is a way to pull out an int from the JSON file
			Integer score = toIntExact((Long)userScores.get(key));
			scores.put(key,  score);
		}

		return scores;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> incrementScore(String questionType) {
		// Get the current score for this questionType
		Integer currentScore = this.getScores().get(questionType);
		JSONObject jsonObject = this.getJsonObjectFromFile();
		JSONObject userScores = this.getScoresJsonObject(jsonObject);
		//update the score
		userScores.put(questionType, ++currentScore);
		System.out.println(jsonObject);
		try {
			@SuppressWarnings("resource")
			FileWriter file = new FileWriter("scores.json");
			file.write(jsonObject.toJSONString());
			file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return the updated scores for the user
		System.out.println(this.getScores());
		return this.getScores();
	}
	
	private JSONObject getScoresJsonObject(JSONObject jsonObject){
		return (JSONObject) jsonObject.get("testUser");
	}
	
	private JSONObject getJsonObjectFromFile(){
		Object obj = null;
		try {
			obj = parser.parse(new FileReader("scores.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}

}
