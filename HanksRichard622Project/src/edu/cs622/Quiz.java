package edu.cs622;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import testing.SimpleIntervalUptest;

public class Quiz {
	// A mapping to keep track of the total questions correct for each type of question
	private static Map<String, Integer> scoring = new HashMap<String, Integer>();
	private static List<Question> questions = questionGenerator();
	private static Random random = new Random();


	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		// Create instances of Question subclasses to "ask"
		//while the user hasn't exited the program
		Boolean askQuestions = true;
		Question question;
		while(askQuestions){
			//Get random question
			question = (AbstractQuestion) getQuestion();
			// Ask question
			System.out.println(((AbstractQuestion)question).question);
			System.out.println("Enter answer or type \"Q\" to exit");
			// get user answer
			String answer = reader.next();
			if(answer.toUpperCase().equals("Q")){
				askQuestions = false;
				System.out.println("\nGoodbye!");
				break;
			}
			// check answer against and return if the answer was correct
			boolean correctAnswer = question.checkAnswer(answer.toUpperCase());
			// provide the correct answer
			if(correctAnswer){
				System.out.println("Correct!");
				// Keep track of correct answers for each type of question
				if(question.getClass() == SimpleIntervalUpMajorScale.class){
					String key = "Simple Interval Up Major Scale";
					String updatedScore = updateScore(key);
					System.out.println(updatedScore);

				}else if(question.getClass() == SimpleIntervalDownMajorScale.class){
					String key = "Simple Interval Down Major Scale";
					String updatedScore = updateScore(key);
					System.out.println(updatedScore);
				}
			}else{
				System.out.println("Sorry, that was not correct.  Correct answer was: " + ((AbstractQuestion)question).answer);
			}
			// For readability
			System.out.println("\n ------------------------------------------\n");
		}

		reader.close();
		System.out.println("\n ------------------------------\n");
		// Print the final score
		printFinalScore();
		
	}
	
	private static String updateScore(String key) {
		int currentScore = scoring.containsKey(key) ? scoring.get(key): 0;
		scoring.put(key, currentScore + 1);
		return "Score for " + key + ": " + scoring.get(key);
	}

	private static void printFinalScore() {
		System.out.println("Your scores from this session were:");
		for(String key: scoring.keySet()){
			System.out.println(key + ": " + scoring.get(key));
		}
		
	}

	private static Question getQuestion() {
		int index = random.nextInt(questions.size());
		return questions.get(index);
	}

	private static List<Question> questionGenerator(){
		// A List of the Question subclasses to choose from
		List<Class <?>> questionClasses = new ArrayList<Class <?>>();
		// Add questions to a List to randomly select from
		List<Question> questions = new ArrayList<>();
		questionClasses.add(SimpleIntervalUpMajorScale.class);
		// Set the initial scoring
		scoring.put("Simple Interval Up Major Scale", 0);
		questionClasses.add(SimpleIntervalDownMajorScale.class);
		// Set the initial scoring
		Quiz.scoring.put("Simple Interval Down Major Scale", 0);
		// The number of questions to instantiate
		AbstractQuestion ques = null;
		int numQuestions = 20;
		for(int i = 0; i<numQuestions; i++){
			// To get a random sampling of the questions in the questionClasses List
			int questionTemplateIndex = i % questionClasses.size();
			// Get a random int to be used as the "key" for each question
			int randomInt = 0 + (int)(Math.random() * ((11 - 0)));
			try {
				ques = (AbstractQuestion)questionClasses.get(questionTemplateIndex).getConstructors()[0].newInstance(new Object[]{new Integer(randomInt)});
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
				System.exit(1);
			}
			questions.add(ques);		
		}

		return questions;
	}

}
