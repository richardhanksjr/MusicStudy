package edu.cs622;

import java.util.concurrent.Callable;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Handles the countdown timer which is meant to be run as a separate thread and will also 
 * @author Richard Hanks
 * 
 *
 */
public class CountdownTimer implements Callable<String>{
	private int currentTimer;
	Text timerText;
	private Thread soundThread;
	
	/*
	 * @param text The Text object that represent the "countdown clock" in the JavaFX GUI window.
	 * @param timerStart this is the number of seconds from which to begin the timer countdown.
	 */
	public CountdownTimer(int timerStart, Text text){
		this.currentTimer = timerStart;
		this.timerText = text;
	}
	
	public void cancelSoundThread(){
		// If the timer hasn't gotten below the threshold (e.g. 5 seconds), there will be no
		// soundThread initialized.  Otherwise, interrupt the thread.
		if(soundThread != null){
			soundThread.interrupt();
		}else{
			// If the timer is greater than the threshold when this is called, set the timer
			// to zero to bypass starting the sound thread.
			currentTimer = 0;
		}
	}
	
	@Override
	public String call() throws Exception{
		CountdownSound sound;
//		Thread soundThread;
		while(currentTimer > 0){
			try{
				// Only one thread should ever be executing this block, but using synchronized to explicitly enforce this.
				synchronized(this){
					currentTimer--;
					// If fewer than 5 seconds remain, play the countdown "sound"
					if(currentTimer < 5){
						sound = new CountdownSound();
						soundThread = new Thread(sound);
						soundThread.start();
					}
					Thread.sleep(1000L);// 1 second
					timerText.setText("Seconds remaining: " + Integer.toString(currentTimer));
				}
			}catch(InterruptedException e){
				
			}
		}
		return "Done";
	}



}
