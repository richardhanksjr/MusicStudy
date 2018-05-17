package edu.cs622;

import java.util.Map;

/**
 * 
 * @author Richard Hanks
 * Interface for classes tasked with keep track of the score for users of the game
 *
 */
public interface Scorable {
	
	public abstract Map<String, Integer> getScores();
	public abstract Map<String, Integer> incrementScore(String questionType);
	public abstract Map<String, Integer> getSpecificScore(String questionType);

}
