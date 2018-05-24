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
	private static User user = null;


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
//		TODO remove unneeded elements like the initial scoring now that there is persistent storage
		// A List of the Question subclasses to choose from
		List<Class <?>> questionClasses = new ArrayList<Class <?>>();
		// Add questions to a List to randomly select from
		List<Question> questions = new ArrayList<>();
//		questionClasses.add(SimpleIntervalUpMajorScale.class);
		questionClasses.add(SimpleIntervalUpGeneric.class);
		// Set the initial scoring
		scoring.put("Simple Interval Up Major Scale", 0);
//		questionClasses.add(SimpleIntervalDownMajorScale.class);
		questionClasses.add(SimpleIntervalDownGeneric.class);
		// Set the initial scoring
		Quiz.scoring.put("Simple Interval Down Major Scale", 0);
		// The number of questions to instantiate
		AbstractQuestion ques = null;
		int numQuestions = 20;
		// To get an equal sampling of major and minor scales in the interval up and down classes, toggle this boolean value
		boolean useMajor = true;
		for(int i = 0; i<numQuestions; i++){
			// To get a random sampling of the questions in the questionClasses List
			int questionTemplateIndex = i % questionClasses.size();
			// Get a random int to be used as the "key" for each question
			int randomInt = 0 + (int)(Math.random() * ((11 - 0)));

			try {
				// This is where we instantiate the question objects before adding them to the List of questions
				if(useMajor){
					ques = (AbstractQuestion)questionClasses.get(questionTemplateIndex).getConstructors()[0].newInstance(new Object[]{new MajorScale(randomInt)});
				}else{
					ques = (AbstractQuestion)questionClasses.get(questionTemplateIndex).getConstructors()[0].newInstance(new Object[]{new NaturalMinorScale(randomInt)});
				}
				System.out.println(ques.question);

				// Toggle the major/minor flag
				useMajor = !useMajor;

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				// TODO Log stack trace and time
				System.out.println("The program encountered a problem is and is terminating.  Status(1)");
				System.exit(1);
			}
			questions.add(ques);		
		}

		return questions;
	}

}
