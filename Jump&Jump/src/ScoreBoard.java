import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class ScoreBoard extends Wall {

	ArrayList<NamedRabbit> rabbits = Level.rabbits;

	public ScoreBoard(int x, int y, int width, int height, int w, int h) throws IOException {
		super(x, y, width, height, w, h);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g2d) {
		Graphics g = (Graphics) g2d;
		super.draw(g2d);
		for (int i = 0; i < rabbits.size(); i++) {
			g.drawString((rabbits.get(i).name + " - " + Integer.toString(rabbits.get(i).score)), (int) x + 30,
					(int) y + (i + 1) * 30 + 10);
		}
	}
}
