package hw4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Typing extends JPanel implements Runnable, KeyListener {

	private HashMap<String, String> map = new HashMap<String, String>();
	private String[] knownNames = new String[100];
	private String[] unknownNames = new String[100];
	private boolean used1[] = new boolean[100];
	private boolean used2[] = new boolean[100];
	private JTextField textField = new JTextField();
	private Random random = new Random();
	private BufferedImage img1, img2;
	private String input1, input2;
	private int y = 0, rand1, rand2, unknownSize = 0;
	private HashMap<String, String> mapToWrite = new HashMap<String, String>();
	private GameStage gs;
	private File soundYes = new File("music/yes.wav");
	private File soundNo = new File("music/no.wav");
	private Clip clipYes, clipNo;
	
	Typing(GameStage gs) {
		try {
			clipYes = AudioSystem.getClip();
			clipNo = AudioSystem.getClip();
			clipYes.open(AudioSystem.getAudioInputStream(soundYes));
			clipNo.open(AudioSystem.getAudioInputStream(soundNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.gs = gs;
		setLayout(null);
		add(textField);
		textField.setBounds(0, 500, 300, 20);
		textField.addKeyListener(this);
		try {
			// read the known words and put them into map
			File f = new File("known_words.txt");
			FileInputStream fis = new FileInputStream(f);
			Scanner scanner = new Scanner(fis);
			while (scanner.hasNext()) {
				String name = scanner.next();
				String text = scanner.next();
				map.put(name, text);
			}
			scanner.close();
			// after putting into map, map the key to an integer
			int i = 0;
			for (Map.Entry<String, String> entry : map.entrySet())
				knownNames[i++] = entry.getKey();
			
			// read the unknown words and put them into unknownNames[]
			f = new File("unknown_words.txt");
			fis = new FileInputStream(f);
			scanner = new Scanner(fis);
			while(scanner.hasNext())
				unknownNames[unknownSize++] = scanner.next();
			scanner.close();
			
			// randomly find two numbers, and then read the numbered picture
			rand1 = random.nextInt(map.size());
			rand2 = random.nextInt(unknownSize);
			img1 = ImageIO.read(new File("img/known/" + knownNames[rand1]));
			img2 = ImageIO.read(new File("img/unknown/" + unknownNames[rand2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// get the text and split it into two pieces
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) { 
			String temp = textField.getText();
			String[] arr = temp.split(" ");
			input1 = arr[0];
			input2 = arr[1];
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// if the first word was correct, set background color to green
		// then clear the text area
		// then mark the words used, put the words into mapToWrite
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) { 
			if (input1.equals(map.get(knownNames[rand1]))) {
				play(1);
				textField.setBackground(Color.green);
				textField.setText("");
				used1[rand1] = true;
				used2[rand2] = true;
				gs.setNowScore(gs.getNowScore() + 5);
				mapToWrite.put(knownNames[rand1], map.get(knownNames[rand1]));
				mapToWrite.put(unknownNames[rand2], input2);
				System.out.println(knownNames[rand1] + " " + map.get(knownNames[rand1]));
				System.out.println(unknownNames[rand2] + " " + input2);
				// find the next valid item
				while (used1[rand1 = random.nextInt(map.size())]);
				while (used2[rand2 = random.nextInt(unknownSize)]);
				// read the new picture
				try {
					img1 = ImageIO.read(new File("img/known/" + knownNames[rand1]));
					img2 = ImageIO.read(new File("img/unknown/" + unknownNames[rand2]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// reset y
				y = 0;
			} else {
				// if the input is not correct, background becomes red
				play(0);
				textField.setBackground(Color.red);
			}
			// stop getting next pictures when scores 100
			if (gs.getNowScore() >= 100) {
				textField.removeKeyListener(this);
				textField.setEditable(false);
				return;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { 
		// reset to white when typing
		textField.setBackground(Color.white); 
	}

	@Override
	public void run() {
		// make the pictures move
		while (true) {
			try {
				Thread.sleep(30);
				if (++y > 500) y = 0;
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// stop when scores 100
			if (gs.getNowScore() >= 100) break;
		}
		// write the result to output.txt
		try {
			File f = new File("output.txt");
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);
			for (Map.Entry<String, String> entry : mapToWrite.entrySet())
				pw.write(entry.getKey() + " " + entry.getValue() + "\n");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// paint the background and draw the image
		super.paintComponent(g);
		setBackground(Color.lightGray);
		g.drawImage(img1, 10, y, img1.getWidth(), img1.getHeight(), null);
		g.drawImage(img2, 10 + img1.getWidth(), y, img2.getWidth(),	img2.getHeight(), null);
	}
	
	public void play(int x) {
		// playing music
		if (x == 1) {
			try{
				clipYes.setMicrosecondPosition(0);
				clipYes.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try{
				clipNo.setMicrosecondPosition(0);
				clipNo.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
