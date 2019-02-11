import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public interface Updatable {
	public static ArrayList<Updatable> updatables = new ArrayList<>();
	public static ArrayList<Updatable> condidates = new ArrayList<>();
	public static ArrayList<Updatable> removers = new ArrayList<>();

	public void update() throws IOException;

	public static void addListener(Updatable x) {
		condidates.add(x);
	}

	public static void removeListener(Updatable x) {
		removers.add(x);
	}

	public static void updateAll() throws IOException {
		for (Updatable up : condidates) {
			updatables.add(up);
		}
		for (Updatable up : removers) {
			updatables.remove(up);
		}
		for (Hittable h : Hittable.condidates) {
			Hittable.hittables.add(h);
		}
		for (Hittable h : Hittable.removers) {
			Hittable.hittables.remove(h);
		}
		for (Updatable up : updatables) {
			up.update();
		}
	}

}
