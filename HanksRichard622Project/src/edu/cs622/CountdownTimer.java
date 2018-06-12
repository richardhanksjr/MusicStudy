package edu.cs622;

import java.util.concurrent.Callable;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Handles the countdown timer which is meant to be run as a separate thread and will also 
 * @author Richard Hanks
 *
 */
public class CountdownTimer implements Callable<String>{
	private int currentTimer = 5;
	Text timerText;
	
	public CountdownTimer(int timerStart, Text text){
		this.currentTimer = timerStart;
		this.timerText = text;
	}
	
	@Override
	public String call() throws Exception{
		System.out.println("Running!");
		while(currentTimer > 0){
			System.out.println(String.format("Time remaining: %d", currentTimer));
			try{
				currentTimer--;
				Thread.sleep(1000L);// 1 second
				timerText.setText(Integer.toString(currentTimer));
				// test split pane
//				Text text = new Text("10");
//				SplitPane clockTimer = new SplitPane();
//				clockTimer.setPrefSize(50, 50);
//				clockTimer.getItems().add(0, new Text("10"));
//				grid.add(clockTimer, 3, 0);
				
			}catch(InterruptedException e){
				
			}
		}
		return "Done";
	}



}
