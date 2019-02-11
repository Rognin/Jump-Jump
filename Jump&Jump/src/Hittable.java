import java.util.ArrayList;

public class Hittable {

	final public int HIT_UP = 1;
	final public int HIT_DOWN = 2;
	final public int HIT_LEFT = 3;
	final public int HIT_RIGHT = 4;
	final public int HIT_NONE = 0;
	int width, height;
	double x, y;
	boolean isBlockedUp, isBlockedDown, isBlockedLeft, isBlockedRight;

	public static ArrayList<Hittable> hittables = new ArrayList<>();
	public static ArrayList<Hittable> condidates = new ArrayList<>();
	public static ArrayList<Hittable> removers = new ArrayList<>();

	public static void addListener(Hittable x) {
		condidates.add(x);
	}
	
	public static void removeListener(Hittable x) {
		removers.add(x);
	}

	public int hitTest(Hittable other) {
		if (other.x <= x && other.x + other.width >= x + this.width) {

			if (this.y + this.height < other.y) {
				return HIT_NONE;
			}
			if (this.y > other.y + other.height) {
				return HIT_NONE;
			}
			if (other.y + other.height / 2 < this.y + this.height / 2) {
				return HIT_UP;
			} else {
				return HIT_DOWN;
			}
		}
		if (other.y <= this.y && other.y + other.height >= this.y + this.height) {
			if (this.x + this.width < other.x) {
				return HIT_NONE;
			}
			if (this.x > other.x + other.width) {
				return HIT_NONE;
			}
			if (other.x + other.width / 2 <= this.x + this.width / 2) {
				return HIT_LEFT;
			} else {
				return HIT_RIGHT;
			}
		}

		// RIGHT DOWN
		if (this.x <= other.x + other.width && this.x + this.width >= other.x + other.width
				&& this.y <= other.y + other.height && this.y + this.height >= other.y + other.height) {
			if (Math.abs(other.x + other.width - this.x) >= Math.abs(other.y + other.height - this.y))
				return HIT_UP;
			return HIT_LEFT;
		}
		// RIGHT UP
		if (this.x <= other.x + other.width && this.x + this.width >= other.x + other.width
				&& this.y + this.height >= other.y && this.y <= other.y) {
			if (Math.abs(other.x + other.width - this.x) >= Math.abs(-other.y + this.height + this.y))
				return HIT_DOWN;
			return HIT_LEFT;
		}
		// LEFT UP
		if (this.x < other.x && this.x + this.width >= other.x && this.y + this.height >= other.y
				&& this.y <= other.y) {
			if (Math.abs(-other.x + this.x + this.width) >= Math.abs(-other.y + this.height + this.y))
				return HIT_DOWN;
			return HIT_RIGHT;
		}
		// LEFT DOWN
		if (this.x < other.x && this.x + this.width >= other.x && this.y <= other.y + other.height
				&& this.y + this.height >= other.y + other.height) {
			if (Math.abs(-other.x + this.x + this.width) >= Math.abs(other.y + other.height - this.y))
				return HIT_UP;
			return HIT_RIGHT;
		}
		return HIT_NONE;
	}
}
