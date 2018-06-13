package edu.cs622;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class QuizApplication extends Application{
// Declare an instance of a stage globally so that I can modify it throughout
	private Stage stage;
	Question question;
	static String username;
	private static User user;
	Text questionText = new Text();
	private int sceneWidth = 500;
	private int sceneHeight = 500;
	// Creates an Executor that uses a single worker thread operating off an unbounded queue.
	ExecutorService ex = Executors.newSingleThreadExecutor();
	Future<String> timerFuture;
	Text timerText = new Text();
	Timer timer = new Timer();
	ScheduledExecutorService checkTimer;
	ScheduledFuture<?> checkTimerThread; 
	/**
	 * Method for checking if the timer is finished and, if so, initializing the "shutdown" sequence
	 *  
	 */
	private void checkForTimerShutdown(){
		// Check if the timer/countdown thread is done running
		if(timerFuture.isDone()){
			// This is the thread that calls this method (checkForTimerShutdown).  If the timerFuture thread is done, need to kill this as well.
			checkTimerThread.cancel(true);
			// Countdown thread/timer is done, use this command to call the final screen, which will display the user's scores.
			Platform.runLater(() -> 
				displayScores()
			);
		}
	}
	
	@Override
	 public void start(Stage mainStage){
		stage = mainStage;
		mainStage.setTitle("MusicTrivia");
		Scene userNameScene = getLoginScene();
		stage.setScene(userNameScene);
		stage.show();
	} 
	
	public static void main(String[] args){
		// Generate the initial questions
		launch(args);
	}
	
	
	private Scene getLoginScene(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
//		// test split pane
//		SplitPane clockTimer = new SplitPane();
//		clockTimer.setPrefSize(50, 50);
//		clockTimer.getItems().add(0, timerText);
//		grid.add(clockTimer, 3, 0);
		Scene userNameScene = new Scene(grid, sceneWidth, sceneHeight);
		Text scenetitle = new Text("Welcome!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label userNameLabel = new Label("Enter username to begin:");
		grid.add(userNameLabel, 0, 1);
		TextField userNameText = new TextField();
		grid.add(userNameText, 1, 1);
		grid.add(scenetitle, 0,0,1, 1);
		// Submit button
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		final Text actiontarget = new Text();
		grid.add(actiontarget,1, 6);
		// Log the user in and change the scene to the game screen
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					QuizApplication.user = new UserDatabase(userNameText.getText());
					// Start the countdown timer as a new thread
					timerFuture = ex.submit(new CountdownTimer(10, timerText));
					setGameScene();
					// Start another new thread to check if the timer is done
					checkTimer = Executors.newSingleThreadScheduledExecutor();
					// Every 500 MS, check if the timer thread has completed.  checkForTimerShutdown will handle the logic if the timer has completed.
					checkTimerThread = checkTimer.scheduleAtFixedRate(new Runnable(){
						@Override
						public void run(){
							checkForTimerShutdown();
						}
					}, 0, 500, TimeUnit.MILLISECONDS);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return userNameScene;
	}
	
	/**
	 * This is called after "login" and when user selects "next question" to load the next question along with a selection of "multiple choice" answers.
	 * 
	 * @throws Exception
	 */
	private void setGameScene() throws Exception{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		SplitPane clockTimer = new SplitPane();
		clockTimer.setPrefSize(50, 50);
		clockTimer.getItems().add(0, timerText);
		grid.add(clockTimer, 2, 5);
		Scene gameScene = new Scene(grid, sceneWidth, sceneHeight);
		Text scenetitle = new Text("Let's play, " + user.getUserName() + "!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		question = (AbstractQuestion) Quiz.getQuestion();
		grid.add(scenetitle, 2,0);
		Text questionText = new Text(question.getQuestion());
		questionText.wrappingWidthProperty().bind(gameScene.widthProperty().subtract(30));
		grid.add(questionText, 2, 2);
		// Get the list of incorrect options
		String[] incorrectAnswers = ((AbstractQuestion) question).getIncorrectAnswerOptions();
		Button correctQuesBtn = new Button(question.getAnswer());
		correctQuesBtn.wrapTextProperty().setValue(true);
		Button incorrect0 = new Button(incorrectAnswers[0]);
		incorrect0.wrapTextProperty().setValue(true);
		Button incorrect1 = new Button(incorrectAnswers[1]);
		incorrect1.wrapTextProperty().setValue(true);
		Button incorrect2 = new Button(incorrectAnswers[2]);
		incorrect2.wrapTextProperty().setValue(true);
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		// Need to randomize the order the buttons are added so that the correct answer
		// isn't always in the same location.  Making a list of the available buttons shuffling
		// the order of buttons and then adding them to hbBtn in the shuffled order
		List<Button> buttons = new ArrayList<>(Arrays.asList(correctQuesBtn, incorrect0, incorrect1, incorrect2));
		Collections.shuffle(buttons);
		for(Button button: buttons){
			hbBtn.getChildren().add(button);

		}
		grid.add(hbBtn, 2, 4);
		stage.setScene(gameScene);
//		timer.schedule(checkForTimerShutdown(), 500);
		correctQuesBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					setAnswerScene(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		incorrect0.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					setAnswerScene(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		incorrect1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					setAnswerScene(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		incorrect2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					setAnswerScene(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	// Called when an answer is selected.  Displays feedback to the user on their answer.  Gives option to quit or select next question.
	private void setAnswerScene(boolean correctAnswer) throws Exception{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		SplitPane clockTimer = new SplitPane();
		clockTimer.setPrefSize(50, 50);
		clockTimer.getItems().add(0, timerText);
		grid.add(clockTimer, 2, 5);
		Scene answerScene = new Scene(grid, sceneWidth, sceneHeight);
		Text correctAnswerTitle;
		// If the user selected the correct answer:
		if(correctAnswer){
			user.incrementScore(question.getKey());
			correctAnswerTitle = new Text("Your answer is correct.");
		}else{
			correctAnswerTitle = new Text("Incorrect.");
		}
		Text answerExplanation = new Text(question.getExplanation());
		answerExplanation.wrappingWidthProperty().bind(answerScene.widthProperty().subtract(30));
		correctAnswerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(correctAnswerTitle, 2,0);
		grid.add(answerExplanation, 2, 1);
		stage.setScene(answerScene);
		Button nextQuestionButton = new Button("Next Question");
		Button quitButton = new Button("Quit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		hbBtn.getChildren().add(nextQuestionButton);
		hbBtn.getChildren().add(quitButton);
		grid.add(hbBtn, 2, 4);
//		checkForTimerShutdown();
		// Set action to get next question
		nextQuestionButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try{
					setGameScene();
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		// Set action to quit the program
		quitButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				displayScores();
			}
		});
			
	}
	
	/**
	 * When the user selects to "quit" the program, display an exit screen with their updated scores.
	 * This is also called when the timer/countdown thread completes.
	 */
	private void displayScores(){
		try{
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			Scene exitScene = new Scene(grid, sceneWidth, sceneHeight);
			// Create a variable to store the scores and then append to the screen
			StringBuilder scoresString = new StringBuilder();
			Map<String, Integer> userScores = user.getScores();
			userScores.entrySet().stream().forEach((elem) -> scoresString.append(elem.getKey() + ": " + elem.getValue() + "\n"));
			Text scoresText = new Text(scoresString.toString());
			grid.add(scoresText, 2,0);
			Button quitButton = new Button("Exit");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BASELINE_CENTER);
			hbBtn.getChildren().add(quitButton);
			grid.add(hbBtn, 2, 4);
			stage.setScene(exitScene);
			// Set action to quit the program
			quitButton.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e){
					System.exit(0);
				}
			});

		}catch(Exception e){
			System.out.println(e);
		}
	}
				
}


