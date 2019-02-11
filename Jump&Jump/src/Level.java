import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Level {

	public static ArrayList<NamedRabbit> rabbits = new ArrayList<>();
	public ArrayList<Wall> walls = new ArrayList<>();
	public ArrayList<BackGround> bgs = new ArrayList<>();

	public Level level;
	public final int LEVEL_WIDTH = 1104;
	public final int LEVEL_HEIGHT = 640;

	public static final double GRAVITY = 0.4;

	public void load(String filename) throws IOException {
		Scanner sc = new Scanner(new File(filename));
		int f = sc.nextInt();
		for (int i = 0; i < f; i++) {
			bgs.add(new BackGround(sc.nextInt(), sc.nextInt(), sc.next()));
		}
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			ArrayList<AnimationSequence> curASs = new ArrayList<>();
			String curName = sc.next();
			String curGraphicsAdress = sc.next();
			double curX = sc.nextDouble();
			double curY = sc.nextDouble();
			int curUpKey = sc.nextInt();
			int curLeftKey = sc.nextInt();
			int curRightKey = sc.nextInt();
			int curDownKey = sc.nextInt();
			int curWidht = sc.nextInt();
			int curHeight = sc.nextInt();
			int curASMaxNumber = sc.nextInt();
			for (int j = 0; j < curASMaxNumber; j++) {
				AnimationSequence curAS = new AnimationSequence();
				curAS.duration = sc.nextInt();
				curAS.lifeTime = sc.nextInt();
				String CurASName = sc.next();
				curAS.frames = new ArrayList<>();
				for (int j2 = 0; j2 < curAS.duration; j2++) {
					if (j2 < 10) {
						String curASFrameName = curGraphicsAdress + CurASName + "0" + j2 + ".png";
						curAS.frames.add(ImageIO.read(new File(curASFrameName)));
					} else {
						String curASFrameName = curGraphicsAdress + CurASName + j2 + ".png";
						curAS.frames.add(ImageIO.read(new File(curASFrameName)));
					}
				}
				curASs.add(curAS);
			}
			rabbits.add(new NamedRabbit(curName, curX, curY, curUpKey, curLeftKey, curRightKey, curDownKey, curWidht, curHeight, curASs));
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			walls.add(new Wall(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
//					sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next()));
		}
		ScoreBoard sb = new ScoreBoard(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(),
				sc.nextInt());
	}

	public void draw(Graphics2D g2d) throws IOException {
		Drawable.drawAll(g2d);
	}

//	public void keyTyped(KeyEvent e) {
//		for (Rabbit kr : rabbits) {
//			kr.keyTyped(e);
//		}
//	}

	public void keyPressed(KeyEvent e) {
		for (Rabbit kr : rabbits) {
			kr.keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		for (Rabbit kr : rabbits) {
			kr.keyReleased(e);
		}
	}

	public void update() throws IOException {
		Updatable.updateAll();
	}

}
