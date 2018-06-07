package edu.cs622;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserDatabase extends User{
	Database db;

	public UserDatabase(String userName) {
		super(userName);
		db = new Database();
	}
	
	@Override
	public Map<String, Integer> getScores(){
		if(!db.checkIfUserExists(this.getUserName())){
			db.createNewUser(this.getUserName());
		}
		Map<String, Integer> localScores = db.getAllScoresForUser(this.getUserName());
		return localScores;
	}
	
	public Map<String, Integer> incrementScore(String questionType){
		if(!db.checkIfUserExists(this.getUserName())){
			db.createNewUser(this.getUserName());
		}
		db.incrementScoreForUser(this.getUserName(), questionType);
		return this.getScores();
	}

}
