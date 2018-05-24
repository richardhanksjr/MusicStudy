package edu.cs622;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import testing.SimpleIntervalUptest;

public class Quiz {
	// A mapping to keep track of the total questions correct for each type of question
	private static Map<String, Integer> scoring = new HashMap<String, Integer>();
	private static List<Question> questions = questionGenerator();
	private static Random random = new Random();
	private static User user = null;
	// To get an equal sampling of major and minor scales in the interval up and down classes, toggle this boolean value
	private static boolean useMajor = true;


	public static void main(String[] args) {		
		Scanner reader = new Scanner(System.in);
		// Beginning of program, prompt user for user name and display their scores
		System.out.println("Hello!  Please enter your username to begin:");
		String userName = reader.next();
		user = new User(userName);
		System.out.println("Hello, " + userName + ".  Your current scores are:");
		printScores();
		System.out.println("\n\n_____________________________________________________");
		// Create instances of Question subclasses to "ask"
		//while the user hasn't exited the program
		Boolean askQuestions = true;
		Question question;
		while(askQuestions){
			//Get random question
			question = (AbstractQuestion) getQuestion();
			System.out.println("question is: " + question);
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
				String key = question.getKey();
				System.out.println("Key is: " + key);
				user.incrementScore(key);
				System.out.println("Your new score for " + key + " is " + (user.getSpecificScore(key).get(key)));
			}else{
				System.out.println("Sorry, that was not correct.");
			}
			// Whether the answer was correct or not, print the explanation, which will include the correct answer
			System.out.println(question.getExplanation());
			// For readability
			System.out.println("\n ------------------------------------------\n");
		}
		reader.close();
		System.out.println("\n ------------------------------\n");
		System.out.println("Your scores after this session were:");
		// Print the final score
		printScores();		
	}
	


	private static void printScores() {
		for(String key: user.getScores().keySet()){
			System.out.println(key + ": " + user.getScores().get(key));
		}
		
	}

	private static Question getQuestion() {
		int index = random.nextInt(questions.size());
		return questions.get(index);
	}

	private static List<Question> questionGenerator(){
		// Add questions to a List to randomly select from
		List<Question> questions = new ArrayList<>();
		// The number of questions to instantiate
		AbstractQuestion ques = null;
		int numQuestions = 20;
		// Keep an array of the available questions by key name, and cycle through to get a random assortment
		String[] questionKeys = {"Compound Scalar Intervals Down", "Compound Scalar Intervals Up", "Simple Interval"};
		for(int i = 0; i<numQuestions; i++){
			// To get a random sampling of the questions in the questionClasses List
			int questionTemplateIndex = i % questionKeys.length;
			ques = Quiz.getQuestionFromChoices(questionKeys[questionTemplateIndex]);
			questions.add(ques);		
		}

		return questions;
	}


	/**
	 * Uses a switch statement to pick from among the available question options
	 * @param key the key that is used to identify the type of question to instantiate
	 * @return an instantiated question subclass
	 */
	private static AbstractQuestion getQuestionFromChoices(String key) {
		int randomKey = 0 + (int)(Math.random() * ((11 - 0)));
		AbstractQuestion ques = null;
		switch(key){
		case "Compound Scalar Intervals Down":
			if(useMajor){
				ques = new SimpleIntervalDownGeneric<MajorScale>(new MajorScale(randomKey));
			}else{
				ques = new SimpleIntervalDownGeneric<NaturalMinorScale>(new NaturalMinorScale(randomKey));
			}
			useMajor = !useMajor;
			break;
		case "Compound Scalar Intervals Up":
			if(useMajor){
				ques = new SimpleIntervalUpGeneric<MajorScale>(new MajorScale(randomKey));
			}else{
				ques = new SimpleIntervalUpGeneric<NaturalMinorScale>(new NaturalMinorScale(randomKey));
			}
			useMajor = !useMajor;
			break;
		case "Simple Interval":
			// Get a random interval key/name
			Object[] availableKeys = Scale.getIntervalMapping().keySet().toArray();
			int randomIndexForKeys = 0 + (int)(Math.random() * (availableKeys.length));
			ques = new SimpleInterval(randomKey, (String) availableKeys[randomIndexForKeys]);
			break;
		}
		return ques;
	}

}
