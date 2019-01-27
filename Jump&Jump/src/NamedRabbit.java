import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class NamedRabbit extends Rabbit {

	String name;

	public NamedRabbit(String name, double x, double y, int upKey, int leftKey, int rightKey, int downKey, int width,
			int height, ArrayList<AnimationSequence> aSs) throws IOException {
		super(x, y, upKey, leftKey, rightKey, downKey, width, height, aSs);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		super.draw(g2d);
		g.setColor(Color.white);
		g.drawString(name, (int) x - 15, (int) y - 10);
	}

	public void update() throws IOException {
		super.update();
	}

}
