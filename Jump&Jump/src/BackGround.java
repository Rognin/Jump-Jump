import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround implements Drawable {

	BufferedImage im;
	int width, height;

	public BackGround(int width, int height, String imageName) throws IOException {
		this.width = width;
		this.height = height;
		im = ImageIO.read(new File(imageName));
		Drawable.drawables.add(this);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		g.drawImage(im, 0, 0, width, height, null);
	}

}
