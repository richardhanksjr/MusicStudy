package edu.cs622;

import java.util.concurrent.Callable;

import javafx.scene.media.AudioClip;

public class CountdownSound implements Runnable{
	
	@Override 
	public void run(){
		AudioClip note = new AudioClip(this.getClass().getResource("click_05.wav").toString());
		note.play();
	}
}
