package edu.cs622;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		Scene userNameScene = new Scene(grid, 400, 275);
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
					QuizApplication.user = new User(userNameText.getText());
					System.out.println("user scores: " + QuizApplication.user.getScores());
					setGameScene();
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
		Scene gameScene = new Scene(grid, 400, 275);
		Text scenetitle = new Text("Let's play, " + user.getUserName() + "!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		question = (AbstractQuestion) Quiz.getQuestion();
		grid.add(scenetitle, 2,0);
		Text questionText = new Text(question.getQuestion());
		questionText.wrappingWidthProperty().bind(gameScene.widthProperty().subtract(30));
		grid.add(questionText, 2, 2);
		Button correctQuesBtn = new Button(question.getAnswer());
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		hbBtn.getChildren().add(correctQuesBtn);
		grid.add(hbBtn, 2, 4);
		stage.setScene(gameScene);
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
	}
	
	// Called when an answer is selected.  Displays feedback to the user on their answer.  Gives option to quit or select next question.
	private void setAnswerScene(boolean correctAnswer) throws Exception{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene answerScene = new Scene(grid, 400, 275);
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
	 * When the user selects to "quit" the program, display an exit screen with their updated scores
	 */
	private void displayScores(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene exitScene = new Scene(grid, 400, 275);
		// Create a variable to store the scores and then append to the screen
		StringBuilder scoresString = new StringBuilder();
		for(String key: user.getScores().keySet()){
			scoresString.append(key + ": " + user.getScores().get(key) + "\n");
		}
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
	}
				
}


