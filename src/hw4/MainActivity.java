package hw4;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainActivity {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				play();
				// new a window and set the values
				MyWindow window = new MyWindow();
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setSize(950, 550);
				window.setVisible(true);
			}
		});
	}
	public static void play() {
		// playing music
		try{
			File file = new File("music/music.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
