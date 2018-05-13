package edu.cs622;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {
	private static List<AbstractQuestion> questions = questionGenerator();
	private static Random random = new Random();

	public static void main(String[] args) {

		// Create instances of Question subclasses to "ask"
		//while the user hasn't exited the program
		boolean askQuestions = true;
		AbstractQuestion question;
		while(askQuestions){
			//Get random question
			question = getQuestion();
			// Ask question
			System.out.println(question.question);
			askQuestions = false;

		}


		// get user answer
		// check answer against and return if the answer was correct
		// provide the correct answer
		// prompt for another question or to exit

	}
	
	private static AbstractQuestion getQuestion() {
		int index = random.nextInt(questions.size());
		return questions.get(index);
	}

	private static List<AbstractQuestion> questionGenerator(){
		// A List of the Question subclasses to choose from
		List<Class <?>> questionClasses = new ArrayList<Class <?>>();
		questionClasses.add(SimpleIntervalUpMajorScale.class);
		questionClasses.add(SimpleIntervalDownMajorScale.class);
//		Constructor<?> testQuestion = (Constructor<?>) questions.get(0).getConstructors()[0].newInstance(0);
		AbstractQuestion testy = null;
		try {
			testy = (AbstractQuestion)questionClasses.get(1).getConstructors()[0].newInstance(new Object[]{new Integer(6)});
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test question is: " + testy.question);
//		List<Class<? extends AbstractQuestion>> classes = new ArrayList<Class<? extends AbstractQuestion>>();
//		classes.add(SimpleIntervalUpMajorScale.class);
//		classes.add(SimpleIntervalDownMajorScale.class);
//		AbstractQuestion testy = classes.get(0).newInstance();
//		System.out.println(testy.question);

		int randomInt = 0 + (int)(Math.random() * ((11 - 0)));
		// The number of questions to instantiate
		int numQuestions = 20;
		for(int i = 0; i<numQuestions; i++){
			
		}
		// 2 versions of interval up
		AbstractQuestion intUp1 = new SimpleIntervalUpMajorScale(7);
		AbstractQuestion intUp2 = new SimpleIntervalUpMajorScale(5);
		// 2 versions of interval down
		AbstractQuestion intDown1 = new SimpleIntervalDownMajorScale(3);
		AbstractQuestion intDown2 = new SimpleIntervalDownMajorScale(7);
		// Add questions to a List to randomly select from
		List<AbstractQuestion> questions = new ArrayList<>();
		questions.add(intUp1);
		questions.add(intUp2);
		questions.add(intDown1);
		questions.add(intDown2);
		return questions;
	}

}
