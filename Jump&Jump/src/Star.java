import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Star extends Hittable implements Drawable, Updatable {

	double vx, vy;
	BufferedImage im;
	int counter;

	public Star(double x, double y) throws IOException {
		counter = 0;
		this.x = x;
		this.y = y;
		this.im = ImageIO.read(new File("graphics/characters/star.png"));
		this.height = im.getHeight();
		this.width = im.getWidth();
		Random r = new Random();
		double tmp = r.nextDouble();
		tmp = tmp * 2 * Math.PI;
		vx = Math.cos(tmp);
		vy = Math.sin(tmp);
		Updatable.addListener(this);
		drawables.add(this);
		Hittable.addListener(this);
	}

	@Override
	public void update() throws IOException {
		isBlockedDown = isBlockedLeft = isBlockedRight = isBlockedUp = false;
		for (Hittable h : hittables) {
			if (!(h instanceof Rabbit) && !(h instanceof Star)) {
				int res = this.hitTest(h);
				switch (res) {
				case HIT_DOWN:
					isBlockedDown = true;
					this.y = h.y - this.height;
					break;
				case HIT_UP:
					isBlockedUp = true;
					this.y = h.y + h.height;
					break;
				case HIT_RIGHT:
					isBlockedRight = true;
					this.x = h.x - this.width;
					break;
				case HIT_LEFT:
					isBlockedLeft = true;
					this.x = h.x + h.width;
					break;
				}
			}
		}
		if (isBlockedDown && this.vy > 0) {
			this.vy = -this.vy / 1.2;
			this.vx = this.vx / 1.5;
		}

		if (isBlockedUp && this.vy < 0)
			this.vy = 0;
		if (isBlockedRight && this.vx > 0)
			this.vx = -this.vx;
		if (isBlockedLeft && this.vx < 0)
			this.vx = -this.vx;
		if (!isBlockedDown) {
			vy += Level.GRAVITY / 64;
		}
		if (Math.abs(vy) > 1)
			if (vy > 0) {
				vy = 0.2;
			} else {
				vy = -0.2;
			}
		if (Math.abs(vx) > 1)
			if (vx > 0) {
				vx = 0.5;
			} else {
				vx = -0.5;
			}
		if (y > 2000) {
			drawables.remove(this);
			Updatable.removeListener(this);
			Hittable.removeListener(this);
		}
		x += vx;
		y += vy;
		counter++;
		if (counter > 1000) {
			drawables.remove(this);
			Updatable.removeListener(this);
			Hittable.removeListener(this);
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		g.drawImage(im, (int) x, (int) y, null);
	}

}
