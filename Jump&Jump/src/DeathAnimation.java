import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DeathAnimation implements Drawable, Updatable {

	double x, y;
	Image im;
	int cnt, aSFrameNumber, aSChangeCounter;
	boolean flag = false;
	AnimationSequence as;

	public DeathAnimation() throws IOException {
		as = new AnimationSequence();
		as.frames = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			as.frames.add(ImageIO
					.read(new File("graphics/characters/deathAnimation/deathAnimation-" + (i + 1) + ".png.png")));
		}
		as.duration = 12;
		as.lifeTime = 10000;
		cnt = 0;
		Drawable.drawables.add(this);
		Updatable.addListener(this);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		g.drawImage(im, (int) x, (int) y, null);
	}

	@Override
	public void update() throws IOException {
		int delay = as.lifeTime / as.duration;
		aSFrameNumber = aSChangeCounter / delay % as.duration;
		if (aSFrameNumber == 0 && aSChangeCounter > as.lifeTime) {
			aSChangeCounter = 0;
		}
		System.out.println(aSFrameNumber);
		this.im = as.frames.get(aSFrameNumber);
		aSChangeCounter += 20;
		if (aSChangeCounter > as.lifeTime) {
			drawables.remove(this);
			Updatable.removeListener(this);
		}
	}

}
