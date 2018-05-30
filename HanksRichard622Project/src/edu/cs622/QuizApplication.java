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
					QuizApplication.username = userNameText.getText();
					setGameScene();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return userNameScene;
	}
	
	private void setGameScene() throws Exception{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene gameScene = new Scene(grid, 400, 275);
		Text scenetitle = new Text("Let's play, " + username + "!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		question = (AbstractQuestion) Quiz.getQuestion();
		grid.add(scenetitle, 2,0);
		Text questionText = new Text(question.getQuestion());
		questionText.wrappingWidthProperty().bind(gameScene.widthProperty().subtract(30));
		grid.add(questionText, 2, 2);
		Button quesBtn = new Button("Next Question");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		hbBtn.getChildren().add(quesBtn);
		grid.add(hbBtn, 2, 4);
		stage.setScene(gameScene);
		quesBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				System.out.println("question is: " + question.getQuestion());
				try {
					setGameScene();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});


	}
	
//	private Scene updateGameScene(){
//		GridPane grid = new GridPane();
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25, 25, 25, 25));
////		Scene gameScene = new Scene(grid, 400, 275);
////		Text scenetitle = new Text("Let's play, " + username + "!");
////		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
////		grid.add(scenetitle, 0, 0, 1, 1);
//		// Get the next question to ask
////		Button quesBtn = new Button("Next Question");
//		HBox hbBtn = new HBox(10);
//		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
////		hbBtn.getChildren().add(quesBtn);
//		grid.add(hbBtn, 1, 4);
//		//Text for the question
////		quesBtn.setOnAction(new EventHandler<ActionEvent>(){
////			@Override
////			public void handle(ActionEvent e){
////				question = (AbstractQuestion) Quiz.getQuestion();
//////				questionText = new Text(question.getQuestion());
////				questionText.setText(question.getQuestion());
////				grid.add(questionText, 2, 2);
////				System.out.println("question is: " + question.getQuestion());
////			}
////		});
//		return gameScene;
//	}
				
}


