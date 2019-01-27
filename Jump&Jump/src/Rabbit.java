import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EventListener;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Rabbit extends Hittable implements Drawable {

	static double DVX = 5;
	static int JUMPSPEED = -10;
	static int RESP_X = 100;
	static int RESP_Y = 120;
	double vx, vy;
	int upKey, leftKey, rightKey, downKey;
	Color color;
	boolean isBlockedUp, isBlockedDown, isBlockedLeft, isBlockedRight;
	static int sleepRightASNumber = 0;
	static int sleepLeftASNumber = 1;
	static int runRightASNumber = 2;
	static int runLeftASNumber = 3;
	static int jumpRightASNumber = 4;
	static int jumpLeftASNumber = 5;
	static int fallRightASNumber = 6;
	static int fallLeftASNumber = 7;
	String graphicsAdress;
	BufferedImage im;
	int direction;
	DeathAnimation da;

	ArrayList<AnimationSequence> aSs;

	int score;
	int aSChangeCounter = 0; // frames passed from last animationSequence change
	int asNumber; // Number of current aS
	int aSFrameNumber = 0; // number of current aS frame
	AnimationSequence curAS;

	public Rabbit(double x, double y, int upKey, int leftKey, int rightKey, int downKey, int width, int height,
			ArrayList<AnimationSequence> aSs) throws IOException {
		this.x = x;
		this.y = y;
		this.upKey = upKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.downKey = downKey;
		this.width = width;
		this.height = height;
		this.aSs = aSs;

		Drawable.drawables.add(this);
		Hittable.hittables.add(this);
	}

	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		g.drawImage(im, (int) this.x, (int) this.y, this.width, this.height, null);
	}

	public void update() throws IOException {
		isBlockedDown = isBlockedLeft = isBlockedRight = isBlockedUp = false;
		vy += Level.GRAVITY;
		for (Hittable h : hittables) {
			if (h != this) {
				int res = this.hitTest(h);
				switch (res) {
				case HIT_DOWN:
					isBlockedDown = true;
					this.y = h.y - this.height;
					break;
				case HIT_UP:
					isBlockedUp = true;
					if (h.getClass() == this.getClass()) {
						da = new DeathAnimation("graphics/characters/deathAnimation.gif");
						da.x = this.x;
						da.y = this.y;
						da.cnt = 0;
						this.x = RESP_X;
						this.y = RESP_Y;
						((Rabbit) h).score++;
						break;
					}
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
		if (isBlockedDown && this.vy > 0)
			this.vy = 0;
		if (isBlockedUp && this.vy < 0)
			this.vy = 0;
		if (isBlockedRight && this.vx > 0)
			this.vx = 0;
		if (isBlockedLeft && this.vx < 0)
			this.vx = 0;
		x += vx;
		y += vy;
		if (vx > 0) {
			this.direction = 1;
		}
		if (vx < 0) {
			this.direction = 0;
		}
		if (isBlockedDown) {
			if (direction == 0) {
				if (vx < 0) {
					asNumber = runLeftASNumber;
				}
				if (vx == 0) {
					asNumber = sleepLeftASNumber;
				}
			}
			if (direction == 1) {
				if (vx > 0) {
					asNumber = runRightASNumber;
				}
				if (vx == 0) {
					asNumber = sleepRightASNumber;
				}
			}
		} else {
			if (vy >= 0) {
				if (direction == 0) {
					asNumber = fallLeftASNumber;
				} else {
					asNumber = fallRightASNumber;
				}
			}
			if (vy < 0) {
				if (direction == 0) {
					asNumber = jumpLeftASNumber;
				} else {
					asNumber = jumpRightASNumber;
//					this.im = ImageIO.read(new File(jumpRight));
				}

			}
		}

		curAS = aSs.get(asNumber);
		int delay = 1000 / curAS.duration;
		aSFrameNumber = aSChangeCounter / delay % curAS.duration;
		this.im = curAS.frames.get(aSFrameNumber);
		if (aSFrameNumber == 0 && aSChangeCounter > 1000) {
			aSChangeCounter = 0;
		}

		aSChangeCounter += 20;

		if (vx > 0) {
			direction = 1;
		}
		if (vx < 0) {
			direction = 0;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 32) {
			vy = JUMPSPEED;
		}
		if (e.getKeyCode() == rightKey) {
			vx = DVX;
		}
		if (e.getKeyCode() == leftKey) {
			vx = -DVX;
		}
		if (e.getKeyCode() == downKey) {
			vy += 1;
		}
		if (e.getKeyCode() == upKey) {
			if (isBlockedDown) {
				vy = JUMPSPEED;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == leftKey) {
			if (vx < 0) {
				vx = 0;
			}
		}
		if (e.getKeyCode() == rightKey) {
			if (vx > 0) {
				vx = 0;
			}
		}
	}

}
