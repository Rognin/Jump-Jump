import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Hittable implements Drawable {

	Color color;
	int h;
	int w;
	BufferedImage left;
	BufferedImage right;
	BufferedImage up;
	BufferedImage down;
	BufferedImage upRight;
	BufferedImage upLeft;
	BufferedImage downRight;
	BufferedImage downLeft;
	BufferedImage bg;
	BufferedImage im;

	public Wall(int x, int y, int width, int height, int w, int h) throws IOException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.w = w;
		this.h = h;
		this.left = ImageIO.read(new File("graphics/blockTextures/dirtLeft.png"));
		this.right = ImageIO.read(new File("graphics/blockTextures/dirtRight.png"));
		this.up = ImageIO.read(new File("graphics/blockTextures/dirtUp.png"));
		this.down = ImageIO.read(new File("graphics/blockTextures/dirtDown.png"));
		this.upRight = ImageIO.read(new File("graphics/blockTextures/dirtUpRight.png"));
		this.upLeft = ImageIO.read(new File("graphics/blockTextures/dirtUpLeft.png"));
		this.downRight = ImageIO.read(new File("graphics/blockTextures/dirtDownRight.png"));
		this.downLeft = ImageIO.read(new File("graphics/blockTextures/dirtDownLeft.png"));
		this.bg = ImageIO.read(new File("graphics/blockTextures/dirtBg.png"));
		Drawable.drawables.add(this);
		Hittable.hittables.add(this);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		g.drawImage(bg, (int) this.x, (int) this.y, this.width, this.height, null);
		g.drawImage(up, (int) this.x, (int) this.y, (int) this.x + this.width, (int) this.y + this.h, 0, 0, this.width,
				this.h, null);
		g.drawImage(down, (int) this.x, (int) this.y + this.height - this.h + 3, (int) this.x + this.width,
				(int) this.y + this.height + 3, 0, 0, this.width, this.h, null);
		g.drawImage(left, (int) this.x, (int) this.y, (int) this.x + this.w, (int) this.y + this.height, 0, 0, this.w,
				this.height, null);
		g.drawImage(right, (int) this.x + this.width - this.w + 3, (int) this.y, (int) this.x + this.width + 3,
				(int) this.y + this.height, 0, 0, this.w, this.height, null);
		g.drawImage(upLeft, (int) this.x, (int) this.y, null);
		g.drawImage(upRight, (int) this.x + this.width - this.w, (int) this.y, null);
		g.drawImage(downLeft, (int) this.x, (int) this.y + this.height - this.h + 3, null);
		g.drawImage(downRight, (int) this.x + this.width - this.w + 2, (int) this.y + this.height - this.h + 2, null);
	}

}
