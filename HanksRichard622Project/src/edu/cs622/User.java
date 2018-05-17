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
	private String fileName = "scores.json";
	
	public User(String userName){
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
			FileWriter file = new FileWriter(this.fileName);
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
		if(!jsonObject.containsKey(this.userName)){
			this.addUserToFile(jsonObject);
		}
		return (JSONObject) jsonObject.get(this.userName);
	}
	
	/**
	 * Adds the current user to the storage file if the current user isn't already in the file
	 */
	private void addUserToFile(JSONObject jsonObject) {
		if(jsonObject.containsKey(this.userName)){
			return;
		}
		// Create innermost JSON object that maps question types to the default starting value, 0
		JSONObject innermost = new JSONObject();
		// For each category name in categoryNames
		JSONArray categoryNames = (JSONArray) jsonObject.get("categoryNames");
		// add to the innermost JSON object with the category name as key and 0 as the value
		for(int i = 0; i < categoryNames.size(); i++){
			innermost.put(categoryNames.get(i), 0);
		}
		// Create a JSON object for the username
//		JSONObject newUser = new JSONObject();
//		// add the innermost json object to the usename object
//		newUser.put(this.userName, innermost);
//		System.out.println(newUser);
		// add the user json object to the given jsonObject
		jsonObject.put(this.userName, innermost);
		// write to the file
		System.out.println("jsonObject is: " + jsonObject);
		
	}
	private JSONObject getJsonObjectFromFile(){
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(this.fileName));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}

}
