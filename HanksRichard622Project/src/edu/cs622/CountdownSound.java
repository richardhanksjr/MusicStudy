package edu.cs622;

import java.util.concurrent.Callable;

import javafx.scene.media.AudioClip;

/**
 * 
 * @author Richard Hanks
 * Runnable class to be used for playing a sound as the player's time is about to finish
 *
 */
public class CountdownSound implements Runnable{
	
	@Override 
	public void run(){
		AudioClip note = new AudioClip(this.getClass().getResource("click_05.wav").toString());
		note.play();
	}
}
