import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DeathAnimation implements Drawable {

	double x, y;
	Image im;
	int cnt;

	public DeathAnimation(String image) throws IOException {
		this.im = Toolkit.getDefaultToolkit().createImage("graphics/characters/deathAnimation.gif");
		cnt = 0;
		Drawable.drawables.add(this);
	}

	@Override
	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		cnt++;
		if (cnt < 54) {
			g.drawImage(im, (int) x, (int) y, null);
		}
	}

}
