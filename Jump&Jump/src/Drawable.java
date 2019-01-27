import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public interface Drawable {
	public static ArrayList<Drawable> drawables = new ArrayList<>();

	public void draw(Graphics2D g2d);

	public static void drawAll(Graphics2D g2d) {
		for (Drawable dr : drawables) {
			dr.draw(g2d);
		}
	}
}
